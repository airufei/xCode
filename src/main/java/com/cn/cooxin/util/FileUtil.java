/**
 * Project Name:CooxinPro
 * File Name:FileUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2016年7月13日上午11:12:10
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;


/**
 * ClassName:FileUtil(文件处理类)
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class);
	public static String tempSavePath = ResourcesReaderUtil.resourceBundleb.getString("tempSavePath");// 临时图片路径

	/**
	 * IsFtpUploadPic:(是否删除已经上传本地图片，默认false)
	 * 
	 * @Author airufei
	 * @return
	 */
	public static boolean isDeleteUploadPic() {
		boolean res = false;
		String isDeleteUploadPic = ResourcesReaderUtil.resourceBundleb.getString("isDeleteUploadPic"); // 是否开启同步任务
		if (StringUtil.isNoneBlank(isDeleteUploadPic)) {
			res = StringUtil.StringToBoolean(isDeleteUploadPic);
		}
		return res;

	}

	/**
	 * downloadPicture:(下载图片)
	 * 
	 * @Author airufei
	 * @param urlStr
	 *            下载地址
	 * @param savepath
	 *            存储路径
	 * @throws IOException
	 */
	public static boolean downloadPicture(String urlStr, String savepath) {
		boolean result = false;
		try {
			if (!StringUtil.IsUrl(urlStr)) {
				return result;
			}
			URL url = new URL(urlStr);
			File file = new File(tempSavePath);
			// 如果文件夹不存在则创建
			if (!file.isDirectory()) {
				file.mkdirs();
			}
			String imgName = "";
			Pattern p = Pattern.compile("(?<=^.+)[^/]+(?=$)");
			Matcher m = p.matcher(urlStr);
			if (m.find()) {
				imgName = m.group();
			}
			boolean ispic = ImageUtil.isPicture(imgName);
			if (!ispic) {
				return result;
			}
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
			String imageName = tempSavePath + imgName;
			FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
			byte[] buffer = new byte[1024];
			int length;
			while ((length = dataInputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, length);
			}
			dataInputStream.close();
			fileOutputStream.close();
			result = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			result = false;
			logger.error("下载地址：=====>" + urlStr + " ========>下載抓取图片出错：" + e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = false;
			logger.error("下载地址：=====>" + urlStr + " ========>下載抓取图片出错：" + e);
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
			logger.error("下载地址：=====>" + urlStr + " ========>下載抓取图片出错：" + e);
		}
		return result;

	}

	/**
	 * CreateDirectory:(根据路径创建)
	 * 
	 * @Author airufei
	 * @param path
	 */
	public static void CreateDirectory(String path) {
		if (StringUtil.isNotBlank(path)) {
			File file = new File(path);
			// 如果文件夹不存在则创建
			if (!file.isDirectory()) {
				file.mkdirs();
			}

		}

	}

	
	/**
	 * getFileList:(获取文件夹下面的所有文件)
	 * 
	 * @Author airufei
	 * @param strPath
	 * @return
	 */
	static List<File> filelist = new ArrayList<File>();

	public static List<File> getFileList(String strPath) {
		try {
			File dir = new File(strPath);
			File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) { // 判断是文件还是文件夹
						getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
					} else {
						filelist.add(files[i]);
					}
				}

			}
		} catch (Exception e) {
			logger.error("getFileList============ error====" + e);
			e.printStackTrace();
		}
		return filelist;
	}
}
