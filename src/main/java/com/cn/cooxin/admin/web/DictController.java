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

import com.cn.cooxin.util.ImageUtil;
import com.cn.cooxin.util.SpringMvcUtil;
import com.cn.cooxin.util.StringUtil;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.service.*;
import com.cn.cooxin.code.entity.Dict;
import com.cn.cooxin.code.entity.Partion;

/**
 * DictController(字典数据)
 * 
 * @author airufei
 * @version 2017-02-26
 */
@Controller
@RequestMapping(value = "/admin/dict")
@SuppressWarnings("all")
public class DictController {

	private static Logger logger = Logger.getLogger(DictController.class);
	@Autowired
	private IDictService dictService;

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
		return "/admin/dict/list";
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
		return "/admin/dict/list";
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
		String typevalue = request.getParameter("typevalue");
		String typename = request.getParameter("typename");
		String fid = request.getParameter("fid");
		String page = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		Map parms = SpringMvcUtil.getPageMap(page, pageSizeStr);// 设置分页参数
		parms.put("typevalue", typevalue);
		parms.put("typename", typename);
		parms.put("fid", fid);
		Partion pt = dictService.getList(parms);
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
	 * comboxlist:(查询列表数据----后台管理)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/comboxlist")
	public List<Dict> comboxlist(HttpServletRequest request, Model model) {
		String typevalue = request.getParameter("typevalue");
		String fvalue=request.getParameter("fvalue");
		Dict d=new Dict();
		d.setFvalue(fvalue);
		if(StringUtil.isNoneBlank(typevalue))
		{
			d.setDictKey(typevalue);
		}
		List<Dict> list = dictService.getDictList(d);
		return list;
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
		Partion pt = dictService.getList(parms);
		SpringMvcUtil.setdataModel(model, pt);
		return "/admin/dict/list";
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
		Dict entity = null;
		try {
			if (id != null && id > 0) {
				entity = dictService.getDictById(id);
			}
		} catch (Exception e) {

			logger.error("DictController:error===>" + e);

		} catch (Error e) {

			logger.error("DictController:error===>" + e);
		}
		if (entity == null) {
			entity = new Dict();
		}
		model.addAttribute("entity", entity);
		return "/admin/dict/edit";
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
				dictService.delete(id);
			} else {
				msg = "请选择需要删除的数据";
				res = false;
			}
		} catch (Exception e) {

			logger.error("DictController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {

			logger.error("DictController:error===>" + e);
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
	public Map save(@RequestBody List<Dict> list, HttpServletRequest request,
			Model model) {
		String msg = "保存成功";
		Map result = new HashMap();
		boolean res = true;
		try {
			if (list == null || (list != null && list.size() < 1)) {
				msg = "无保存内容";
				res = false;
			}
			Dict dict = list.get(0);
			// 无保存内容
			if (dict == null) {
				msg = "无保存内容";
				res = false;
			}
			if (res) {
				String vaue=dict.getDictKey();
				Long fid=dict.getFid();
				if(StringUtil.isBlank(vaue))
				{
					vaue="dict_"+StringUtil.getRandom(100, 18888);
				}
				if(fid!=null&&fid>0)
				{
					Dict f_dict = dictService.getDictById(fid);
					if(f_dict!=null)
					{
						dict.setFvalue(f_dict.getDictKey());
					}
				}else
				{
					dict.setFid((long) -1);
				}
				dict.setDictKey(vaue);
				dictService.add(dict);
				res = true;
			}

		} catch (Exception e) {

			logger.error("DictController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {

			logger.error("DictController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

}