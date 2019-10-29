package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.29 029 11:45
 * _@Desc:
 */
@SpringBootApplication
@EnableEurekaServer
public class AppMain_Eureka_Server_7001 {

	public static void main(String[] args) {
		SpringApplication.run(AppMain_Eureka_Server_7001.class, args);
	}
}
