package com.cn.cooxin.admin.entity;

import com.cn.cooxin.code.entity.BaseEntitys;

/**
 * 日志管理Entity
 * 
 * @author airufei
 * @version 2017-02-25
 */
public class BaseLog extends BaseEntitys {

	private static final long serialVersionUID = 1L;
	private String content; // 日志内容

	private String logtype; // 日志类型

	private String rqurl;//请求路径
	public BaseLog() {

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public String getRqurl() {
		return rqurl;
	}

	public void setRqurl(String rqurl) {
		this.rqurl = rqurl;
	}
	
}