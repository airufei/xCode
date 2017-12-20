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
 * AdminRoleController(角色管理)
 * 
 * @author airufei
 * @version 2017-02-11
 */
@Controller
@RequestMapping(value = "/admin/adminRole")
@SuppressWarnings("all")
public class AdminRoleController {

	private static Logger logger = Logger.getLogger(AdminRoleController.class);
	@Autowired
	private IAdminRoleService adminRoleService;
	@Autowired
	private IRoleMenuService roleMenuService;

	/**
	 * getAdminRoleById:(根据id获取单条数据)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ModelAttribute
	public AdminRole getAdminRoleById(Long id) {
		AdminRole entity = null;
		if (id != null && id > 0) {
			entity = adminRoleService.getAdminRoleById(id);
		}
		if (entity == null) {
			entity = new AdminRole();
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
		return "admin/role/list";
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
		return "admin/role/list";
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
		Map parms = SpringMvcUtil.getPageMap(page,pageSizeStr);// 设置分页参数
		Partion pt = adminRoleService.getList(parms);
		Object list = null;
		int totalCount = 0;
		if (pt != null) {
			list = pt.getList();
			totalCount = pt.getPageCount();
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
		String pagestr = page+"";
		String pageSizeStr = request.getParameter("rows");
		Map parms = SpringMvcUtil.getPageMap(pagestr,pageSizeStr);// 设置分页参数
		Partion pt = adminRoleService.getList(parms);
		SpringMvcUtil.setdataModel(model, pt);
		return "admin/role/list";
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
		AdminRole entity = null;
		if (id != null && id > 0) {
			entity = adminRoleService.getAdminRoleById(id);
		}
		if (entity == null) {
			entity = new AdminRole();
		}
		model.addAttribute("entity", entity);
		return "admin/role/edit";
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
		String msg = "删除成功";
		Map result = new HashMap();
		boolean res = true;
		try {
			if (id != null && id > 0) {
				adminRoleService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AdminRoleController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		} catch (Error e) {
			e.printStackTrace();
			logger.error("AdminRoleController:error===>" + e);
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
	public Map save(HttpServletRequest request, Model model) {
		String msg = "";
		Map result = new HashMap();
		boolean res = true;
		long id = 0;
		try {
			String name = request.getParameter("name");
			String remark = request.getParameter("remark");
			String mulist = request.getParameter("mulist");
			String idStr = request.getParameter("id");
		    id=StringUtil.StringToLong(idStr);
			Map mp=request.getParameterMap();
			if (StringUtil.isBlank(name)) {
				msg = "角色名称不能为空";
				res = false;
			}
			if (StringUtil.isBlank(mulist)) {
				msg = "权限不能为空";
				res = false;
			}
			if (res) {
				AdminRole adminRole = new AdminRole();
				adminRole.setName(name);
				if(id>0){
				  adminRole.setId(id);
				}
				adminRole.setRemark(remark);
				if (res) {
					adminRoleService.add(adminRole);
					id = adminRole.getId();// 获取主键值
					if (id > 0) {
						saveRoleAndMeun(mulist, id);
						msg="保存成功";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AdminRoleController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		} catch (Error e) {
			e.printStackTrace();
			logger.error("AdminRoleController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

	/**
	 * getTree:(获取菜单-树结构数据)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTree")
	public List<Tree> getTree(Long roleId, HttpServletRequest request,
			Model model) {
		String msg = "";
		Map result = new HashMap();
		boolean res = true;
		List<Tree> list = null;
		try {
			if (roleId != null) {
				list = roleMenuService.getTreeList(roleId.longValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AdminMenuController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		} catch (Error e) {
			e.printStackTrace();
			logger.error("AdminMenuController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
		}
		if (list == null) {
			new ArrayList<Tree>();
		}
		return list;
	}

	/**
	 * saveRoleAndMeun:(保存菜单和角色关系数据)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean saveRoleAndMeun(String meunList, long roleId) {
		boolean res = true;
		long id = 0;
		String[] arr = meunList.split(",");
		if (res) {
			for (int i = 0; i < arr.length; i++) {
				RoleMenu mu = new RoleMenu();
				mu.setMenuid(StringUtil.StringToLong(arr[i]));
				mu.setRoleid(roleId);
				roleMenuService.add(mu);
			}
		}
		return res;
	}
}