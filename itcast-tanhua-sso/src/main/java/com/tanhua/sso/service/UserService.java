package com.tanhua.sso.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanhua.sso.mapper.UserMapper;
import com.tanhua.sso.pojo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.service.UserService
 * @Date: 2022年05月21日 22:22
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${jwt.secret}")
    private String secret;
    public String login(String mobile, String code) {
        //是否为新用户
        Boolean isNew = false;
        //判断验证码
        String redisKey = "CHECK_CODE_" + mobile;
        String redisCode = redisTemplate.opsForValue().get(redisKey);
        if (!StringUtils.equals(redisCode, code)) {
            return null;
        }
        //判断手机号
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        User selectUser = this.userMapper.selectOne(queryWrapper);
        if (selectUser == null) {
            //手机号未注册
            User user = new User();
            user.setMobile(mobile);
            user.setPassword(DigestUtils.md5Hex(secret + "_123456"));//默认密码

            this.userMapper.insert(user);
            selectUser = user;
            isNew = true;
        }
        //产生token
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("mobile", mobile);
        claims.put("id", selectUser.getId());
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
        //将用户数据写入到redis中
        String redisTokenKey = "TOKEN_" + token;
        try {
            this.redisTemplate.opsForValue().set(redisTokenKey, MAPPER.writeValueAsString(selectUser), Duration.ofHours(1));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            // 发送登录成功的消息
            Map<String, Object> msg = new HashMap<>();
            msg.put("userId", selectUser.getId());
            msg.put("date", new Date());
            this.rocketMQTemplate.convertAndSend("tanhua-sso-login", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNew + "|" + token;
    }
}

