package com.cn.cooxin.admin.entity;

import com.cn.cooxin.code.entity.BaseEntitys;

/**
 * 菜单管理Entity
 * 
 * @author airufei
 * @version 2017-02-11
 */
public class AdminMenu extends BaseEntitys {

	private static final long serialVersionUID = 1L;
	private String name; // 菜单名称

	private String url; // 菜单地址

	private int isbutton = (int) 0; // 是否按钮 :0不是按钮   1新增  2编辑  3删除  4查询
	
	private int level; // 菜单等级

	private Long fid; // 父级菜单ID
    
	private int isadmin; // 菜单所属系统   后端菜单 0 后端 1前端
	
	
	private long roleId;//角色ID 只用于查询参数
	
	public AdminMenu() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsbutton() {
		return isbutton;
	}

	public void setIsbutton(int isbutton) {
		this.isbutton = isbutton;
	}

	

	public int getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}
}