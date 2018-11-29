package com.cloud.cloudadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @author 冯亚鹏
 * @version	1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class CloudAdminApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CloudAdminApplication.class, args);
	}
}
