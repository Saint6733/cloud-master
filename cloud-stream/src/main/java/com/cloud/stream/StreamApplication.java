package com.cloud.stream;

import com.cloud.stream.kafka.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称：StreamApplication<br>
 * 类描述：消息服务Stream，基于多种MQ实现 <br>
 * 创建时间：2018年11月27日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class StreamApplication {

	@GetMapping("/stream/hello")
	public String hell(){
		return "Hello,World!";
	}
	public static void main(String[] args) {
		SpringApplication.run(StreamApplication.class,args);
	}
}
