package com.cloud.cloudauthresource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 类名称：OAuth2ResourceServer<br>
 * 类描述：<br>
 * 创建时间：2018年12月03日<br>
 *
 * @author 王芳
 * @version 1.0.0
 */
@Configuration
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .requestMatchers()
                .antMatchers("/api/**");
    }
}
