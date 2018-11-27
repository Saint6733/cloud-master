package com.cloud.sleuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称：SleuthApplication<br>
 * 类描述：分布式链路跟踪，基于Zipkin等实现 <br>
 * 创建时间：2018年11月27日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SleuthApplication {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	/**
	 * 获取所有服务
	 */
	@RequestMapping("/services")
	public Object services() {
		return discoveryClient.getServices();
	}
	
	@RequestMapping("/home")
	public String home() {
		return "Hello World";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SleuthApplication.class,args);
	}
	
}
