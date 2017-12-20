/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.dao;

import java.util.List;

import com.cn.cooxin.code.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
public interface IGenTableColumnDao  {
	
	public List<GenTableColumn> getList(GenTableColumn genTableColumn);

	public void add(GenTableColumn column);

	public void updateById(GenTableColumn column);

	public void deleteById(GenTableColumn column);

	/**
	 * getQueryFieldCount:(查询字段的个数)
	 * @Author airufei
	 * @param col
	 * @return
	 */
	public int getQueryFieldCount(GenTableColumn col);
	
	/**
	 * getQueryFieldCount:(编辑页字段的个数)
	 * @Author airufei
	 * @param col
	 * @return
	 */
	public int getEditFieldCount(GenTableColumn col);

	/**
	 * deleteColumnByTableId:(根据tableID删除方案生成)
	 * @Author airufei
	 * @param id
	 */
	public void deleteColumnByTableId(GenTableColumn col);
}
