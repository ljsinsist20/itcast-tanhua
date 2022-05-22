package com.tanhua.sso.controller;

import com.tanhua.sso.service.UserService;
import com.tanhua.sso.vo.ErrorResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.controller.UserController
 * @Date: 2022年05月21日 22:22
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("loginVerification")
    public ResponseEntity<Object> login(@RequestBody Map<String, Object> param) {
        try {
            String mobile = param.get("phone").toString();
            String code = param.get("verificationCode").toString();
            String token = this.userService.login(mobile, code);

            Map<String, Object> result = new HashMap<>(2);
            if (StringUtils.isNotEmpty(token)) {
                String[] ss = StringUtils.split(token, "|");
                Boolean isNew = Boolean.valueOf(ss[0]);
                String tokenStr = ss[1];

                result.put("isNew", isNew);
                result.put("token", tokenStr);
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 登录失败，验证码错误
        ErrorResult errorResult = ErrorResult.builder().errCode("000000").errMessage("验证码错误").build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }
}
