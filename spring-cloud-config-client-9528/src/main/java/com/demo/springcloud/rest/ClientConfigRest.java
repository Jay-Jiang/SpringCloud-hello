package com.demo.springcloud.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * _@Author: jianghj
 * _@Date: 2019.11.14 014 17:42
 * _@Desc: 测试从配置中心获取配置信息
 */
@RestController
public class ClientConfigRest {

	@Value("${spring.application.name}")
	private String applicationName;
	@Value("${eureka.client.service-url.defaultZone}")
	private String eurekaservers;
	@Value("${server.port}")
	private String port;

	@RequestMapping("/getConfig")
	public String getConfigInfo() {
		String str = "applicationName: " + applicationName + "\t" +
				"eurekaServers: " + eurekaservers + "\t" +
				"port: " + port;
		return str;
	}
}
