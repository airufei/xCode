/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.dao;

import java.util.List;
import java.util.Map;

import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.Partion;

/**
 * 业务表DAO接口
 *
 * @version 2013-10-15
 */
@SuppressWarnings("all")
public interface IGenTableDao {

	List<GenTable> getTableList(GenTable table);

	void add(GenTable genTable);

	void updateById(GenTable genTable);

	GenTable getGenTableById(long id);

	List<GenTable> getList(Map map);
	
	int getTotalCount(Map map);

	List<GenTable> findAllList(GenTable genTable);

	/**
	 * getTable:(获取配置表信息)
	 * @Author airufei getTable
	 * @param table
	 * @return
	 */
	GenTable getTable(GenTable table);

	/**
	 * deletedById:(删除配置表信息)
	 * @Author airufei
	 * @param id
	 */
	void deletedById(Long id);
	
}
