package com.tanhua.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.MyApplication
 * @Date: 2022年05月21日 18:28
 */

@MapperScan("com.tanhua.sso.mapper")
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
