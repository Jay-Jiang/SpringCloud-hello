package com.demo.springcloud.controller;

import com.demo.springcloud.config.ConfigBean;
import com.demo.springcloud.entity.Dept;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
	RestTemplate restTemplate;

	@RequestMapping(value = "/consumer/dept/add", method = RequestMethod.POST)
	public Boolean addNewDept(@RequestBody Dept dept) {
		return restTemplate.postForObject(ConfigBean.DEPT_URL_PREFFIX + "/dept/add", dept, Boolean.class);
	}

	@RequestMapping(value = "/consumer/dept/get/{deptNo}", method = RequestMethod.GET)
	public Dept getDept(@PathVariable("deptNo") Long deptNo) {
		return restTemplate.getForObject(ConfigBean.DEPT_URL_PREFFIX+"/dept/get/{1}", Dept.class, deptNo);
	}

	@RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return restTemplate.getForObject(ConfigBean.DEPT_URL_PREFFIX + "/dept/list", List.class);
	}

	@RequestMapping(value = "/consumer/dept/delete/{deptNo}", method = RequestMethod.DELETE)
	public Boolean deleteDept(@PathVariable("deptNo") Long deptNo) {
		restTemplate.delete(ConfigBean.DEPT_URL_PREFFIX + "/dept/delete/"+ deptNo);
		return true;
	}


}
