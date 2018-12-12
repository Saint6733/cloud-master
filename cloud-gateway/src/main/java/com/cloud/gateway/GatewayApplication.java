package com.cloud.gateway;

import com.cloud.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类名称：GatewayApplication<br>
 * 类描述：API网关，基于Gateway,Zuul等实现 <br>
 * 创建时间：2018年11月27日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@ComponentScan(basePackages = {"com.cloud.common","com.cloud.gateway"})
public class GatewayApplication {
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/services")
	public String serviceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("cloud-auth");
		if (list != null && list.size() > 0 ) {
			return list.get(0).getUri().toString();
		}
		return null;
	}


	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}



	@GetMapping("/fallback")
	public String fallbackApi(){

		return "Success ! Hystrix";
	}
}
