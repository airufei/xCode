/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.entity;

import com.cn.cooxin.util.StringUtil;


/**
 * 业务表Entity
 *
 * @version 2013-10-15
 */
public class GenTable extends BaseEntitys {
	
	private static final long serialVersionUID = 1L;
	private String name; 	// 表名称
	private String comments;		// 描述
	private String className;		// 实体类名称
	

	public GenTable() {
		super();
	}

	
	public String getName() {
		return StringUtil.lowerCase(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 获取列名和说明
	 * @return
	 */
	public String getNameAndComments() {
		return getName() + (comments == null ? "" : "  :  " + comments);
	}

	
	
}


