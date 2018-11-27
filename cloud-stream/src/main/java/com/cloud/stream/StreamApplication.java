package com.cloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名称：StreamApplication<br>
 * 类描述：消息服务Stream，基于多种MQ实现 <br>
 * 创建时间：2018年11月27日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@SpringBootApplication
public class StreamApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StreamApplication.class,args);
	}
	
}
