/**
 * Project Name:CooxinPro
 * File Name:AdminMenuTree.java
 * Package Name:com.cn.cooxin.admin.entity
 * Date:2017年2月14日下午10:47:41
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.admin.entity;

import java.util.List;

/**
 * ClassName:AdminMenuTree (当前用户的菜单树)
 * Date:     2017年2月14日 下午10:47:41
 * @param <prvaite>
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class AdminMenuTree {

	private AdminMenu mu;
	private List<AdminMenu> children;
	public AdminMenu getMu() {
		return mu;
	}
	public void setMu(AdminMenu mu) {
		this.mu = mu;
	}
	public List<AdminMenu> getChildren() {
		return children;
	}
	public void setChildren(List<AdminMenu> children) {
		this.children = children;
	}
	
}

