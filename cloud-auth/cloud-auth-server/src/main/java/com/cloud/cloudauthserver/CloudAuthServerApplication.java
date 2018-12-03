package com.cloud.cloudauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 类名称：CloudAuthServerApplication<br>
 * 类描述：<br>
 * 创建时间：2018年12月01日<br>
 *
 * @author 王芳
 * @version 1.0.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAuthorizationServer
public class CloudAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudAuthServerApplication.class, args);
    }
}
