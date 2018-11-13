/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.dao;

import java.util.List;
import java.util.Map;

import com.cn.cooxin.code.entity.GenTemplate;


/**
 * 代码模板DAO接口
 *
 * @version 2013-10-15
 */
@SuppressWarnings("all")
public interface IGenTemplateDao  {

	GenTemplate getGenTemplateById(long id);

	
	List<GenTemplate> getList(Map mp);

	void add(GenTemplate genTemplate);

	void updateById(GenTemplate genTemplate);

	void deleteById(GenTemplate genTemplate);
	
}
