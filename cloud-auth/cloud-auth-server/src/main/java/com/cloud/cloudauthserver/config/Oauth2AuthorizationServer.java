package com.cloud.cloudauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 类名称：Oauth2AuthorizationServer<br>
 * 类描述：<br>
 * 创建时间：2018年12月01日<br>
 *
 * @author 王芳
 * @version 1.0.0
 */
@Configuration
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
   /* @Autowired
    private AuthenticationManager authenticationManager;*/

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /* 配置token获取合验证时的策略 */
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置oauth2的 client信息
        // authorizedGrantTypes 有4种，这里只开启2种
        // secret密码配置从 Spring Scurity 5.0开始必须以 {bcrypt}+加密后的密码 这种格式填写
        clients.inMemory()
                .withClient("testclient")
                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("testclient"))
                .redirectUris("http://127.0.0.1:9092/user","http://127.0.0.1:9092/login")
                .scopes("test").authorizedGrantTypes("authorization_code")
                //设置用户自动授权同意
                .autoApprove(true);
    }

   /* @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置tokenStore
        endpoints.authenticationManager(authenticationManager).tokenStore(memoryTokenStore());
    }
    // 使用最基本的InMemoryTokenStore生成token
    @Bean
    public TokenStore memoryTokenStore() {
        return new InMemoryTokenStore();
    }*/
}
