package com.demo.springcloud.dao;

import com.demo.springcloud.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 15:02
 * _@Desc: @Mapper 注解不能丢
 */
@Mapper
public interface DeptDao {

	Boolean insertNewDept(Dept dept);

	Dept selectDeptById(Long deptNo);

	List<Dept> selectAllDepts();

	Boolean deleteDeptById(Long deptNo);

}
