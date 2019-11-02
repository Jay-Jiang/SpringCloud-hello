package com.demo.springcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 16:19
 * _@Desc: 将 Spring 封装的 RestTemplate 对象注入到 Spring 容器中。
 */
@Configuration
public class ConfigBean {

	public static String DEPT_URL_PREFFIX;

	@Value("${dept.url.preffix}")
	private void setDeptUrlPreffix(String deptUrlPreffix) {
		DEPT_URL_PREFFIX = deptUrlPreffix;
	}

	@Bean("restTemplate")
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
