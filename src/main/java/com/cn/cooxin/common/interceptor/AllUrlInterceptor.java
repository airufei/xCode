/**
 * Project Name:CooxinPro
 * File Name:AllurlInterceptor.java
 * Package Name:com.cn.cooxin.common.interceptor
 * Date:2017年3月6日下午5:06:04
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cn.cooxin.common.redis.RedisClientService;
import com.cn.cooxin.util.NetworkUtil;

/**
 * ClassName:AllurlInterceptor
 * Date:     2017年3月6日 下午5:06:04
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class AllUrlInterceptor extends HandlerInterceptorAdapter{

	@Resource
	RedisClientService redisService;
	private final Logger log = LoggerFactory.getLogger(AllUrlInterceptor.class);
	String reqLogkey="reqLogkey_salkjdjqwoidjpoqjwpod9765439939123klalkak";
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
		if(requestUri!=null&&requestUri.contains(".html"))
		{
			String requestStr=NetworkUtil.getRqLog(request);
			log.info(requestStr);
			redisService.putToQueue(reqLogkey, requestStr);
		}
		return ret;
	}
	
}

