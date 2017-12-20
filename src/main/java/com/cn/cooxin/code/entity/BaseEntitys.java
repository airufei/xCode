/**
 * Project Name:CooxinPro
 * File Name:BaseEntity.java
 * Package Name:com.cn.cooxin.pojo
 * Date:2017年1月15日下午3:37:22
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.code.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:BaseEntity (用一句话描述这个变量表示什么)
 * Date:     2017年1月15日 下午3:37:22
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class BaseEntitys implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String dbName="mysql"; 	// 数据库名称
	private long id;//数据主键Id
	private long userId;//用户id
	private Date createtime=new Date();//创建时间
	private Date updatetime=new Date();//修改时间
	private int flag=0;// 删除标记 0正常 1删除 2 暂不上线
	private String remark;// 备注
	private String sortWay="desc"; // 排序方式
	private String sortField="updatetime"; // 排序字段
	
	private String createtimestr;//创建时间
	private String updatetimestr;//修改时间
	public String getDbName() {
		return dbName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getCreatetimestr() {
		return createtimestr;
	}


	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}


	public String getUpdatetimestr() {
		return updatetimestr;
	}


	public void setUpdatetimestr(String updatetimestr) {
		this.updatetimestr = updatetimestr;
	}


	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}


	public String getSortWay() {
		return sortWay;
	}


	public void setSortWay(String sortWay) {
		this.sortWay = sortWay;
	}
	

}

