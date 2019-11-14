package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:31
 * _@Desc: SpringCloud Config 服务端
 */
@SpringBootApplication
@EnableConfigServer
public class AppMain_Config_9526 {

	public static void main(String[] args) {
		SpringApplication.run(AppMain_Config_9526.class, args);
	}
}
