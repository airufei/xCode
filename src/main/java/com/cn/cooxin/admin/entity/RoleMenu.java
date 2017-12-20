package com.cn.cooxin.admin.entity;

import com.cn.cooxin.code.entity.BaseEntitys;

/**
 * 角色菜单Entity
 * @author airufei
 * @version 2017-02-11
 */
public class RoleMenu extends BaseEntitys {
	
	private static final long serialVersionUID = 1L;
	private Long roleid;		// 角色名称
			
	private Long menuid;		// 菜单ID
			
	
	
	public RoleMenu() {
		
	}


			
         public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
			
         public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}
}