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

	/**
	 * 注入 Dept 服务的访问地址
	 */
	@Value("${dept.url.preffix}")
	private void setDeptUrlPreffix(String deptUrlPreffix) {
		DEPT_URL_PREFFIX = deptUrlPreffix;
	}

	/**
	 * 注入 RestTemplate 实例
	 */
	@Bean("restTemplate")
	@LoadBalanced  //开启负载均衡，默认使用轮询算法
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * 指定当前 Ribbon 采用的全局负载均衡算法
	 * 注入到 Spring 容器中（如果没有指定，则采用默认的轮询算法）
	 */
	//@Bean("defaultRule")
	//public IRule runningRule() {
	//	//轮询算法
	//	return new RoundRobinRule();
	//	//随机算法
	//	//return new RandomRule();
	//	//响应时间加权算法
	//	//return new WeightedResponseTimeRule();
	//	//return new RetryRule();
	//	//return new MyRule();
	//}
}
