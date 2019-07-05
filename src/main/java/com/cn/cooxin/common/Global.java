/**
 * Project Name:CooxinPro
 * File Name:Global.java
 * Package Name:com.cn.cooxin.common
 * Date:2017年1月15日下午5:41:49
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.DefaultResourceLoader;
import com.cn.cooxin.util.ResourcesReaderUtil;
import com.cn.cooxin.util.StringUtil;
import com.google.common.collect.Maps;


/**
 * ClassName:Global (全局配置类)
 * Date:     2017年1月15日 下午5:41:49
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	


	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = ResourcesReaderUtil.getStringValue(key);
			map.put(key, value != null ? value : StringUtil.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	
	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

	

    /**
     *存储静态文件文件的文件夹
     * @return
     */
    public static String getHtmlFilePath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String htmlPath = Global.getConfig("staticFile");
		File file = new File(htmlPath);
		if(!file.isDirectory())
		{
			file.mkdirs();
		}
		return htmlPath;
    }
    /**
     *存储子站文件文件的文件夹
     * @return
     */
    public static String getWebSiteHtmlFilePath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String htmlPath = Global.getConfig("website");
		File file = new File(htmlPath);
		if(!file.isDirectory())
		{
			file.mkdirs();
		}
		return htmlPath;
    }
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtil.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {

		}
		return projectPath;
    }
}

