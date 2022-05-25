package com.tanhua.sso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.service.SmsService
 * @Date: 2022年05月21日 19:05
 */
@Service
public class SmsService {
    @Autowired
    private RestTemplate restTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     *  测试
     * @param mobile
     * @return
     */
    public String sendSms(String mobile) {
        int code = RandomUtils.nextInt(100000, 999999);
        //发送成功 TODO 没有短信平台
        return String.valueOf(code);
    }

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    public Map<String, Object> sendCheckCode(String mobile) {
        HashMap<String, Object> result = new HashMap<>(2);
        try {
            String rediskey = "CHECK_CODE_" + mobile;
            String value = this.redisTemplate.opsForValue().get(rediskey);
            if (StringUtils.isNoneEmpty(value)) {
                result.put("code", 1);
                result.put("msg", "上一次发送的验证码还未失效");
                return result;
            }
            String code = this.sendSms(mobile);
            if (null == code) {
                result.put("code", 2);
                result.put("msg", "发送短信验证码失败");
                return result;
            }
            //发送验证码成功
            result.put("code", 3);
            result.put("msg", "ok");

            //存入redis，2分钟后失效
            this.redisTemplate.opsForValue().set(rediskey, code, Duration.ofMinutes(2));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 4);
            result.put("msg", "发送验证码出现异常");
            return result;
        }
    }
}