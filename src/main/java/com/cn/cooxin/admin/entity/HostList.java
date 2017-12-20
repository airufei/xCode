/**
 * Project Name:CooxinPro
 * File Name:HostList.java
 * Package Name:com.cn.cooxin.admin.entity
 * Date:2017年5月23日下午11:06:12
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.admin.entity;

import java.util.List;

/**
 * ClassName:HostList (用一句话描述这个变量表示什么)
 * Date:     2017年5月23日 下午11:06:12
 * @param <T>
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class HostList<T> {

	private List<T> relList;
	private List<T> hostList;
	public List<T> getRelList() {
		return relList;
	}
	public void setRelList(List<T> relList) {
		this.relList = relList;
	}
	public List<T> getHostList() {
		return hostList;
	}
	public void setHostList(List<T> hostList) {
		this.hostList = hostList;
	}
	
}

