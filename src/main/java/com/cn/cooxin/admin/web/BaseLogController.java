package com.cn.cooxin.admin.web;

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
 * BaseLogController(日志管理)
 * 
 * @author airufei
 * @version 2017-02-25
 */
@Controller
@RequestMapping(value = "/admin/log")
@SuppressWarnings("all")
public class BaseLogController {

	private static Logger logger = Logger.getLogger(BaseLogController.class);
	@Autowired
	private IBaseLogService baseLogService;

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
		return "/admin/baseLog/list";
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
		return "/admin/baseLog/list";
	}

	/**
	 * list:(查询列表数据----后台管理)
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
		String content = request.getParameter("content");
		String logtype = request.getParameter("logtype");

		String page = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		Map parms = SpringMvcUtil.getPageMap(page, pageSizeStr);// 设置分页参数
		parms.put("content", content);
		parms.put("logtype", logtype);
		Partion pt = baseLogService.getList(parms);
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
	 * toIndex:(查询列表数据---互联网端)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list/{page}")
	public String toIndex(@PathVariable Integer page,
			HttpServletRequest request, Model model) {
		Map parms = new HashMap();
		String str = request.getParameter("page");
		parms = SpringMvcUtil.getPageMap(page);// 设置分页参数
		Partion pt = baseLogService.getList(parms);
		SpringMvcUtil.setdataModel(model, pt);
		return "/admin/baseLog/list";
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
		BaseLog entity = null;
		try {
			if (id != null && id > 0) {
				entity = baseLogService.getBaseLogById(id);
			}
		} catch (Exception e) {

			logger.error("BaseLogController:error===>" + e);

		} catch (Error e) {

			logger.error("BaseLogController:error===>" + e);
		}
		if (entity == null) {
			entity = new BaseLog();
		}
		model.addAttribute("entity", entity);
		return "/admin/baseLog/edit";
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
				baseLogService.delete(id);
			} else {
				msg = "请选择需要删除的数据";
				res = false;
			}
		} catch (Exception e) {

			logger.error("BaseLogController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {

			logger.error("BaseLogController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
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
	public Map save(@RequestBody List<BaseLog> list,
			HttpServletRequest request, Model model) {
		String msg = "保存成功";
		Map result = new HashMap();
		boolean res = true;
		try {
			if (list == null || (list != null && list.size() < 1)) {
				msg = "无保存内容";
				res = false;
			}
			BaseLog baseLog = list.get(0);
			// 无保存内容
			if (baseLog == null) {
				msg = "无保存内容";
				res = false;
			}
			if (res) {
				baseLogService.add(baseLog);
				res = true;
			}

		} catch (Exception e) {

			logger.error("BaseLogController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {

			logger.error("BaseLogController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

}