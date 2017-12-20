package com.cn.cooxin.admin.entity;

import com.cn.cooxin.code.entity.BaseEntitys;

/**
 * 角色管理Entity
 * 
 * @author airufei
 * @version 2017-02-11
 */
public class AdminRole extends BaseEntitys {

	private static final long serialVersionUID = 1L;
	private String name; // 角色名称

	public AdminRole() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}