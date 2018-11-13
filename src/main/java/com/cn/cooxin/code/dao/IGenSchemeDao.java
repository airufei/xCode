/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.dao;

import java.util.List;
import java.util.Map;

import com.cn.cooxin.code.entity.GenScheme;

/**
 * 生成方案DAO接口
 *
 * @version 2013-10-15
 */
@SuppressWarnings("all")
public interface IGenSchemeDao  {

	void add(GenScheme genScheme);

	void updateById(GenScheme genScheme);

	
	List<GenScheme> getList(Map mp);
	
	int getTotalCount(Map mp);

	void deleteById(GenScheme genScheme);

	GenScheme getSchemeById(long id);
	
}
