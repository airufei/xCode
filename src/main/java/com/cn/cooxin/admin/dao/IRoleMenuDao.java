/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.admin.dao;
import com.cn.cooxin.admin.entity.RoleMenu;
import com.cn.cooxin.code.entity.Partion;

import java.util.List;
import java.util.Map;
/**
 * 角色菜单DAO接口
 * @author airufei
 * @version 2017-02-11
 */
@SuppressWarnings("all")
public interface IRoleMenuDao {
	
	/**
	 * 删除数据（逻辑删除）
	 * @param question
	 * @return
	 */
	public void delete(long id);
    /**
	 * 单条数据增加
	 * @param roleMenu
	 * @return
	 */
	public void add(RoleMenu roleMenu);

    /**
	 * 批量数据增加
	 * @param roleMenu
	 * @return
	 */
	 public void addTrainRecordBatch(List<RoleMenu> list);

     /**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	 public RoleMenu getRoleMenuById (long id);

      /**
	 * 修改单条数据
	 * @param id
	 * @return
	 */
	  public void updateById(RoleMenu roleMenu);

	  /**
	   * 获取分页数据
	   * @param map
	   * @return
	   */
	   public List<RoleMenu>  getList(Map map);
	   
	   
	   /**
	   * 获取集合数据，不带分页
	   * @param map
	   * @return
	   */
	   public List<RoleMenu>  getRoleMenuList(RoleMenu roleMenu);
	   
	   /**
	   * 获取分页记录总数
	   * @param map
	   * @return
	   */
	   public int  getTotalCount(Map map);
	   
}