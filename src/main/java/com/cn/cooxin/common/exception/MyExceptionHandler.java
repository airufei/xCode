/**
 * Project Name:CooxinPro
 * File Name:MyExceptionHandler.java
 * Package Name:com.cn.cooxin.common.exception
 * Date:2017年2月19日下午3:13:44
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cn.cooxin.util.NetworkUtil;
/**
 * ClassName:MyExceptionHandler (异常处理)
 * Date:     2017年2月19日 下午3:13:44
 * @Author   airufei
 * @Version  1.0
 * @see
 */
public class MyExceptionHandler implements HandlerExceptionResolver  {  
	private final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
	@Override  
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) { 
        // 根据不同错误转向不同页面  
		ModelAndView model=new ModelAndView();
		log.error("MyExceptionHandler====>错误信息："+ ex);
		String newsUrl = NetworkUtil.getSystemUrl(request);// 获取iP端口
		try {
			String requestStr=NetworkUtil.getRqLog(request);
			log.info("MyExceptionHandler====>错误以后跳转的新地址："+newsUrl+" =========》requestStr："+requestStr);
			response.sendRedirect(newsUrl);// 跳转静态网页
			log.info("error_500 跳转到首页面！");
			return null;
		} catch (IOException e) {
			log.error("toIndex===>" + e);
		}
		
		return model;
    } 
}

