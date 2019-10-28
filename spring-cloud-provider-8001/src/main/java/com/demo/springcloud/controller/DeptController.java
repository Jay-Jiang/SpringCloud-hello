package com.demo.springcloud.controller;

import com.demo.springcloud.entity.Dept;
import com.demo.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:24
 * _@Desc: RESTful 风格的请求路径
 */
@RestController()
public class DeptController {

	@Autowired
	DeptService deptService;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public Boolean addDept(@RequestBody Dept dept) {
		return deptService.addDept(dept);
	}

	@RequestMapping(value = "/dept/get/{deptNo}", method = RequestMethod.GET)
	public Dept getDept(@PathVariable("deptNo") Long deptNo) {
		return deptService.getDept(deptNo);
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptService.listDept();
	}

	@RequestMapping(value = "/dept/delete/{deptNo}", method = RequestMethod.DELETE)
	public Boolean deleteDept(@PathVariable("deptNo") Long deptNo) {
		return deptService.deleteDept(deptNo);
	}
}
