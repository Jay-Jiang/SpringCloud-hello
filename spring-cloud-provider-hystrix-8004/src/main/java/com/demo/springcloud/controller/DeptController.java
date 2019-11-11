package com.demo.springcloud.controller;

import com.demo.springcloud.entity.Dept;
import com.demo.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:24
 * _@Desc: RESTful 风格的请求路径
 */
@RestController
public class DeptController {

	@Autowired
	DeptService deptService;


	@RequestMapping(value = "/dept/get/{deptNo}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDeptByHystrix")
	public Dept getDept(@PathVariable("deptNo") Long deptNo) {

		Dept dept = deptService.getDept(deptNo);
		if (dept == null) {
			throw new RuntimeException("[deptNo=" + deptNo + "]没有对应的信息，查询结果为空。");
		}

		return dept;
	}

	/**
	 * 异常后，Hystrix 会自动调用此方法，返回一个提示性的结果
	 * 此 FallBack 方法的入参，应该和原方法保持一致
	 */
	public Dept getDeptByHystrix(@PathVariable("deptNo") Long deptNo) {

		return new Dept().setDeptNo(deptNo)
				.setDeptName("HystrixCommand：[deptNo=" + deptNo + "]没有对应的信息，查询结果为空。")
				.setDbName("Not find the database in mysql");
	}

	/**
	 * 用于服务发现
	 */
	//@Autowired
	//private DiscoveryClient client;
	//@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	//public Boolean addDept(@RequestBody Dept dept) {
	//	return deptService.addDept(dept);
	//}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptService.listDept();
	}
	//
	//@RequestMapping(value = "/dept/delete/{deptNo}", method = RequestMethod.DELETE)
	//public Boolean deleteDept(@PathVariable("deptNo") Long deptNo) {
	//	return deptService.deleteDept(deptNo);
	//}

	//@RequestMapping(value = "/dept/get/services", method = RequestMethod.GET)
	//public DiscoveryClient getServices() {
	//
	//	List<String> list = client.getServices();
	//	System.out.println("**********" + list);
	//
	//	List<ServiceInstance> srvList = client.getInstances("PROVIDER-DEPT");
	//	for (ServiceInstance element : srvList) {
	//		System.out.println(
	//				element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
	//	}
	//	return client;
	//}
}
