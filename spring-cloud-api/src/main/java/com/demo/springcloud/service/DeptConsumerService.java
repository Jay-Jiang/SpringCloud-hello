package com.demo.springcloud.service;

import com.demo.springcloud.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.11.5 005 17:32
 *  按指定的服务名称，为该服务添加客户端负载均衡的接口（默认是轮询算法）
 *  其中，接口方法上，必须指定访问路径
 */
@FeignClient("PROVIDER-DEPT")
public interface DeptConsumerService {

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	Boolean addDept(Dept dept);

	@RequestMapping(value = "/dept/get/{deptNo}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	Dept getDept(Long deptNo);

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	List<Dept> listDept();

	@RequestMapping(value = "/dept/delete/{deptNo}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	Boolean deleteDept(Long deptNo);
}
