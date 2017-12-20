/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.GenTemplate;
import com.cn.cooxin.code.service.GenTemplateService;


/**
 * 代码模板Controller
 * @author ThinkGem
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "/gen/genTemplate")
@SuppressWarnings("all")
public class GenTemplateController {

	@Autowired
	private GenTemplateService genTemplateService;
	

	@ModelAttribute
	public GenTemplate get(@RequestParam(required=false) long id) {
		if (id<1){
			return genTemplateService.getGenTemplateById(id);
		}else{
			return new GenTemplate();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map mp = new HashMap();
		genTemplateService.getList(mp);
		return "modules/gen/genTemplateList";
	}
	@RequestMapping(value = "form")
	public String form(GenTemplate genTemplate, Model model) {
		model.addAttribute("genTemplate", genTemplate);
		return "modules/gen/genTemplateForm";
	}

	@RequestMapping(value = "save")
	public String save(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
		String msg= "保存代码模板'" + genTemplate.getName() + "'成功";
		genTemplateService.save(genTemplate);
		
		return "redirect:"  + "/gen/genTemplate/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
		genTemplateService.delete(genTemplate);
		String msg= "删除代码模板成功";
		return "redirect:"  + "/gen/genTemplate/?repage";
	}

}
