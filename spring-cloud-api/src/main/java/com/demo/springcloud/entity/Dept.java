package com.demo.springcloud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * _@Author: jianghj
 * _@Date: 2019.10.28 028 11:27
 * _@Desc: 构建实体- 部门（进行网络传输之前，必须实现序列化接口）
 */

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {

	private Long deptNo;
	private String deptName;
	private String dbName;

	public Dept(String deptName) {
		this.deptName = deptName;
	}

}
