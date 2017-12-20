/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.admin.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.cooxin.util.ImageUtil;
import com.cn.cooxin.util.MD5Util;
import com.cn.cooxin.util.SendMailUtil;
import com.cn.cooxin.util.SpringMvcUtil;
import com.cn.cooxin.util.StringUtil;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.service.*;
import com.cn.cooxin.code.entity.Partion;

/**
 * BaseUserController(用户信息)
 * 
 * @author airufei
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "/admin/baseUser")
@SuppressWarnings("all")
public class BaseUserController {

	private static Logger logger = Logger.getLogger(BaseUserController.class);
	@Autowired
	private IBaseUserService baseUserService;

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
		return "admin/baseUser/list";
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
		return "admin/baseUser/list";
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
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String wechart = request.getParameter("wechart");
		String page = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		Map parms = SpringMvcUtil.getPageMap(page,pageSizeStr);// 设置分页参数
		parms.put("username", username);
		parms.put("email", email);
		parms.put("phone", phone);
		parms.put("wechart", wechart);
		Partion pt = baseUserService.getList(parms);
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
		Map parms = new HashMap();
		String str = request.getParameter("page");
		parms = SpringMvcUtil.getPageMap(page);// 设置分页参数
		Partion pt = baseUserService.getList(parms);
		SpringMvcUtil.setdataModel(model, pt);
		return "admin/baseUser/list";
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
		BaseUser entity = null;
		if (id != null && id > 0) {
			entity = baseUserService.getBaseUserById(id);
		}
		if (entity == null) {
			entity = new BaseUser();
		}
		model.addAttribute("entity", entity);
		return "admin/baseUser/edit";
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
				baseUserService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map save(@RequestBody List<BaseUser> list,
			HttpServletRequest request, Model model) {
		String msg = "保存成功";
		Map result = new HashMap();
		boolean res = true;
		int type = 1;
		try {
			if (list == null || (list != null && list.size() < 1)) {
				msg = "无保存内容";
				res = false;
			}
			BaseUser baseUser = list.get(0);
			// 无保存内容
			if (baseUser == null) {
				msg = "无保存内容";
				res = false;
			}
			if (res) {
				baseUser.setType(type);
				String password = MD5Util.getMD5(baseUser.getPassword());// MD5加密
				baseUser.setPassword(password);
				baseUserService.add(baseUser);
				res = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

	/**
	 * registerCooxin:(互联网用户注册或者编辑)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/registerCooxin", method = RequestMethod.POST)
	public Map register(@RequestBody List<BaseUser> list,
			HttpServletRequest request, Model model) {
		String msg = "注册成功";
		String mail = "";
		Map result = new HashMap();
		boolean res = true;
		int type = 0;
		try {
			if (list == null || (list != null && list.size() < 1)) {
				msg = "无注册内容";
				res = false;
			}
			BaseUser baseUser = list.get(0);
			HttpSession session = request.getSession();
			// 无注册内容
			if (baseUser == null) {
				msg = "无注册内容";
				res = false;
			}
			if (!ImageUtil.vilateCode(session, baseUser.getImgCode())) {
				msg = "验证码不正确";
				res = false;
			}
			boolean isLogin = false;
			result = vilateParms(baseUser, isLogin);
			res = StringUtil.objectToBoolean(result.get("result"));
			msg = StringUtil.objectToString(result.get("msg"));
			String password = MD5Util.getMD5(baseUser.getPassword());// MD5加密
			if (res) {
				BaseUser bs = new BaseUser();
				bs.setEmail(baseUser.getEmail());
				boolean isNext = baseUserService.isExsitBaseUser(bs);
				if (!isNext) {
					baseUser.setType(type);
					baseUser.setPassword(password);
					baseUserService.add(baseUser);
					res = true;
					mail = baseUser.getEmail();
				}else
				{
					res = false;
					msg = "账号已存在";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		result.put("mail", mail);
		return result;
	}

	/**
	 * login:(登录)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request, Model model) {
		String url="admin/login";
		HttpSession session = request.getSession();
		if(session!=null)
		{
			session.removeAttribute("CooxinUser");
		}
		return url;
	}
	/**
	 * login:(登录)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginCooxin", method = RequestMethod.POST)
	public Map loginCooxin(@RequestBody List<BaseUser> list,
			HttpServletRequest request, Model model) {
		String msg = "登录成功";
		Map result = new HashMap();
		boolean res = true;
		int type = 0;
		try {
			HttpSession session = request.getSession();
			if (list == null || (list != null && list.size() < 1)) {
				msg = "登录失败";
				res = false;
			}
			BaseUser baseUser = list.get(0);
			baseUser.setType(0);
			boolean isLogin = true;
			result = vilateParms(baseUser, isLogin);
			res = StringUtil.objectToBoolean(result.get("result"));
			msg = StringUtil.objectToString(result.get("msg"));
			if (res) {
				BaseUser user = baseUserService.getBaseUser(baseUser);
				if (user != null) {
					String dbPwd = user.getPassword();
					String psaaword = MD5Util.getMD5(baseUser.getPassword());
					if (psaaword.equals(dbPwd)) {
						session.setAttribute("CooxinUser", user);
						type = user.getType();
						msg = "登陆成功";
						res = true;
						// 登录成功；
					} else {
						msg = "用户密码不正确";
						res = false;
					}
				} else {
					msg = "用户不存在";
					res = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		result.put("type", type);
		return result;
	}

	/**
	 * toFindPwd:(跳转找回密码页)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/findPwd")
	public String toFindPwd(HttpServletRequest request, Model model) {
		String url = "admin/login";
		boolean res = true;
		int type = 0;
		try {
			String mailCode = request.getParameter("mailCode");
			if (StringUtil.isNoneBlank(mailCode)) {
				HttpSession session = request.getSession();
				String mail = (String) session.getAttribute(mailCode);
				if (StringUtil.isNoneBlank(mail)) {
					url = "admin/findpwd";
					model.addAttribute("mail", mail);
					session.removeAttribute(mailCode);
					session.removeAttribute("mailCode");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
		} catch (Error e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
		}
		return url;
	}

	/**
	 * toFindPwd:(跳转找回密码页)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public Map updatePwd(HttpServletRequest request, Model model) {
		String msg = "重置密码成功";
		Map result = new HashMap();
		boolean res = true;
		int type = 0;
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			BaseUser baseUser = new BaseUser();
			HttpSession session = request.getSession();
			baseUser.setEmail(email);
			baseUser.setPassword(password);
			boolean isLogin = false;
			result = vilateParms(baseUser, isLogin);
			res = StringUtil.objectToBoolean(result.get("result"));
			msg = StringUtil.objectToString(result.get("msg"));
			password = MD5Util.getMD5(baseUser.getPassword());// MD5加密
			if (res) {
				BaseUser base = baseUserService.getBaseUser(baseUser);
				if (base != null && base.getId() > 0) {
					baseUser.setType(type);
					baseUser.setPassword(password);
					baseUserService.updatePwd(baseUser);
					res = true;
				} else {
					msg = "账号不存在";
					res = false;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		return result;
	}

	/**
	 * sendMails:(发送邮件)
	 * 
	 * @Author airufei
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendMails", method = RequestMethod.POST)
	public Map sendMails(@RequestBody List<BaseUser> list,
			HttpServletRequest request, Model model) {
		String msg = "发送邮件成功";
		Map result = new HashMap();
		boolean res = true;
		int type = 0;
		try {
			HttpSession session = request.getSession();
			if (list == null || (list != null && list.size() < 1)) {
				msg = "发送邮件失败";
				res = false;
			}
			BaseUser baseUser = list.get(0);
			if (baseUser != null) {
				String email = baseUser.getEmail();
				String imgCode = baseUser.getImgCode();
				if (StringUtil.isBlank(email)) {
					msg = "邮箱不能为空";
					res = false;
				}
				if (!ImageUtil.vilateCode(session, imgCode)) {
					msg = "验证码错误";
					res = false;
				}
				if (!StringUtil.isEmail(email)) {
					msg = "账号(邮箱)格式不正确";
					res = false;
				}
				if (res) {
					BaseUser bs = new BaseUser();
					bs.setEmail(email);
					boolean isNext = baseUserService.isExsitBaseUser(bs);
					if (isNext) {
						int code = StringUtil.getRandNum(1000, 10000);
						String url = "http://localhost:8080/cooxin/admin/baseUser/findPwd?mailCode="
								+ code;
						String content = "<a href='" + url + "'>点击进行密码重置</a>";
						String subject = "找回密码";
						SendMailUtil.sendCommonMail(email, subject, content);
						model.addAttribute("mail", email);
						session.setAttribute(code + "", email);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		} catch (Error e) {
			e.printStackTrace();
			logger.error("BaseUserController:error===>" + e);
			msg = "服务器繁忙，请稍后再试";
			res = false;
		}
		result.put("result", res);
		result.put("msg", msg);
		result.put("type", type);
		return result;
	}

	/**
	 * getAuthCode:(获取验证码)
	 * 
	 * @Author airufei
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping("/authCode")
	public void getAuthCode(HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		try {
			int width = 120;
			int height = 37;
			int maxNum=9;
			int minNum=0;
			// 设置response头信息
			// 禁止缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			BufferedImage image = ImageUtil.getCode(session,width, height,maxNum,minNum);
			ImageIO.write(image, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
    
	/**
	 * vilateParms:(登录参数校验)
	 * 
	 * @Author airufei
	 * @param email
	 * @param psaaword
	 * @param imgCode
	 * @return
	 */
	private Map vilateParms(BaseUser u, boolean isLogin) {
		String msg = "成功";
		Map result = new HashMap();
		boolean res = true;
		String email = u.getEmail();
		String uname = u.getUsername();
		String re = u.getUsername();
		String psaaword = u.getPassword();
		int type = u.getType();
		String imgCode = u.getImgCode();
		if (StringUtil.isBlank(email)) {
			msg = "账号(邮箱)不能为空";
			res = false;
		}
		if (StringUtil.isBlank(psaaword)) {
			msg = "密码不能为空";
			res = false;
		}
		if (StringUtil.isBlank(imgCode) && type == 0 && !isLogin) {
			msg = "验证码不能为空";
			res = false;
		}
		if (!StringUtil.isEmail(email)) {
			msg = "账号(邮箱)格式不正确";
			res = false;
		}
		if (!StringUtil.isBlank(uname) && (email.length() > 20)) {
			msg = "昵称长度超出限制（30位以内）";
			res = false;
		}
		if (!StringUtil.isBlank(email)
				&& (email.length() > 30 || email.length() < 5)) {
			msg = "账号(邮箱)长度不正确(邮箱：5-30位)";
			res = false;
		}
		if (!StringUtil.isBlank(psaaword)
				&& (psaaword.length() > 16 || psaaword.length() < 6)) {
			msg = "密码长度不正确(密码：6-16位)";
			res = false;
		}

		result.put("result", res);
		result.put("msg", msg);
		return result;
	}
}