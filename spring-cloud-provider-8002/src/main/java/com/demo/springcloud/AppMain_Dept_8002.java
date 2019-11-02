package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:31
 * _@Desc:
 */
@SpringBootApplication
@EnableEurekaClient
public class AppMain_Dept_8002 {

	public static void main(String[] args) {
		SpringApplication.run(AppMain_Dept_8002.class, args);
	}
}
