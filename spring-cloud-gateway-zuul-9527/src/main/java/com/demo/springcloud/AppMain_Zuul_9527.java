package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:31
 * _@Desc: 开启网关 Zuul
 */
@SpringBootApplication
@EnableZuulProxy
public class AppMain_Zuul_9527 {

	public static void main(String[] args) {
		SpringApplication.run(AppMain_Zuul_9527.class, args);
	}


	/**
	 * 通过注入以下 bean ，实现路由的正则匹配
	 * servicePattern：匹配的请求地址
	 * routePattern：转化成的路由地址
	 * 如：将 rest-demo-v1 映射为 /v1/rest-demo/**
	 */

	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		/**
		 * A RegExp Pattern that extract needed information from a service ID. Ex :
		 * "(?<name>.*)-(?<version>v.*$)"
		 */
		//private Pattern servicePattern;
		/**
		 * A RegExp that refer to named groups define in servicePattern. Ex :
		 * "${version}/${name}"
		 */
		//private String routePattern;
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
	}
}
