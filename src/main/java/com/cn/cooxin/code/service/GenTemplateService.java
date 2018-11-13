/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.cooxin.code.dao.IGenTemplateDao;
import com.cn.cooxin.code.entity.GenScheme;
import com.cn.cooxin.code.entity.GenTemplate;
import com.cn.cooxin.code.entity.Partion;
import com.cn.cooxin.util.GenUtils;


/**
 * 代码模板Service
 *
 * @version 2013-10-15
 */
@Service
@SuppressWarnings("all")
public class GenTemplateService {

	@Autowired
	private IGenTemplateDao genTemplateDao;
	
	public GenTemplate getGenTemplateById(long id) {
		return genTemplateDao.getGenTemplateById(id);
	}
	
	public Partion getList(Map mp) {
		GenUtils.getTemplatePath();
		List<GenTemplate> list=genTemplateDao.getList(mp);
		int totalcount=1;
		Partion pt=new Partion(mp,list, totalcount);
		return pt;
	}
	
	
	public void save(GenTemplate genTemplate) {
		if (genTemplate.getContent()!=null){
			genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
		}
		if (genTemplate.getId()<1){
			genTemplateDao.add(genTemplate);
		}else{
			genTemplateDao.updateById(genTemplate);
		}
	}
	
	public void delete(GenTemplate genTemplate) {
		genTemplateDao.deleteById(genTemplate);
	}
	
}
