/**
 * Project Name:CooxinPro
 * File Name:StringUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2016年7月6日下午1:47:14
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ClassName:StringUtil(字符串处理工具类) Date: 2016年7月6日 下午1:47:14
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";
	private static final int CPUTIME = 500;
	private static final int PERCENT = 100;
	private static final int FAULTLENGTH = 10;

	/**
	 * 字符串转数字
	 * 
	 * @param str
	 *            数字字符串
	 * @return
	 */
	public static int StringToInt(String str) {
		int result = 0;

		try {
			if (str != null && str.length() > 0) {
				result = Integer.parseInt(str.replace(",", ""));
			}
		} catch (NumberFormatException e) {

		}
		return result;

	}

	/**
	 * 字符串转数字
	 * 
	 * @param str
	 *            数字字符串
	 * @return
	 */
	public static long StringToLong(String str) {
		long result = 0;

		try {
			if (str != null && str.length() > 0) {
				result = Long.parseLong(str);
			}
		} catch (NumberFormatException e) {

		}
		return result;
	}

	/**
	 * 字符串转数字
	 * 
	 * @param str
	 *            数字字符串
	 * @return
	 */
	public static int objToInt(Object obj) {
		int result = 0;

		try {
			if (obj != null && obj.toString().length() > 0) {
				result = Integer.parseInt(obj.toString());
			}
		} catch (NumberFormatException e) {

		}
		return result;

	}

	/**
	 * isEmpty:(判断字符串是否为空)
	 * 
	 * @Author airufei
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * DateToString:(时间格式化)
	 * 
	 * @Author airufei
	 * @param str
	 * @return
	 */
	public static String dateToString(Date date) {
		String pat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormater = new SimpleDateFormat(pat);
		return dateFormater.format(date);

	}

	/**
	 * DateToString:(时间格式化)
	 * 
	 * @Author airufei
	 * @param str
	 * @return
	 */
	public static Date dateToDate(Date date) {
		String pat = "yyyy-MM-dd HH:mm:ss";
		Date date1 = null;
		try {
			String datestr = dateToString(date);
			SimpleDateFormat dateFormater = new SimpleDateFormat(pat);
			date1 = dateFormater.parse(datestr);
		} catch (ParseException e) {

		}
		return date1;

	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null || (val != null && val.toString().length() < 1)) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果不为空，则设置值
	 * 
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * 
	 * @param objectString
	 *            对象串 例如：row.user.id
	 *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + (val.substring(1)) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		if (str != null) {
			p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
			m = p.matcher(str);
			b = m.matches();
		}
		return b;
	}

	public static boolean isEmail(String email) {
		boolean isMail = false;
		if (!isBlank(email)) {
			// 电子邮件
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			isMail = matcher.matches();
		}
		return isMail;
	}

	/**
	 * getRandNum:(生成随机数)
	 * 
	 * @Author airufei
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static int getRandNum(int min, int max) {
		Random random = new Random();
		int num = random.nextInt(max);
		if (min > num) {
			num = max - min;
		}
		return num;
	}

	/**
	 * getRandNum:(生成随机数)
	 * 
	 * @Author airufei
	 * @param min
	 *            最小值
	 * @return
	 */
	public static int getRandNum(int max) {
		Random random = new Random();
		int num = random.nextInt(max);
		return num;
	}

	/**
	 * Print:(向页面输出信息)
	 * 
	 * @Author airufei
	 * @param response
	 * @param msg
	 */
	public static void PrintMsgToPage(HttpServletResponse response, String msg) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Charset", "utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch (IOException e) {

		}

	}

	/**
	 * ObjToString:(对象转字符串)
	 * 
	 * @Author airufei
	 * @param obj
	 * @return
	 */
	public static String objectToString(Object obj) {
		String resStr = "";
		if (obj != null) {
			resStr = obj.toString().trim();
		}
		return resStr;

	}

	/**
	 * ObjToString:(对象转数字)
	 * 
	 * @Author airufei
	 * @param obj
	 * @return
	 */
	public static int objectToInt(Object obj) {
		int resStr = 0;
		try {
			if (obj != null) {
				String str = obj.toString();
				if (str != null) {
					resStr = Integer.parseInt(str.trim());
				}
			}
		} catch (Exception e) {

		}
		return resStr;

	}

	/**
	 * StringtToInt:(字符串转数字)
	 * 
	 * @Author airufei
	 * @param obj
	 * @return
	 */
	public static int StringtToInt(String str) {
		int resStr = 0;
		try {

			if (str != null && str.length() > 0) {
				resStr = Integer.parseInt(str.trim());
			}

		} catch (Exception e) {

		}
		return resStr;

	}

	/**
	 * objectToBoolean:(对象转boolean)
	 * 
	 * @Author airufei
	 * @param obj
	 * @return
	 */
	public static boolean objectToBoolean(Object obj) {
		boolean resStr = false;
		try {
			if (obj != null) {
				resStr = Boolean.parseBoolean(obj.toString().trim());
			}
		} catch (Exception e) {

			resStr = false;
		}
		return resStr;

	}

	/**
	 * StringToBoolean:(字符串boolean)
	 * 
	 * @Author airufei
	 * @param obj
	 * @return
	 */
	public static boolean StringToBoolean(Object obj) {
		boolean resStr = false;
		try {
			if (obj != null && obj.toString().length() > 0) {
				resStr = Boolean.parseBoolean(obj.toString().trim());
			}
		} catch (Exception e) {

			resStr = false;
		}
		return resStr;

	}

	/**
	 * toJson:(转JSON数据格式)
	 * 
	 * @Author airufei
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		GsonBuilder gb = new GsonBuilder();
		Gson gson = gb.create();
		return gson.toJson(obj);
	}

	/**
	 * StrArryToString:(String 数组获取第一个元素值)
	 * 
	 * @Author airufei
	 * @param arry
	 * @return
	 */
	public static String StrArryToString(String[] arry) {
		String str = null;
		if (arry != null && arry.length > 0) {
			str = arry[0];
		}
		return str;
	}

	/**
	 * getUuId:(获取UUID 不带-)
	 * 
	 * @Author airufei
	 * @return
	 */
	public static String getUuId() {

		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * stringToUnicode:(汉字转Unicode码)
	 * 
	 * @Author airufei
	 * @param string
	 * @return
	 */
	public static String stringToUnicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * filterEmoji:(过来特殊字符)
	 * 
	 * @Author airufei
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("*");
				return source;
			}
			return source;
		}
		return source;
	}

	/**
	 * getCpuRatioForWindows:(获得cpu使用率)
	 * 
	 * @Author airufei
	 * @return
	 */
	public static String getCpuRatioForWindows() {
		try {
			String procCmd = System.getenv("windir") + "//system32//wbem//wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			// 取进程信息
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				return "CPU使用率:" + Double.valueOf(PERCENT * (busytime) * 1.0 / (busytime + idletime)).intValue() + "%";
			} else {
				return "CPU使用率:" + 0 + "%";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "CPU使用率:" + 0 + "%";
		}
	}

	// 获取文件系统使用率
	public static List<String> getDisk() {
		// 操作系统
		List<String> list = new ArrayList<String>();
		for (char c = 'A'; c <= 'Z'; c++) {
			String dirName = c + ":/";
			File win = new File(dirName);
			if (win.exists()) {
				long total = (long) win.getTotalSpace();
				long free = (long) win.getFreeSpace();
				Double compare = (Double) (1 - free * 1.0 / total) * 100;
				String str = c + ":盘 已使用 " + compare.intValue() + "%";
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * readCpu:(读取cpu相关信息)
	 * 
	 * @Author airufei
	 * @param proc
	 * @return
	 */
	private static long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < FAULTLENGTH) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}
				// 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
				// ThreadCount,UserModeTime,WriteOperation
				String caption = substring(line, capidx, cmdidx - 1).trim();
				String cmd = substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				String s1 = substring(line, kmtidx, rocidx - 1).trim();
				String s2 = substring(line, umtidx, wocidx - 1).trim();
				if (caption.equals("System Idle Process") || caption.equals("System")) {
					if (s1.length() > 0)
						idletime += Long.valueOf(s1).longValue();
					if (s2.length() > 0)
						idletime += Long.valueOf(s2).longValue();
					continue;
				}
				if (s1.length() > 0)
					kneltime += Long.valueOf(s1).longValue();
				if (s2.length() > 0)
					usertime += Long.valueOf(s2).longValue();
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * getExt:(获取文件后缀)
	 * 
	 * @Author airufei
	 * @param name
	 * @return
	 */
	public static String getExt(String name) {
		if (name == null || "".equals(name) || !name.contains("."))
			return "";
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * getFirstWords:(根据分隔符获取第一个单词)
	 * 
	 * @Author airufei
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String getFirstWords(String str, String reg) {
		String result = "";
		if (isBlank(str)) {
			return result;
		}
		if (isBlank(reg)) {
			reg = ",";
		}
		String[] array = str.split(reg);
		if (array == null) {
			array = str.split("、");
		}
		if (array == null) {
			array = str.split("，");
		}
		if (array == null) {
			array = str.split(" ");
		}
		if (array == null) {
			return result;
		}
		if (array.length > 0) {
			result = array[0];
		}
		return result;
	}

	/**
	 * getFiveWords:(根据分隔符获取前五个单词)
	 * 
	 * @Author airufei
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String[] getFiveWords(String str, String reg) {
		String[] array = null;
		if (isBlank(str)) {
			return array;
		}
		if (isBlank(reg)) {
			reg = ",";
		}
		array = str.split(reg);
		if (array == null) {
			array = str.split("、");
		}
		if (array == null) {
			array = str.split("，");
		}
		if (array == null) {
			array = str.split(" ");
		}
		return array;
	}

	/**
	 * getRandom:(获取随机数)
	 * 
	 * @Author airufei
	 * @param minNum
	 * @return
	 */
	public static int getRandom(int maxNum, int min) {
		if (min < 0) {
			min = 0;
		}
		Random random = new Random();
		int num = random.nextInt(maxNum);
		if (min > num) {
			num = maxNum - num;
		}
		return num;
	}

	/**
	 * 获取指定位数的随机数
	 * 
	 * @param num
	 * @return
	 */
	public static String getRandom(int num) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			sb.append(String.valueOf(random.nextInt(10)));
		}
		return sb.toString();
	}

	/**
	 * getKeyWord:(获取资讯信息的随机关键词)
	 * 
	 * @Author airufei
	 * @param entity
	 * @return
	 */
	public static String getFiveKeyWord(String keywords) {
		String keyword = "";
		if (StringUtil.isNotBlank(keywords)) {
			keywords = keywords.trim();
			String[] array = keywords.split(",");
			if (array != null && array.length > 0) {
				if (array.length > 5) {
					for (int i = 0; i < 5; i++) {
						if (StringUtil.isBlank(keyword)) {
							keyword = array[i];
						} else {
							keyword = keyword + "," + array[i];// 随机获取关键词
						}

					}
				} else {
					keyword = keywords;
				}
			}
		}
		return keyword;
	}

	/**
	 * getKeyWord:(获取资讯信息的随机关键词)
	 * 
	 * @Author airufei
	 * @param entity
	 * @return
	 */
	public static String getKeyWord(String keywords) {
		String keyword = "";
		if (StringUtil.isNotBlank(keywords)) {
			keywords = keywords.trim();
			String[] array = keywords.split(",");
			if (array != null && array.length > 0) {
				int index = StringUtil.getRandNum(array.length);
				keyword = array[index];// 随机获取关键词
			}
		}
		return keyword;
	}

	/**
	 * wordsSection:(文字内容处理 长的段落 拆分等等)
	 * 
	 * @Author airufei
	 * @param contentStr
	 * @return
	 */
	public static String StrToSection(String contentStr) {
		StringBuilder s = null;
		if (StringUtil.isBlank(contentStr)) {
			return null;
		}
		if (contentStr.contains("<p>")) {
			return contentStr;
		}
		if (contentStr.contains("<div>")) {
			contentStr = contentStr.replace("<div>", "span");
		}
		s = new StringBuilder();
		String[] strArr = null;
		strArr = contentStr.split("。");
		if (strArr == null || strArr.length < 1) {
			strArr = contentStr.split("！");
		}
		if (strArr == null || strArr.length < 1) {
			strArr = contentStr.split("？");
		}
		if (strArr == null || strArr.length < 1) {
			strArr = contentStr.split("\n");
		}
		if (strArr == null || strArr.length < 1) {
			strArr = contentStr.split("?");
		}
		if (strArr == null || strArr.length < 1) {
			strArr = contentStr.split(".");
		}
		if (strArr != null) {
			int len = strArr.length;
			s.append("<p>小蜜蜂为你精心推荐。</p><p>");
			for (int i = 0; i < len; i++) {
				String str = strArr[i];
				if (str != null && str.contains(str)) {
					str = str.replace("电影下载", "小蜜蜂电影下载").replace("电影频道", "小蜜蜂电影频道").replace("电影评价", "小蜜蜂电影评价");
					str = str.replace("影评", "小蜜蜂影评").replace("游玩", "小蜜蜂旅游").replace("出游", "小蜜蜂旅游").replace("好办法", "小蜜蜂推荐");
					str = str.replace("突发", "小蜜蜂热点").replace("游戏", "小蜜蜂游戏").replace("美女图片", "小蜜蜂美女").replace("美食", "小蜜蜂美食");
				}
				if (str != null && str.length() > 30) {// 最后一段不要
					s.append(str);
				}
				int yushu = s.toString().length() % 60;
				if (yushu >= 50 || (yushu > 20 && yushu < 25) || yushu <= 5) {
					if (str.length() > 2) {
						String ext = str.substring(str.length() - 2, str.length());
						if (ext != null && ext.contains("。")) {
							s.append("</p><p>");
						} else if (i != len - 1) {
							s.append("。</p><p>");
						}
					}
				}
			}
			s.append("</P><p>愿你的年轻的岁月如同小蜜蜂一样勤奋，愿你生活如同蜂蜜一样甜蜜。</p>");
		}
		return s.toString().replace("<br></img>", "</img><br>");
	}

	/**
	 * 验证网址Url
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsUrl(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * isChinese:(这里用一句话描述这个方法的作用)
	 * 
	 * @Author airufei
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		boolean result = false;
		if (StringUtil.isBlank(str)) {
			return result;
		}
		String reg = "^[\u4e00-\u9fa5]*$";
		if (match(reg, str)) {
			result = true;
		}
		return result;
	}

	/**
	 * 清除恶意的XSS脚本和SQL
	 * 
	 * @param value
	 * @return
	 */
	public static String cleanXSSAndSQL(String value) {

		Map<String, String> xssMap = new LinkedHashMap<String, String>();
		xssMap.put("[s|S][c|C][r|R][i|C][p|P][t|T]", "");
		// 含有脚本 javascript
		xssMap.put("[\\\"\\\'][\\s]*[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']", "\"\"");
		// 含有函数： eval
		xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
		// 含有符号 <
		xssMap.put("<", "");
		xssMap.put("delete", "");
		xssMap.put("drop", "");
		xssMap.put("or", "");
		xssMap.put("update", "");
		xssMap.put("selete", "");
		// 含有符号 >
		xssMap.put(">", "");
		// 含有符号 (
		xssMap.put("\\(", "(");
		// 含有符号 )
		xssMap.put("\\)", ")");
		// 含有符号 '
		xssMap.put("'", "'");
		// 含有符号 "
		xssMap.put("\"", "");
		Set<String> keySet = xssMap.keySet();
		for (String key : keySet) {
			String v = xssMap.get(key);
			value = value.replaceAll(key, v);
		}
		return value;
	}
}
