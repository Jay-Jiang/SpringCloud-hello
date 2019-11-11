package com.demo.springcloud.service;

import com.demo.springcloud.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.11.5 005 17:32
 * 按指定的服务名称，为该服务添加客户端负载均衡的接口（默认是轮询算法）
 * 其中，接口方法Handler上，必须指定RESTful 访问路径，以及指明取值参数
 * 指定该服务统一的 FallBack 处理，方便实现服务降级处理。
 */
@FeignClient(name = "PROVIDER-DEPT", fallbackFactory = DeptServiceFallbackFactory.class)
public interface DeptConsumerService {

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	Boolean addDept(Dept dept);

	@RequestMapping(value = "/dept/get/{deptNo}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	Dept getDept(@PathVariable("deptNo") Long deptNo);

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	List<Dept> listDept();

	@RequestMapping(value = "/dept/delete/{deptNo}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	Boolean deleteDept(@PathVariable("deptNo") Long deptNo);
}
