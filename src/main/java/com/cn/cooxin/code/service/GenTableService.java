/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.cooxin.code.dao.IGenDataBaseDictDao;
import com.cn.cooxin.code.dao.IGenTableColumnDao;
import com.cn.cooxin.code.dao.IGenTableDao;
import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.GenTableColumn;
import com.cn.cooxin.code.entity.Partion;
import com.cn.cooxin.util.GenUtils;
import com.cn.cooxin.util.StringUtil;

/**
 * 业务表Service
 * 
 * @author ThinkGem
 * @version 2013-10-15
 */
@SuppressWarnings("all")
@Service
public class GenTableService {

	@Autowired
	private IGenTableDao genTableDao;

	@Autowired
	private IGenDataBaseDictDao genDataBaseDictDao;

	public GenTable getGenTableById(long id) {
		GenTable genTable = genTableDao.getGenTableById(id);
		return genTable;
	}

	public GenTable getTable(GenTable table) {
		return genTableDao.getTable(table);

	}

	public Partion getList(Map map) {
		List<GenTable> list = genTableDao.getList(map);
		int totalcount = genTableDao.getTotalCount(map);
		Partion pt = new Partion(map, list, totalcount);
		return pt;
	}

	/**
	 * getTableList:(获取配置表集合数据)
	 * 
	 * @Author airufei
	 * @param table
	 * @return
	 */
	public List<GenTable> getTableList(GenTable table) {
		List<GenTable> retList = new ArrayList();
		List<GenTable> list = genTableDao.getTableList(table);
		if (list != null) {
			for (GenTable item : list) {
				String tableName = item.getName();
				String comments = item.getComments();
				if (StringUtil.isNoneBlank(comments)) {
					tableName = tableName + "-" + comments;
				}
				item.setName(tableName);
				retList.add(item);
			}
		}
		return retList;
	}

	public List<GenTable> findAll() {
		return genTableDao.findAllList(new GenTable());
	}

	/**
	 * 获取物理数据表列表
	 * 
	 * @param genTable
	 * @return
	 */
	public List<GenTable> findTableListFormDb(GenTable genTable) {
		List<GenTable> list = new ArrayList<GenTable>();
		List<GenTable> li = genDataBaseDictDao.getTableList(genTable);
		if (li != null) {
			for (GenTable tb : li) {

				String tableName = tb.getName();
				String comments = tb.getComments();
				if (StringUtil.isNoneBlank(comments)) {
					tableName = tableName + "-" + comments;
				}
				tb.setComments(tableName);
				list.add(tb);
			}
		}
		return list;
	}

	/**
	 * 验证表名是否可用，如果已存在，则返回false
	 * 
	 * @param genTable
	 * @return
	 */
	public boolean checkTableName(String tableName) {
		if (StringUtil.isBlank(tableName)) {
			return true;
		}
		GenTable genTable = new GenTable();
		genTable.setName(tableName);
		List<GenTable> list = genTableDao.getTableList(genTable);
		return list.size() == 0;
	}

	/**
	 * 获取物理数据表列表
	 * 
	 * @param genTable
	 * @return
	 */
	public GenTable getTableFormDb(GenTable genTable) {
		if (genTable == null) {
			return null;
		}
		// 如果有表名，则获取物理表
		if (StringUtil.isNotBlank(genTable.getName())) {
			List<GenTable> list = genDataBaseDictDao.getTableList(genTable);
			if (list.size() > 0) {

				// 如果是新增，初始化表属性
				if (genTable.getId() < 1) {
					genTable = list.get(0);
					// 设置字段说明
					if (StringUtil.isBlank(genTable.getComments())) {
						genTable.setComments(genTable.getName());
					}
					genTable.setClassName(StringUtil
							.toCapitalizeCamelCase(genTable.getName()));
				}

			}
		}
		return genTable;
	}

	@Transactional(readOnly = false)
	public void save(GenTable genTable) {
		if (genTable.getId() <= 0) {
			genTableDao.add(genTable);
		} else {
			genTableDao.updateById(genTable);
		}
	}

	public void deletedById(Long id) {
		
		genTableDao.deletedById(id);
	}

}
