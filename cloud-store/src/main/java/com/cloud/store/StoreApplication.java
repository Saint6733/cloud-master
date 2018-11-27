package com.cloud.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名称：StoreApplication<br>
 * 类描述：非SQL存储，基于CouchDB，MongoDB等实现 <br>
 * 创建时间：2018年11月27日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@SpringBootApplication
public class StoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class,args);
	}
	
}
