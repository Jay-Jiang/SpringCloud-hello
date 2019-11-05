package com.demo.springcloud;

import com.demo.rule.DeptRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 17:35
 * _@Desc:
 */
@SpringBootApplication
@EnableEurekaClient
//为 PROVIDER-DEPT 服务，指定自定义的负载均衡算法
@RibbonClient(name = "PROVIDER-DEPT", configuration = DeptRibbonConfig.class)
public class AppMain_Consumer_80 {
	public static void main(String[] args) {
		SpringApplication.run(AppMain_Consumer_80.class, args);
	}
}
