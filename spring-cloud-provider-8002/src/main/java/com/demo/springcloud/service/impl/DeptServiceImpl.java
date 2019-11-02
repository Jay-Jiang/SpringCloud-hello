package com.demo.springcloud.service.impl;

import com.demo.springcloud.dao.DeptDao;
import com.demo.springcloud.entity.Dept;
import com.demo.springcloud.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:19
 * _@Desc:
 */
@Service
public class DeptServiceImpl implements DeptService {

	@Resource
	DeptDao deptDao;

	@Override
	public Boolean addDept(Dept dept) {
		return deptDao.insertNewDept(dept);
	}

	@Override
	public Dept getDept(Long deptNo) {
		return deptDao.selectDeptById(deptNo);
	}

	@Override
	public List<Dept> listDept() {
		return deptDao.selectAllDepts();
	}

	@Override
	public Boolean deleteDept(Long deptNo) {
		return deptDao.deleteDeptById(deptNo);
	}
}
