/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.cn.cooxin.code.entity.GenConfig;
import com.cn.cooxin.code.entity.GenScheme;
import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.Partion;
import com.cn.cooxin.code.service.GenSchemeService;
import com.cn.cooxin.code.service.GenTableService;
import com.cn.cooxin.util.GenUtils;
import com.cn.cooxin.util.SpringMvcUtil;
import com.cn.cooxin.util.StringUtil;

/**
 * 生成方案Controller
 * 
 * @author ThinkGem
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "/admin/codeScheme")
@SuppressWarnings("all")
public class GenSchemeController {

	private static Logger logger = Logger.getLogger(GenSchemeController.class);
	@Autowired
	private GenSchemeService genSchemeService;

	@Autowired
	private GenTableService genTableService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		return "admin/code/list";
	}

	@RequestMapping("")
	public String indexs(HttpServletRequest request, Model model) {
		return "admin/code/list";
	}

	@ResponseBody
	@RequestMapping(value = { "list", "" })
	public JSONObject list(GenScheme genScheme, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JSONObject result = new JSONObject();
		String page = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		Map parms = SpringMvcUtil.getPageMap(page, pageSizeStr);// 设置分页参数
		Partion pt = genSchemeService.getList(parms);
		result.put("rows", pt.getList());
		result.put("total", pt.getTotalCount());
		return result;
	}

	@RequestMapping(value = "edit")
	public String getEditDat(GenScheme genScheme, Model model) {
		String packageName = genScheme.getPackageName();
		String subPackageName = genScheme.getSubPackageName();
		String moduleName = genScheme.getModuleName();
		if (StringUtil.isBlank(packageName)) {
			genScheme.setPackageName("com.cn.cooxin");
		}
		if(StringUtil.isNoneBlank(subPackageName))
		{
			genScheme.setSubPackageName(subPackageName.trim());
		}
		if(StringUtil.isNoneBlank(moduleName))
		{
			genScheme.setModuleName(moduleName.trim());
		}
		long id = genScheme.getId();
		genScheme = genSchemeService.getSchemeById(id);
		GenTable table = new GenTable();
		List<GenTable> list = genTableService.getTableList(table);
		GenConfig con = GenUtils.getConfig();
		model.addAttribute("genScheme", genScheme);
		model.addAttribute("config", con);
		model.addAttribute("tableList", list);
		return "admin/code/edit";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public Map save(@RequestBody List<GenScheme> list, Model model,
			RedirectAttributes redirectAttributes) {
		String msg = "";
		Map result = new HashMap();
		boolean res = true;
		try {
			if (list == null) {
				res = false;
				msg = "无保存内容";
				result.put("result", res);
				result.put("msg", msg);
				return result;
			}
			GenScheme genScheme = list.get(0);
			if (genScheme == null) {
				res = false;
				msg = "无保存内容";
				result.put("result", res);
				result.put("msg", msg);
				return result;
			}
			String packageName = genScheme.getPackageName();
			String subPackageName = genScheme.getSubPackageName();
			String moduleName = genScheme.getModuleName();
			if (StringUtil.isBlank(packageName)) {
				genScheme.setPackageName("com.cn.cooxin");
			}
			if(StringUtil.isNoneBlank(subPackageName))
			{
				genScheme.setSubPackageName(subPackageName.trim());
			}
			if(StringUtil.isNoneBlank(moduleName))
			{
				genScheme.setModuleName(moduleName.trim());
			}
			String subName = genScheme.getSubModuleName();
			if (subName != null && subName.length() > 0) {
				genScheme.setFlag(0);
				String resMsg = genSchemeService.save(genScheme);
				msg = "操作生成方案'" + genScheme.getName() + "'成功<br/>" + result;
				res = true;
			} else {
				res = false;
				msg = "访问路径不能为空！";
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.error("GenTableController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;

		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

	/**
	 * delete:(删除方案-物理删除)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleted")
	public Map delete(Long id, HttpServletRequest request, Model model) {
		String msg = "删除成功";
		Map result = new HashMap();
		boolean res = true;
		try {
			if (id != null && id > 0) {
				GenScheme sc=new GenScheme();
				sc.setId(id);
				genSchemeService.deleteById(sc);
			} else {
				msg = "请选择需要删除的数据";
				res = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("GenTableController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {
			e.printStackTrace();
			logger.error("GenTableController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

}
