package com.cloud.cloudauthresource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称：CloudAuthServerApplication<br>
 * 类描述：<br>
 * 创建时间：2018年12月01日<br>
 *
 * @author 王芳
 * @version 1.0.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableResourceServer
@RestController
@Slf4j
public class CloudAuthResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudAuthResourceApplication.class, args);
    }

    // 添加一个测试访问接口
    @GetMapping("/api/user")
    public Authentication getUser(Authentication authentication) {
        LOGGER.info("resource: user {}", authentication);
        return authentication;
    }

}
