package com.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 类名称：AuthApplication<br>
 * 类描述：权限认证，基于Oauth2等实现<br>
 * 创建时间：2018年11月27日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
		//new SpringApplicationBuilder(AuthApplication.class).run(args);
	}
}
