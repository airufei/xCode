/**
 * Project Name:CooxinPro
 * File Name:Tree.java
 * Package Name:com.cn.cooxin.admin.entity
 * Date:2017年2月11日下午12:21:19
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.admin.entity;

import java.util.List;

/**
 * ClassName:Tree (用一句话描述这个变量表示什么)
 * Date:     2017年2月11日 下午12:21:19
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class Tree {

	public long id;//基点id
	public String text ;//节点名称
    public String state="closed" ;//'open' 或 'closed'，默认是 'open' 如果为'closed'的时候，将不自动展开该节点。即点击该节点不会加下一个节点
    public boolean checked ;// 是否选中
    public Object attributes ;// 给一个节点添加的自定义属性。
    public List<Tree> children ; //子节点
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Object getAttributes() {
		return attributes;
	}
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
}

