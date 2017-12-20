/**
 * Project Name:CooxinPro
 * File Name:Interceptor.java
 * Package Name:com.cn.cooxin.common.interceptor
 * Date:2017年2月13日下午4:00:40
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cn.cooxin.admin.entity.BaseUser;
import com.cn.cooxin.util.ResourcesReaderUtil;
import com.cn.cooxin.util.StringUtil;

/**
 * ClassName:Interceptor 后台系统拦截，如果不登陆，则不能进入到系统内容
 *  Date: 2017年2月13日 下午4:00:40
 * @Author airufei
 * @Version 1.0
 * @see
 */
public class Interceptor extends HandlerInterceptorAdapter {

	private final Logger log = LoggerFactory.getLogger(Interceptor.class);

	boolean isFilterAdmin=StringUtil.StringToBoolean(ResourcesReaderUtil.resourceBundleb.getString("isFilterAdmin"));
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean ret = true;
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (url != null && url.contains("admin")&&!url.contains("jpg")&&!url.contains("png")&&!url.contains("jpeg")&&!url.contains("gif")) {
			BaseUser user = (BaseUser) request.getSession().getAttribute("CooxinUser");
			boolean isFilterUrl = NoFilterUrl(url);
			if (user == null && !isFilterUrl&&isFilterAdmin) {
				log.info("请求后台目录,没有登录：跳转到login页面！");
				 request.getRequestDispatcher("/WEB-INF/jsp/admin/login.jsp").forward(request, response);
				 ret = false;
			}
		}
		return ret;

	}

	/**
	 * NoFilterUrl:(是否存在不需要拦截的地址 )
	 * 
	 * @Author airufei
	 * @param url
	 * @return
	 */
	private boolean NoFilterUrl(String url) {
		boolean isFilterUrl = false;
		Map<String, Object> mp = NoFilterUrlMap();
		if (mp != null && StringUtil.isNotBlank(url)) {
			String metnod = url.replace("/", "").replace("admin", "").replace("baseUser", "");
			if (mp.containsKey(metnod)) {
				isFilterUrl = true;
			}
		}
		return isFilterUrl;
	}

	/**
	 * NoFilterUrlMap:(到admin后不需要过滤的地址集合)
	 * 
	 * @Author airufei
	 * @return
	 */
	private Map<String, Object> NoFilterUrlMap() {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("loginCooxin", "loginCooxin");
		mp.put("login", "login");
		mp.put("findPwd", "findPwd");
		mp.put("updatePwd", "updatePwd");
		mp.put("sendMails", "sendMails");
		mp.put("authCode", "authCode");//获取图像验证码
		mp.put("registerCooxin", "registerCooxin");//提交注册
		mp.put("register", "register");//去注册
		return mp;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
