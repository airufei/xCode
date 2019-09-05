/**
 * Project Name:CooxinPro
 * File Name:MyHandlerExceptionResolver.java
 * Package Name:com.cn.cooxin.common
 * Date:2017年1月12日下午10:55:36
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:MyHandlerExceptionResolver (404统一处理)
 * Date:     2017年1月12日 下午10:55:36
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(MyHandlerExceptionResolver.class);
	  
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		 String uri = request.getRequestURI();  
         logger.error("异常的url是：" + uri, new Exception());  
           
        return new ModelAndView("error");  
	}

}

