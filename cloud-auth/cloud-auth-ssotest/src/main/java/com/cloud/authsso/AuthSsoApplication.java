package com.cloud.authsso;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称：authclient<br>
 * 类描述：sso 单点登录服务器<br>
 * 创建时间：2018年12月02日<br>
 *
 * @author 王芳
 * @version 1.0.0
 */
@EnableOAuth2Sso
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
@Slf4j
public class AuthSsoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthSsoApplication.class)
                .run(args);
    }
    // sso测试接口
    @GetMapping("/sso/user")
    public Authentication getUser(Authentication authentication) {
        LOGGER.info("auth : {}", authentication);
        return authentication;

    }
}