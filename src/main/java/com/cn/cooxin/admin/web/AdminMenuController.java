/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.admin.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.cooxin.util.SpringMvcUtil;
import com.cn.cooxin.util.StringUtil;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.service.*;
import com.cn.cooxin.code.entity.Partion;

/**
 * AdminMenuController(菜单管理)
 * 
 * @author airufei
 * @version 2017-02-11
 */
@Controller
@RequestMapping(value = "/admin/adminMenu")
@SuppressWarnings("all")
public class AdminMenuController {

	private static Logger logger = Logger.getLogger(AdminMenuController.class);
	@Autowired
	private IAdminMenuService adminMenuService;

	/**
	 * getAdminMenuById:(根据id获取单条数据)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ModelAttribute
	public AdminMenu getAdminMenuById(Long id) {
		AdminMenu entity = null;
		if (id != null && id > 0) {
			entity = adminMenuService.getAdminMenuById(id);
		}
		if (entity == null) {
			entity = new AdminMenu();
		}
		return entity;
	}

	/**
	 * index:(跳转列表页)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		List list = adminMenuService.getAdminMenuList(new AdminMenu());
		model.addAttribute("muList", list);
		return "admin/menu/list";
	}

	/**
	 * indexs:(跳转列表页)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String indexs(HttpServletRequest request, Model model) {
		List list = adminMenuService.getAdminMenuList(new AdminMenu());
		model.addAttribute("muList", list);
		return "admin/menu/list";
	}

	/**
	 * list:(查询列表数据)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public JSONObject list(HttpServletRequest request, Model model) {
		JSONObject result = new JSONObject();
		String page = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		String name = request.getParameter("name");
		String isadmin = request.getParameter("isadmin");
		Map parms = SpringMvcUtil.getPageMap(page,pageSizeStr);// 设置分页参数
		parms.put("name", name);
		parms.put("isadmin", isadmin);
		Partion pt = adminMenuService.getList(parms);
		Object list = null;
		int totalCount = 0;
		if (pt != null) {
			list = pt.getList();
			totalCount = pt.getTotalCount();
		}
		result.put("rows", list);
		result.put("total", totalCount);
		return result;
	}

	/**
	 * toIndex:(查询列表数据)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list/{page}")
	public String toIndex(@PathVariable Integer page,
			HttpServletRequest request, Model model) {
		String pageStr = page+"";
		String pageSizeStr = request.getParameter("rows");
		String name = request.getParameter("name");
		Map parms = SpringMvcUtil.getPageMap(pageStr,pageSizeStr);// 设置分页参数
		parms.put("name", name);
		Partion pt = adminMenuService.getList(parms);
		SpringMvcUtil.setdataModel(model, pt);
		return "admin/menu/list";
	}

	/**
	 * goEdit:(去往编辑页面-查询编辑所需数据)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String goEdit(Long id, HttpServletRequest request, Model model) {
		AdminMenu entity = null;
		if (id != null && id > 0) {
			entity = adminMenuService.getAdminMenuById(id);
		}
		if (entity == null) {
			entity = new AdminMenu();
		}
		List list = adminMenuService.getAdminMenuList(new AdminMenu());
		model.addAttribute("entity", entity);
		model.addAttribute("muList", list);
		return "admin/menu/edit";
	}

	/**
	 * delete:(删除数据-逻辑删除)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Long id, HttpServletRequest request, Model model) {
		String msg = "";
		Map result = new HashMap();
		boolean res = true;
		try {
			if (id != null && id > 0) {
				adminMenuService.delete(id);
			}
		} catch (Exception e) {

			logger.error("AdminMenuController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		} catch (Error e) {

			logger.error("AdminMenuController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

	

	/**
	 * save:(保存新增或者编辑的)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	public Map save(@RequestBody List<AdminMenu> list,
			HttpServletRequest request, Model model) {
		String msg = "";
		Map result = new HashMap();
		boolean res = true;
		try {
			if (list == null || (list != null && list.size() < 1)) {
				msg = "无保存内容";
				res = false;
			}
			AdminMenu adminMenu = list.get(0);
			// 无保存内容
			if (adminMenu == null) {
				msg = "无保存内容";
				res = false;
			}
			if (res) {
				adminMenuService.add(adminMenu);
				res = true;
				msg = "成功";
			}

		} catch (Exception e) {

			logger.error("AdminMenuController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		} catch (Error e) {

			logger.error("AdminMenuController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

}