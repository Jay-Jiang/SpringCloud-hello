package com.demo.springcloud.controller;

import com.demo.springcloud.entity.Dept;
import com.demo.springcloud.service.DeptConsumerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 16:24
 * _@Desc: RESTful 风格的消费
 */
@RestController
public class DeptConsumerController {

	@Resource
	DeptConsumerService deptConsumerService;

	@RequestMapping(value = "/consumer/dept/add", method = RequestMethod.POST)
	public Boolean addNewDept(@RequestBody Dept dept) {
		return deptConsumerService.addDept(dept);
	}

	@RequestMapping(value = "/consumer/dept/get/{deptNo}", method = RequestMethod.GET)
	public Dept getDept(@PathVariable("deptNo") Long deptNo) {
		return deptConsumerService.getDept(deptNo);
	}

	@RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptConsumerService.listDept();
	}

	@RequestMapping(value = "/consumer/dept/delete/{deptNo}", method = RequestMethod.DELETE)
	public Boolean deleteDept(@PathVariable("deptNo") Long deptNo) {
		return deptConsumerService.deleteDept(deptNo);
	}
}
