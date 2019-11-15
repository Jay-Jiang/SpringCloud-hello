package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * _@Author: jianghj
 * _@Date: 2019.11.14 014 17:44
 * _@Desc: 测试通过springcloud config 客户端，连接 Config 服务端，从 GitHub 获取指定的环境配置
 *
 * 注意：项目启动后，其uri中的端口号：是从远端获取的配置中的端口号
 */
@SpringBootApplication
public class AppMain_config_Client_9528 {
	public static void main(String[] args) {
		SpringApplication.run(AppMain_config_Client_9528.class, args);
	}
}
