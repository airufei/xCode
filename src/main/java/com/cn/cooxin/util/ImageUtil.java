/**
 * Project Name:CooxinPro
 * File Name:ImageUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年2月13日下午9:48:52
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;

/**
 * ClassName:ImageUtil (图像处理工具类) Date: 2017年2月13日 下午9:48:52
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
public class ImageUtil {
	private static Logger logger = Logger.getLogger(ImageUtil.class);

	public static String savepath = ResourcesReaderUtil.resourceBundleb
			.getString("piderpic"); // 图片地址
	
	public static String tempSavePath = ResourcesReaderUtil.resourceBundleb
			.getString("tempSavePath");// 临时图片路径
	
	public static String readpath = ResourcesReaderUtil.resourceBundleb
			.getString("readpic");// 读取图片路径
	/**
	 * getCode:(获取图片验证码-不带数字运算)
	 * 
	 * @Author airufei
	 * @param session
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getCode(HttpSession session, int width,
			int height) {
		Random random = new Random();
		// 绘制字符
		String strCode = "";
		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		// Graphics类的样式
		g.setColor(getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, width, height);
		// 绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(getRandColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			strCode = strCode + rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 28);
		}
		g.dispose();
		// 将字符保存到session中用于前端的验证
		session.setAttribute("strCode", strCode);
		return image;
	}

	/**
	 * getCode:(数字运算图形验证码)
	 * 
	 * @Author airufei
	 * @param session
	 * @param type
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param minNum
	 *            颜色最小值 值越大 颜色越浅
	 * @param maxNum
	 *            颜色最最大值 值越大 颜色越浅
	 * @return
	 */
	public static BufferedImage getCode(HttpSession session, int width, int height, int maxNum, int minNum) {

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.fillRect(0, 0, width, height);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(Color.WHITE);
		Random random = new Random();
		Font font = new Font("微软雅黑", Font.BOLD, 18);
		g.setFont(font);

		String[] ops = { "+", "-", "*", "=" };// 定义运算符
		int num1 = random.nextInt(10);// 生成第一个操作数
		int num2 = (random.nextInt(9) + 1); // 随机生成0~8之间的一个数+1，作为第二个操作数。因为有可能出现除法，所以第二个操作数不能为0。所以+1，使数在1~9之间。
		int op_num = random.nextInt(3);// 随机生成一个运算符数组中的下标，从而得到随机的一个运算符。这里是0~2之间一个随机值。因为3是等号
		String strRand2 = (String) ops[op_num];// 运算符号 //根据操作符判断是-的要避免负数
		if ("-".equals(strRand2)) {
			if (num1 < num2) {
				num1 = num2 + 1;
			}
		}
		String strRand1 = String.valueOf(num1);
		int red1 = getRandom(maxNum, minNum);
		int green1 = getRandom(maxNum, minNum);
		int blue1 = getRandom(maxNum, minNum);
		g.setColor(new Color(red1, green1, blue1)); // 画出第一个操作数
		g.drawString(strRand1, 13 * 0 + 6, 26);
		g.setColor(new Color(0, 0, 0)); // 画出操作运算符
		if ("+".equals(strRand2)) {
			strRand2 = "加";

		}
		if ("-".equals(strRand2)) {
			strRand2 = "减";
		}
		if ("*".equals(strRand2)) {
			strRand2 = "乘";

		}

		g.drawString(strRand2, 10 * 2 + 6, 26);
		String strRand3 = String.valueOf(num2);
		int red3 = getRandom(maxNum, minNum);
		int green3 = getRandom(maxNum, minNum);
		int blue3 = getRandom(maxNum, minNum);
		g.setColor(new Color(red3, green3, blue3)); // 画出第二个操作数
		g.drawString(strRand3, 12 * 3 + 16, 26);
		String strRand4 = (String) ops[3];
		int red4 = getRandom(maxNum, minNum);
		int green4 = getRandom(maxNum, minNum);
		int blue4 = getRandom(maxNum, minNum);
		g.setColor(new Color(red4, green4, blue4)); // 画出等号
		g.drawString(strRand4, 13 * 4 + 16, 26);

		int red5 = getRandom(maxNum, minNum);
		int green5 = getRandom(maxNum, minNum);
		int blue5 = getRandom(maxNum, minNum);
		g.setColor(new Color(red5, green5, blue5)); // 画出问号
		g.drawString("?", 17 * 4 + 16, 26);

		Integer randomCode = 0; // 由运算符的不同执行不同的运算，得到验证码结果值
		switch (op_num) {
		case 0:
			randomCode = num1 + num2;
			break;
		case 1:
			randomCode = num1 - num2;
			break;
		case 2:
			randomCode = num1 * num2;
			break;

		}
		logger.info("randomCode=================================>:"+ randomCode);
		session.setAttribute("strCode", randomCode);// 把运算符结果值set到session中，用于其他文件进行验证码校对
		g.dispose();
		return image;

	}


	/** 
	  * 判断文件是否为图片 
	  *  
	  * @param imgName 文件名 
	  * @return 检查后的结果 
	  * @throws Exception 
	  */ 
	public static boolean isPicture(String imgName)
	{
		boolean result=false;
		if(StringUtil.isBlank(imgName))
		{
			return result;
		}
		// 获得文件后缀名 
		String tmpName = imgName.substring(imgName.lastIndexOf(".") + 1,imgName.length());
		JSONObject json=new  JSONObject();
		json.put("jpg", "jpg");
		json.put("bmp", "bmp");
		json.put("png", "png");
		json.put("gif", "gif");
		json.put("jpeg", "jpeg");
		json.put("webp", "webp");
		if(json.containsKey(tmpName))
		{
			result=true;
		}
		return result;
		
	}


	/**
	 * getRandom:(获取随机数)
	 * 
	 * @Author airufei
	 * @param minNum
	 * @return
	 */
	public static int getRandom(int maxNum, int min) {
		if (maxNum > 255) {
			maxNum = 255;
		}
		if (min < 0) {
			min = 0;
		}
		Random random = new Random();
		int num = random.nextInt(150);
		if (min > num) {
			num = maxNum - num;
		}
		return num;
	}

	
	/**
	 * vilateCode:(验证码是否正确)
	 * 
	 * @Author airufei
	 * @param session
	 * @param code
	 * @return
	 */
	public static boolean vilateCode(HttpSession session, String code) {
		boolean isSame = false;
		if (session != null && !StringUtil.isBlank(code)) {
			String seCode = StringUtil.objectToString(session
					.getAttribute("strCode"));
			if (code.equals(seCode)) {
				isSame = true;
				session.removeAttribute("strCode");
			}
		}
		return isSame;
	}

	// 创建颜色
	public static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
