/**
 * Project Name:CooxinPro
 * File Name:MyDispatchServlet.java
 * Package Name:com.cn.cooxin.common.servlet
 * Date:2017年3月1日下午3:44:05
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.common.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.UrlPathHelper;

import com.cn.cooxin.util.StringUtil;

/**
 * ClassName:MyDispatchServlet Date: 2017年3月1日 下午3:44:05
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
public class MyDispatchServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	private static final UrlPathHelper urlPathHelper = new UrlPathHelper();

	private String fileNotFondUrl = "";

	/**
	 * noHandlerFound:(映射controller出错---404信息)
	 * @Author airufei
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Override  
	protected void noHandlerFound(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bootUrl=request.getContextPath();
		String targetUrl=bootUrl + fileNotFondUrl;
		String errorRequestUri ="";//请求错误地址
		if (pageNotFoundLogger.isWarnEnabled()) {
			errorRequestUri= urlPathHelper.getRequestUri(request);
			pageNotFoundLogger
					.warn("No mapping found for HTTP request with URI ["
							+ errorRequestUri + "] in DispatcherServlet with name '"
							+ getServletName() + "'");
		}
		if(StringUtil.isNotBlank(errorRequestUri))
		{
			if(errorRequestUri.contains("news"))
			{
				targetUrl=bootUrl;
			}else if(errorRequestUri.contains("moive"))
			{
				targetUrl=bootUrl + "/moive/1.html";
			}
			else if(errorRequestUri.contains("tv"))
			{
				targetUrl=bootUrl + "/tv/1.html";
			}
			else if(errorRequestUri.contains("admin"))
			{
				targetUrl=bootUrl + "/admin/index";
			}
			
		}
		response.sendRedirect(targetUrl);
	}
	
	
	public String getFileNotFondUrl() {
		return fileNotFondUrl;
	}

	public void setFileNotFondUrl(String fileNotFondUrl) {
		this.fileNotFondUrl = fileNotFondUrl;
	}

}
