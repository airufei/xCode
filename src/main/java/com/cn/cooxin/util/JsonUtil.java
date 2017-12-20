package com.cn.cooxin.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JSON 工具类
 * @author Administrator
 *
 */
public class JsonUtil {

	/**
	 * 对象转JSON
	 * @param obj
	 * @return
	 */
	public static String ObjectToJson(Object obj) {
		String result = null;
		if (obj != null) {
			try {
				Gson gson = new GsonBuilder() .setDateFormat("yyyy-MM-dd HH:mm:ss").create();  
				result = gson.toJson(obj);
			} catch (Exception e) {
			}

		}
		return result;
	}
}
