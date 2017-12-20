/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.admin.entity;

import com.cn.cooxin.code.entity.BaseEntitys;


/**
 * 用户角色Entity
 * @author airufei
 * @version 2017-01-18
 */
public class UserRole extends BaseEntitys {
	
	private static final long serialVersionUID = 1L;
	private Long roleid;		// 角色名称
	private Long userid;		// 用户id

	public UserRole() {

	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}