/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.admin.service;
import java.util.List;
import java.util.Map;

import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.code.entity.Partion;


/**
 * Service(菜单管理)
 * @author airufei
 * @version 2017-02-11
 */
 @SuppressWarnings("all")
public interface IAdminMenuService {
	
	/**
	 * 删除数据（逻辑删除）
	 * @param question
	 * @return
	 */
	public void delete(long id);
    /**
	 * 单条数据增加
	 * @param adminMenu
	 * @return
	 */
	public void add(AdminMenu adminMenu);

    /**
	 * 批量数据增加
	 * @param adminMenu
	 * @return
	 */
	 public void addTrainRecordBatch(List<AdminMenu> list);

     /**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	 public AdminMenu getAdminMenuById (long id);

     /**
	 * 修改单条数据
	 * @param id
	 * @return
	 */
	  public void updateById(AdminMenu adminMenu);

	  /**
	   * 获取分页数据
	   * @param map
	   * @return
	   */
	   public Partion  getList(Map map);
	   
	   /**
	   * 获取集合数据，不带分页
	   * @param map
	   * @return
	   */
	   public List<AdminMenu>  getAdminMenuList(AdminMenu adminMenu);
	   
	   /**
	   * 获取分页总数
	   * @param map
	   * @return
	   */
	   public int  getTotalCount(Map map);
	   
	   /**
		 * getTreeList:(获取完整树结构)
		 * @Author airufei
		 * @return
		 */
	   public List<AdminMenuTree> getMenuList(long roleId);
	
}