/**
 * Project Name:CooxinPro
 * File Name:SpringMvcUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年1月13日上午9:50:29
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

import com.cn.cooxin.code.entity.Partion;


/**
 * ClassName:SpringMvcUtil（MVC工具类）
 * Date:     2017年1月13日 上午9:50:29
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
@SuppressWarnings("all")
public class SpringMvcUtil {

	/**
	 * setdataModel:(给分页查询设置model（key/value）值)
	 * @Author airufei
	 * @param model
	 * @param pt
	 * @return
	 */
	public static void setdataModel(Model model, Partion pt) {
		model.addAttribute("list", pt.getList());
		model.addAttribute("isNext", pt.getIsNext());
		model.addAttribute("nevPage", pt.getNevPage());
		model.addAttribute("isNev", pt.getIsNev());
		model.addAttribute("nextPage", pt.getNextPage());
		model.addAttribute("pageCount", pt.getPageCount());
		model.addAttribute("currentCount", pt.getCurrentCount());
	}
	
	/**
	 * getPageMap:(当前页转化为MYSQL所需要的分页参数)
	 * @Author airufei
	 * @param currentNo 当前页
	 * @param pageSizeStr 每页记录数
	 * @return
	 */
	
	public static Map getPageMap(String currentNo,String pageSizeStr)
	{
		int currentCount = StringUtil.StringToInt(currentNo);
		int pageSize = StringUtil.StringToInt(pageSizeStr);
		Map map =new HashMap();
		if(pageSize<5)
		{
			 pageSize=10;
		}
		int startIndex=0;
		if(currentCount<=1)
		{
			currentCount=1;
			startIndex=0;
		}else
		{
			startIndex=(currentCount-1)*pageSize;
		}
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		map.put("currentCount", currentCount);
		map.put("sortWay", "desc");
		map.put("flag", 0);
		return map;
	}
	
	/**
	 * getPageMap:(当前页转化为MYSQL所需要的分页参数)
	 * @Author airufei
	 * @param pageStr
	 * @return
	 */
	
	public static Map getPageMap(int currentCount)
	{
		Map map =new HashMap();
		int	 pageSize=20;
		int startIndex=0;
		if(currentCount<=1)
		{
			currentCount=1;
			startIndex=0;
		}else
		{
			startIndex=(currentCount-1)*pageSize;
		}
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		map.put("currentCount", currentCount);
		map.put("sortWay", "desc");
		map.put("flag", 0);
		return map;
	}
}

