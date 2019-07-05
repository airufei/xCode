/**
 * Project Name:CooxinPro
 * File Name:NetworkUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年2月13日下午4:49:33
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.util;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.cooxin.admin.entity.UserRequestInfo;

/**
 * ClassName:NetworkUtil Date: 2017年2月13日 下午4:49:33
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
public class NetworkUtil {

	/**
	 * Logger for this class
	 */
	private static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

	/**
	 * getDetailUrl:(获取详情静态页面的全路径地址)
	 * @Author airufei
	 * @param request
	 * @param id
	 * @return
	 */
	public static String getDetailUrl(HttpServletRequest request, String id) {
		String url = null;
		try {
			String readstaticFile = ResourcesReaderUtil.resourceBundleb.getString("readStaticFile");
			String webRoot = getSystemUrl(request);// 获取iP端口 项目名称
			url = webRoot + "/" + readstaticFile + "/detail/" + id + ".html";
		} catch (Exception e) {
			

			
		}
		return url;
	}
	
	/**
	 * getNewListUrl:(获取列表静态页面的全路径地址)
	 * @Author airufei
	 * @param request
	 * @param id
	 * @return
	 */
	public static String getNewListUrl(HttpServletRequest request, String page) {
		String url = null;
		try {
			String readstaticFile = ResourcesReaderUtil.resourceBundleb.getString("readStaticFile");
			String webRoot = getSystemUrl(request);// 获取iP端口 项目名称
			url = webRoot + "/" + readstaticFile + "/index_" + page + ".html";
		} catch (Exception e) {
			logger.error("getNewListUrl:(获取列表静态页面的全路径地址)===>"+e);

			
		}
		return url;
	}
	/**
	 * isExsitDetailFile:(判断详情静态页面文件是否存在)
	 * @Author airufei
	 * @param request
	 * @param id
	 * @return
	 */
	public static boolean isExsitDetailFile(HttpServletRequest request,String moduleName,  String id) {
		boolean isExist = false;
		try {
			String staticFile = ResourcesReaderUtil.resourceBundleb.getString("staticFile"); 
			if(StringUtil.isNotBlank(moduleName))
			{
				staticFile=staticFile+"/"+moduleName;
			}
			String path=staticFile+"/detail/"+id+".html";
			isExist = FileUtils.isExsitFile(path);
		} catch (Exception e) {

			logger.error("isExsitDetailFile:(判断详情静态页面文件是否存在)===>"+e);
		}
		return isExist;
	}
	
	/**
	 * isExsitMoiveDetailFile:(判断电影详情静态页面文件是否存在)
	 * @Author airufei
	 * @param request
	 * @param id
	 * @return
	 */
	public static boolean isExsitMoiveDetailFile(HttpServletRequest request, String id) {
		boolean isExist = false;
		try {
			String staticFile = ResourcesReaderUtil.resourceBundleb.getString("staticFile"); 
			String path=staticFile+"/detail/mv_"+id+".html";
			isExist = FileUtils.isExsitFile(path);
		} catch (Exception e) {

			logger.error("isExsitDetailFile:(判断详情静态页面文件是否存在)===>"+e);
		}
		return isExist;
	}
	
	/**
	 * deleteDetailFile:(删除详情静态页面文件)
	 * @Author airufei
	 * @param request
	 * @param id
	 * @return
	 */
	public static boolean deleteDetailFile(HttpServletRequest request, String id) {
		boolean isExist = false;
		try {
			String staticFile = ResourcesReaderUtil.resourceBundleb.getString("staticFile"); 
			String path=staticFile+"/detail/"+id+".html";
			isExist = FileUtils.isExsitFile(path);
		} catch (Exception e) {

			logger.error("deleteDetailFile:(删除详情静态页面文件)===>"+e);
		}
		return isExist;
	}

	/**
	 * isExsitIndexFile:(判断列表静态页面文件是否存在)
	 * @Author airufei
	 * @param request
	 * @param id
	 * @return
	 */
	public static boolean isExsitIndexFile(HttpServletRequest request,String moduleName,  String page) {
		boolean isExist = false;
		try {
			String staticFile = ResourcesReaderUtil.resourceBundleb.getString("staticFile"); 
			if(StringUtil.isNotBlank(moduleName))
			{
				staticFile=staticFile+"/"+moduleName;
			}
			String path=staticFile+"/index_"+page+".html";
			if("1".equals(page))
			{
				path=staticFile+"/index.html";
			}
			isExist = FileUtils.isExsitFile(path);
		} catch (Exception e) {

			logger.error("isExsitIndexFile:(判断列表静态页面文件是否存在)===>"+e);
		}
		return isExist;
	}

	/**
	 * deletIndexFile:(删除列表静态页面文件)
	 * @Author airufei
	 * @param request
	 * @param id
	 * @return
	 */
	public static boolean deletIndexFile(HttpServletRequest request, String page) {
		boolean isExist = false;
		try {
			String staticFile = ResourcesReaderUtil.resourceBundleb.getString("staticFile"); 
			String path=staticFile+"/index_"+page+".html";
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {

			logger.error("deletIndexFile:(删除列表静态页面文件)===>"+e);
		}
		return isExist;
	}
	
	/**
	 * getSystemUrl:(不带端口号)
	 * @Author airufei
	 * @param request
	 * @return
	 */
	public static String getSystemUrl(HttpServletRequest request) {
		String scheme = request.getScheme();
		int localPort = request.getServerPort();
		String serverName = request.getServerName();
		int nginxPort=StringUtil.StringToInt(ResourcesReaderUtil.resourceBundleb.getString("nginxPort"));
		String url = "";
		if(localPort!=nginxPort)
		{
			url = scheme + "://" + serverName + ":" + nginxPort+ request.getContextPath();
		}else
		{
			url = scheme + "://" + serverName + request.getContextPath();
		}
		
		
		return url;
	}

	/**
	 * getSystemUrl:(带端特定口号)
	 * @Author airufei
	 * @param request
	 * @return
	 */
	public static String getSystemUrl(HttpServletRequest request,int localPort ) {
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		logger.info("ip/domname:"+serverName);
		String url = "";
		url = scheme + "://" + serverName + ":" + request.getServerPort()
				+ request.getContextPath();
		return url;
	}
	
	/**
	 * getSystemUrl:(获取访问服务器的端口号IP-带端口号)
	 * @Author airufei
	 * @param request
	 * @param IsPort
	 * @return
	 */
	public static String getSystemUrl(HttpServletRequest request,boolean IsPort) {
		// 用于获取服务器IP 端口号 项目名
		int localPort = request.getServerPort();
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		logger.info("ip/domname:"+serverName);
		String url = "";
		if (localPort == 80) {
			url = scheme + "://" + serverName + request.getContextPath();
		} else if(IsPort) {
			url = scheme + "://" + serverName + ":" + request.getServerPort()
					+ request.getContextPath();
		}
		return url;
	}
	
	public static String getRqLog(HttpServletRequest request) {
		String requestStr="";
		try {
			String requestUri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String url = requestUri.substring(contextPath.length());
			String allurl = request.getRequestURL().toString();// 获得客户端发送请求的完整url
			String ip = request.getRemoteAddr();// 返回发出请求的IP地址
			String params = request.getQueryString();// 返回请求行中的参数部分
			String host = request.getRemoteHost();// 返回发出请求的客户机的主机名
			int port = request.getRemotePort();// 返回发出请求的客户机的端口号。
			
			if(allurl!=null&&allurl.length()>600)
			{
				allurl=allurl.substring(0,500);
			}
			String agent = request.getHeader("User-Agent");
			StringTokenizer st = new StringTokenizer(agent, ";");
			st.nextToken();
			String trueIP = NetworkUtil.getIpAddress(request);
			// 得到用户的浏览器名
			String userbrowser = st.nextToken();
			UserRequestInfo ur=new UserRequestInfo();
			ur.setAgent(agent);
			ur.setUrl(url);
			ur.setAllurl(allurl);
			ur.setContextPath(contextPath);
			ur.setHost(host);
			ur.setIp(ip);
			ur.setTrueIP(trueIP);
			ur.setPort(port+"");
			ur.setUserbrowser(userbrowser);
			ur.setParams(params);
		    requestStr=JsonUtil.ObjectToJson(ur);
		} catch (IOException e) {
			logger.info("====>getRqLog:"+e);
		}
		return requestStr;
	}
	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request)
			throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isInfoEnabled()) {
			logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip="
					+ ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip="
							+ ip);
				}
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
}
