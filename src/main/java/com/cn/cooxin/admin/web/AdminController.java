package com.cn.cooxin.admin.web;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.cooxin.admin.entity.BaseUser;
import com.cn.cooxin.util.DateUtils;
import com.cn.cooxin.util.ResourcesReaderUtil;

/**
 * ClassName: AdminController(后天管理统一入口)
 * 
 * @Version 1.0
 * @Author: airufei date: 2017年1月18日 下午6:31:38
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request, Model model) {
		String sysName = ResourcesReaderUtil.resourceBundleb.getString("adminsysName"); //系统名称
		model.addAttribute("sysName", sysName);
		return "admin/index";
	}

	@RequestMapping("")
	public String index(HttpServletRequest request, Model model) {
		String sysName = ResourcesReaderUtil.resourceBundleb.getString("adminsysName"); //系统名称
		model.addAttribute("sysName", sysName);
		return "admin/index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		String mail = request.getParameter("mail");
		model.addAttribute("mail", mail);
		return "admin/login";
	}

	/**
	 * register:(跳转注册页)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest request, Model model) {
		return "admin/register";
	}

	/**
	 * sendMail:(跳转发送邮件页)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendMail")
	public String toSendMail(HttpServletRequest request, Model model) {
		return "admin/sendMail";
	}

	@RequestMapping("/menus")
	public String menu(HttpServletRequest request, Model model) {

		return "admin/menu";
	}

	@RequestMapping("/footer")
	public String footer(HttpServletRequest request, Model model) {
		return "admin/footer";
	}

	@RequestMapping("/header")
	public String header(HttpServletRequest request, Model model) {
		String sysName = ResourcesReaderUtil.resourceBundleb.getString("adminsysName"); //系统名称
		String today = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");// yyyy-mm-dd
		String week = DateUtils.getWeek();
		Object obj = request.getAttribute("CooxinUser");
		String name = "系统管理员";
		if (obj != null) {
			BaseUser user = (BaseUser) obj;
			if (user != null) {
				name = user.getUsername();
			}
		}
		model.addAttribute("week", week);
		model.addAttribute("today", today);
		model.addAttribute("sysName", sysName);
		model.addAttribute("userName", name);
		return "admin/header";
	}

}
