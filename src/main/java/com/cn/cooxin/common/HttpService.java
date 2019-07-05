/**
 * Project Name:CooxinPro
 * File Name:HttpService.java
 * Package Name:com.cn.cooxin.spider.domain
 * Date:2016年7月12日下午3:58:55
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.cn.cooxin.util.SSLClient;
import com.cn.cooxin.util.StringUtil;

/**
 * ClassName:HttpService 发起抓取数据请求
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
@SuppressWarnings("all")
public class HttpService {

	private static Logger logger = Logger.getLogger(HttpService.class);

	

	/**
	 * httpPost
	 * 
	 * @param url
	 *            路径
	 * @param jsonParam
	 *            参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		return httpPost(url, jsonParam, false);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            url地址
	 * @param jsonParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam,
			boolean noNeedResponse) {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(),
						"utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
				method.addHeader("Host", "www.zhihu.com");
				method.addHeader("Origin", "https://www.zhihu.com");
				method.addHeader(
						"Content-Security-Policy",
						"default-src *; img-src * data: blob:; frame-src 'self' *.zhihu.com getpocket.com note.youdao.com read.amazon.cn; script-src 'self' *.zhihu.com *.google-analytics.com res.wx.qq.com 'unsafe-eval'; style-src 'self' *.zhihu.com 'unsafe-inline'; connect-src * wss:;");
				method.addHeader(
						"Cookie",
						"aliyungf_tc=AQAAAEktu1lvngEATqrAdlz+FECjQQWT; q_c1=4e7f9228bc2b44a2a1d9baf045f00b8f|1484896358000|1484896358000; cap_id='ZTFlZDhiMDg1YWM0NGQ5ZTk3NzI4ODQ5YjZmZmZjOTE=|1484896358|5e2a42c291679e7509efe460a8e3c3d0700400ba'; l_cap_id='YmU1MjZhNDdlNzVjNGQxNjhkZDBjZmY3ZjYxODY1YWU=|1484896358|c4dad1189277c629a7a6641224c15b3748801914'; d_c0='AADCGb_BLguPTlr0iUhkIcIY1_ie9JiMdPo=|1484896358'; _xsrf=30cef6677b4d52c6799e9e34ed3fbb90; _zap=46e5d83f-81e8-4c7e-bc1b-a2fc6b0fef9c; login='MzZjODRlMzlkMzg1NGFjMGJlOGQ0OTA3MjdmMWQ2ZWQ=|1484896732|ef2d8e7fde91c9ef253944c2b722c55451747a21'; n_c=1; __utma=51854390.621994440.1484901035.1484901035.1484901035.1; __utmb=51854390.0.10.1484901035; __utmc=51854390; __utmz=51854390.1484901035.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=51854390.100--|2=registration_date=20110913=1^3=entry_date=20110913=1; z_c0=Mi4wQUFEQTlxMFlBQUFBQU1JWnY4RXVDeGNBQUFCaEFsVk4zRWFwV0FBWlpGTnNwdU9fUzRjMVRibTV1dTBNTDBCbmN3|1484902177|e1240231def06f353e2be30899295eb8e4bd3032");
				method.addHeader("Referer",
						"https://www.zhihu.com/explore/recommendations");
				method.addHeader("X-Xsrftoken",
						"30cef6677b4d52c6799e9e34ed3fbb90");
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					if (noNeedResponse) {
						return null;
					}
					/** 把json字符串转换成json对象 **/
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			request.addHeader("", "");
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity());
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.parseObject(strResult);
				url = URLDecoder.decode(url, "UTF-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static String httpGet(String url, int p) {
		// get请求返回结果
		String strResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				strResult = EntityUtils.toString(response.getEntity());
				/** 把json字符串转换成json对象 **/

				url = URLDecoder.decode(url, "UTF-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return strResult;
	}

	/**
	 * http请求
	 * 
	 * @param param
	 *            请求参数
	 * @param url
	 *            请求地址
	 * @return
	 */
	public static JSONObject HttpPost(List<NameValuePair> param, String url) {
		String Str = null;
		JSONObject jsonResult = null;
		HttpEntity entity = null;
		try {
			if (url != null) {
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				entity = new UrlEncodedFormEntity(param);
				post.setEntity(entity);
				HttpResponse response = httpclient.execute(post);
				if (response != null) {
					Str = EntityUtils.toString(response.getEntity());
					int resCode = response.getStatusLine().getStatusCode();
					if (resCode == 200) {
						if (Str != null) {
							jsonResult = JSONObject.parseObject(Str);
						}
					} else {
						throw new Exception(Str);
					}

				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("Post Exception:" + e+"=====url:"+url);
		} catch (ClientProtocolException e) {
			logger.error("Post Exception:" + e+"=====url:"+url);
		} catch (IOException e) {
			logger.error("Post Exception:" + e+"=====url:"+url);
		} catch (Exception e) {
			logger.error("Post Exception:" + e+"=====url:"+url);
		} catch (Error e) {
			logger.error("Post Exception:" + e+"=====url:"+url);
		}
		return jsonResult;
	}

	/**
	 * HttpsPost:(HTTPS请求)
	 * 
	 * @Author airufei
	 * @param url
	 * @param map
	 * @param charset
	 * @return
	 */
	public static JSONObject HttpsPost(String url, JSONObject map) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		JSONObject jsonResult = null;
		String charset = "utf-8";
		try {

			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator
						.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
					if (result != null) {
						jsonResult = JSONObject.parseObject(result);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Https Exception:" + ex+"=====url:"+url);
			ex.printStackTrace();
		} catch (Error ex) {
			logger.error("Https Exception:" + ex+"=====url:"+url);
			ex.printStackTrace();

		}
		return jsonResult;
	}

	
	/**
	 * httpsGet:(发送HTTPS get请求)
	 * @Author airufei
	 * @param url
	 * @return
	 */
	public static String httpsGet(String url) {
		String charset = "utf-8";
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {

			logger.error("Https Exception:" + e+"=====url:"+url);
		}

		return result;
	}

	
}
