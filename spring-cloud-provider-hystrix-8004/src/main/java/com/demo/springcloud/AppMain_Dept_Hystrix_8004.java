package com.demo.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:31
 * _@Desc:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //开启对 Hystrix 的支持
public class AppMain_Dept_Hystrix_8004 {

	public static void main(String[] args) {
		SpringApplication.run(AppMain_Dept_Hystrix_8004.class, args);
	}

	/**
	 * 用于发现 /hystrix.stream，避免访问时 404 错误
	 * SpringBoot 2.0 的 bug 之一
	 */
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}
}
