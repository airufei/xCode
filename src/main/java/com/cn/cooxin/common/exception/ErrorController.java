/**
 * Project Name:CooxinPro
 * File Name:GlobalController.java
 * Package Name:com.cn.cooxin.common.exception
 * Date:2017年2月19日上午10:47:56
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.common.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import com.cn.cooxin.code.entity.Partion;
import com.cn.cooxin.util.NetworkUtil;
import com.cn.cooxin.util.SpringMvcUtil;

/**
 * ClassName:GlobalController (请求异常统一处理) Date: 2017年2月19日 上午10:47:56
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
@Controller
@RequestMapping(value = "/error")
@SuppressWarnings("all")
public class ErrorController {

	private final Logger log = LoggerFactory.getLogger(ErrorController.class);


	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/error_500")
	public void error_500(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String newsUrl = NetworkUtil.getSystemUrl(request);// 获取iP端口
		try {
			response.sendRedirect(newsUrl);// 跳转静态网页
			log.info("error_500 跳转到首页面！");
			return;
		} catch (IOException e) {
			log.error("toIndex===>" + e);
		}

	}

	@RequestMapping(value = "/error_404")
	public String error_404(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String url404 = "/error/404";
		int type = 1;// hot 推荐
		Map parms = new HashMap();
		parms = SpringMvcUtil.getPageMap(1);// 设置分页参数
		String hotkey = "detail_jaskkjasdlkasd404pagelljsjdkaksdlkaskd" + type;
		return url404;

	}

}
