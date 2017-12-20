/**
 * Project Name:CooxinPro
 * File Name:UserRequestInfo.java
 * Package Name:com.cn.cooxin.admin.entity
 * Date:2017年3月6日下午5:17:23
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.admin.entity;
/**
 * ClassName:UserRequestInfo 客户请求信息
 * Date:     2017年3月6日 下午5:17:23
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class UserRequestInfo {
 
	private String userbrowser;//浏览器
	private String requestUri;//请求资源路径
	private String allurl;//完整url
	private String url;//方法url
	private String ip;//请求IP
	private String trueIP;//请求真实IP
	private String Agent;//请求代理
	private String host;//客户主机
	private String port;//客户端口
	private String contextPath;//请求资源根路径
	private String params;//请求参数
	
	public String getUserbrowser() {
		return userbrowser;
	}
	public void setUserbrowser(String userbrowser) {
		this.userbrowser = userbrowser;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public String getAllurl() {
		return allurl;
	}
	public void setAllurl(String allurl) {
		this.allurl = allurl;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTrueIP() {
		return trueIP;
	}
	public void setTrueIP(String trueIP) {
		this.trueIP = trueIP;
	}
	public String getAgent() {
		return Agent;
	}
	public void setAgent(String agent) {
		Agent = agent;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}

