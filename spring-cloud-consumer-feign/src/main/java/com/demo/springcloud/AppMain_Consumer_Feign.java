package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 17:35
 * _@Desc:
 */
@SpringBootApplication
@EnableEurekaClient
//开启 Feign 客户端
@EnableFeignClients(basePackages = {"com.demo.springcloud"})
//扫描额外包路径（当前包及其子包以外的）
//@ComponentScan("com.demo.springcloud.service")
public class AppMain_Consumer_Feign {
	public static void main(String[] args) {
		SpringApplication.run(AppMain_Consumer_Feign.class, args);
	}
}
