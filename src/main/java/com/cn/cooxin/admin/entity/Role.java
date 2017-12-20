/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.admin.entity;

import com.cn.cooxin.code.entity.BaseEntitys;



/**
 * 角色信息Entity
 * @author airufei
 * @version 2017-01-18
 */
public class Role extends BaseEntitys {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 角色名称

	
	public Role() {
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}