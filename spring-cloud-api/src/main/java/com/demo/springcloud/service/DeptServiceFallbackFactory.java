package com.demo.springcloud.service;

import com.demo.springcloud.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * _@Author: jianghj
 * _@Date: 2019.11.11 011 14:09
 *
 * 通过实现 FallBackFactory 接口，为指定的服务调用，统一封装 FallBack 方法
 * 将FallBack 方法与主业务逻辑代码解耦
 * 注意：需要将此类注入到 Spring 容器中，否则无法正常进行加载使用
 */
@Component
public class DeptServiceFallbackFactory implements FallbackFactory<DeptConsumerService> {

	@Override
	public DeptConsumerService create(Throwable throwable) {
		return new DeptConsumerService() {
			@Override
			public Boolean addDept(Dept dept) {
				return false;
			}

			@Override
			public Dept getDept(Long deptNo) {
				return new Dept().setDeptNo(deptNo)
						.setDeptName("服务降级：DeptService 已经停止服务！")
						.setDbName("Not find the database in mysql");
			}

			@Override
			public List<Dept> listDept() {
				Dept dept = getDept(0L);
				ArrayList<Dept> depts = new ArrayList<>();
				depts.add(dept);
				return depts;
			}

			@Override
			public Boolean deleteDept(Long deptNo) {
				return false;
			}
		};
	}
}
