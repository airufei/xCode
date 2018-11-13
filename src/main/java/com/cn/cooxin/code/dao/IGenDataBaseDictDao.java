/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.dao;

import java.util.List;

import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.GenTableColumn;


/**
 * 业务表字段DAO接口
 *
 * @version 2013-10-15
 */
public interface IGenDataBaseDictDao {

	/**
	 * 查询表列表
	 * @param genTable
	 * @return
	 */
	List<GenTable> getTableList(GenTable genTable);

	/**
	 * 获取数据表字段
	 * @param genTable
	 * @return
	 */
	List<GenTableColumn> getTableColumnList(GenTableColumn Column);
	
	/**
	 * 获取数据表主键
	 * @param genTable
	 * @return
	 */
	List<String> getTablePK(GenTableColumn Column);

	
}
