package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 17:35
 * _@Desc:
 */
@SpringBootApplication
@EnableHystrixDashboard
public class AppMain_Consumer_Hystrix_Dashboard {
	public static void main(String[] args) {
		SpringApplication.run(AppMain_Consumer_Hystrix_Dashboard.class, args);
	}

	//如果访问服务提供者的 /hystrix.stream 路径，报错 404
	//需要在服务提供者项目启动类中，添加如下的 ServletRegistrationBean
	//@Bean
	//public ServletRegistrationBean getServlet() {
	//	HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
	//	ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
	//	registrationBean.setLoadOnStartup(1);
	//	registrationBean.addUrlMappings("/hystrix.stream");
	//	registrationBean.setName("HystrixMetricsStreamServlet");
	//	return registrationBean;
	//}
}
