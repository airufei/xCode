package com.cn.cooxin.util;

import java.util.ResourceBundle;


/**
 * 资源文件读取器
 * @author Administrator
 *
 */
public class ResourcesReaderUtil {

	public static ResourceBundle resourceBundleb = ResourceBundle
			.getBundle("setings");
	public static ResourceBundle jdbcBundleb = ResourceBundle
			.getBundle("mysql");
	
	/**
	 * getString:(根据配置文件节点获取配置文件中的值)
	 * @Author airufei
	 * @param str
	 * @return
	 */
	public static String getString(String str)
	{
		str=jdbcBundleb.getString(str);
		return str;
		
	}
	/**
	 * getString:(根据配置文件节点获取配置文件中的值)
	 * @Author airufei
	 * @param str
	 * @return
	 */
	public static String getStringValue(String str)
	{
		str=resourceBundleb.getString(str);
		return str;
		
	}
}
