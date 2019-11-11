package com.demo.springcloud.service;

import com.demo.springcloud.entity.Dept;

import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:18
 * _@Desc:
 */
public interface DeptService {

	Boolean addDept(Dept dept);

	Dept getDept(Long deptNo);

	List<Dept> listDept();

	Boolean deleteDept(Long deptNo);
}
