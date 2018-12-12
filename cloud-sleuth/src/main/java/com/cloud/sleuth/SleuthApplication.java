package com.cloud.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称：SleuthApplication<br>
 * 类描述：分布式链路跟踪，基于Zipkin/pinpoint等实现 <br>
 * 创建时间：2018年11月27日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.cloud.common"})
@RestController
public class SleuthApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SleuthApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(){
		return "hello sleuth";
	}
}
