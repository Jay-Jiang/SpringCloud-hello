package com.demo.rule;

import com.demo.springcloud.bean.MyRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * _@Author: jianghj
 * _@Date: 2019.11.4 004 16:22
 * _@Desc: SpringCloud D 版本的方式，自定义负载均衡算法
 */
@Configuration
public class DeptRibbonConfig {

	@Bean("myRule")
	public IRule customRule() {
		return new MyRule();
	}


}
