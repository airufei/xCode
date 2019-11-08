/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.cooxin.code.dao.IGenSchemeDao;
import com.cn.cooxin.code.dao.IGenTableColumnDao;
import com.cn.cooxin.code.dao.IGenTableDao;
import com.cn.cooxin.code.entity.GenScheme;
import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.GenTableColumn;
import com.cn.cooxin.code.entity.GenTemplate;
import com.cn.cooxin.code.entity.GenConfig;
import com.cn.cooxin.code.entity.Partion;
import com.cn.cooxin.util.GenUtils;
import com.cn.cooxin.util.StringUtil;


/**
 * 生成方案Service
 *
 * @version 2013-10-15
 */
@Service
@SuppressWarnings("all")
public class GenSchemeService {

	@Autowired
	private IGenSchemeDao GenSchemeDao;
	@Autowired
	private IGenTableDao GenTableDao;
	@Autowired
	private IGenTableColumnDao GenTableColumnDao;
	
	private static Logger logger = LoggerFactory.getLogger(GenSchemeService.class);
	
	public Partion getList(Map mp) {
		GenUtils.getTemplatePath();
		int totalcount=GenSchemeDao.getTotalCount(mp);
		List<GenScheme> list=null;
		if(totalcount>0){
			list=GenSchemeDao.getList(mp);
		}
		Partion pt=new Partion(mp,list, totalcount);
		return pt;
	}
	
	@Transactional(readOnly = false)
	public String save(GenScheme GenScheme) {
		if(GenScheme==null)
		{
			return "";
		}
		if (GenScheme.getId()<1){
			GenSchemeDao.add(GenScheme);
		}else{
			GenSchemeDao.updateById(GenScheme);
		}
		// 生成代码
		if (GenScheme.getFlag()==0){
			GenScheme.setReplaceFile(true);
			return generateGen(GenScheme);
		}
		return "";
	}
	
	
	private String generateGen(GenScheme GenScheme){

		StringBuilder result = new StringBuilder();
		String functionNameSimple = GenScheme.getFunctionNameSimple();
		String functionName = GenScheme.getFunctionName();
		if(StringUtil.isBlank(functionNameSimple))
		{
			GenScheme.setFunctionNameSimple(functionName);
		}

		// 查询主表及字段列
		GenTable GenTable = GenTableDao.getGenTableById(GenScheme.getTableId());
		GenTableColumn col=new GenTableColumn();
		col.setTableName(GenTable.getName());
		List<GenTableColumn> list=GenTableColumnDao.getList(col);
		int queryFieldCount =GenTableColumnDao.getQueryFieldCount(col);
		int editFieldCount =GenTableColumnDao.getEditFieldCount(col);
		// 获取所有代码模板
		GenConfig config = GenUtils.getConfig();
		
		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, GenScheme.getCategory(), false);
		List<GenTemplate> childTableTemplateList = GenUtils.getTemplateList(config, GenScheme.getCategory(), true);
		
		// 生成主表模板代码
		GenScheme.setTableId(GenTable.getId());
		GenScheme.setTableName(GenTable.getName());//getIsNotBaseField 这个字段在GenTableColumn类中
		Map<String, Object> model = GenUtils.getDataModel(GenScheme,GenTable,list,queryFieldCount,editFieldCount);
		for (GenTemplate tpl : templateList){
			logger.info("tpl================"+tpl.getName());
			List<String> li=tpl.getCategoryList();
			for (String st : li){
				logger.info("st================"+st);
			}
			boolean res=GenScheme.getReplaceFile();
			String roPath="sys";
			String moduleName=StringUtil.lowerCase(GenScheme.getModuleName());
			String str=GenUtils.generateToFile(tpl, model,res,roPath,moduleName);
			result.append(str);
		}
		return result.toString();
	}


	public GenScheme getSchemeById(long id) {
		return GenSchemeDao.getSchemeById(id);
	}
	
	public void deleteById(GenScheme sc) {
		GenSchemeDao.deleteById(sc);
	}
	
}
