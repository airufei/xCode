/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 *
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
			"yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	private final static int[] LUNAR_INFO = {  
        0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,  
        0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,  
        0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,  
        0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,  
        0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,  
        0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,  
        0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,  
        0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,  
        0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,  
        0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,  
        0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,  
        0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,  
        0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,  
        0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,  
        0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0  
    };  
  
    // 允许输入的最小年份  
    private final static int MIN_YEAR = 1900;  
    // 允许输入的最大年份  
    private final static int MAX_YEAR = 2049;  
    // 当年是否有闰月  
    private static boolean isLeapYear;  
    // 阳历日期计算起点  
    private final static String START_DATE = "1900-01-30";  
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	
	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	
	/**
	 * beforDayDate:(获取N天前的时间字符串)
	 * @Author airufei
	 * @param befDay 正数为加    负数为减
	 * @return
	 */
	public static String beforDayDate(int befDay)
	{
		String str="";
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Date beginDate = new Date();
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE)+ befDay);
			Date endDate = dft.parse(dft.format(date.getTime()));
			str=formatDate(endDate,"");
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
		return str;
	}
	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
				* 1000 - min * 60 * 1000 - s * 1000);

		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
				+ sss;
	}

	/**
	 * getDateTime:(获取当前时间前1小时随机时间-分秒随机取值)
	 * 
	 * @Author airufei
	 * @param h
	 *            需要变化的小时数（为正数则加，为负数则减）
	 * @param numCount
	 *            需要生成的数量
	 * @param maxIntervalMin
	 *            大分钟间隔数
	 * @param minIntervalSecond
	 *            最小秒钟间隔数
	 * @return
	 */
	public static List<Date> getNowDateTime(int h, int numCount,
			int maxIntervalMin, int minIntervalSecond) {
		List<Date> list = new ArrayList<>();
		Date dt = new Date();
		String dateStr = DateUtils.formatDate(dt, "yyyy-MM-dd HH");
		if (dateStr != null) {
			dateStr = dateStr + ":00:00";
		}
		dt = DateUtils.parseDate(dateStr);
		boolean isNext = true;
		while (isNext) {
			Date dt1 = DateUtils.getDateTime(dt, h);
			boolean check = checkDateTime(list, dt1, maxIntervalMin,
					minIntervalSecond);
			if (check) {
				list.add(dt1);
			}
			int listCount = list.size();
			if (listCount >= numCount) {
				isNext = false;
			}
		}
		return list;

	}

	/**
	 * checkDateTime:(检查时间是否符合规范)
	 * 
	 * @Author airufei
	 * @param list
	 *            已经合规的时间集合
	 * @param newdate
	 *            需要检查的时间
	 * @param minInterval
	 *            大分钟间隔数
	 * @param secondInterval
	 *            最小秒钟间隔数
	 * @return
	 */
	public static boolean checkDateTime(List<Date> list, Date newdate,
			int minInterval, int secondInterval) {
		boolean result = true;
		if (list == null || (list.size() < 1)) {
			return result;
		}
		for (Date item : list) {
			long cha = getMinTwoDate(item, newdate);
			if (cha > minInterval) {
				result = false;
				break;
			}
			long second = getSecondTwoDate(item, newdate);
			if (second < secondInterval) {
				result = false;
				break;
			}
		}
		return result;

	}

	/**
	 * getDateTime:(获取随机随机时间-分秒随机取值)
	 * 
	 * @Author airufei
	 * @param dt
	 * @param h
	 *            需要变化的小时数（为正数则加，为负数则减）
	 * @return
	 */
	public static Date getDateTime(Date dt, int h) {
		GregorianCalendar gc = new GregorianCalendar();
		Random rd = new Random();
		int m = rd.nextInt(59);// m 需要变化的分钟数（为正数则加，为负数则减）
		if (m < -30) {
			m = 17;
		}
		int s = rd.nextInt(59);
		if (s < -60) {
			s = 13;
		}
		gc.setTime(dt);
		int field = 1;
		int value = 0;
		if (h != 0) {
			field = 10;
			value = h;
			gc.add(field, value);
		}
		if (m != 0) {
			field = 12;
			value = m;
			gc.add(field, value);
		}
		if (s != 0) {
			field = 13;
			value = s;
			gc.add(field, value);
		}
		return gc.getTime();
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static long getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取两个时间的分钟差值
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static long getMinTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		long cha = 0;
		if (afterTime > beforeTime) {
			cha = (afterTime - beforeTime) / (1000 * 60);
		} else {
			cha = (beforeTime - afterTime) / (1000 * 60);
		}
		return cha;
	}

	/**
	 * 获取两个时间的秒钟差值
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static long getSecondTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		long cha = 0;
		if (afterTime > beforeTime) {
			cha = (afterTime - beforeTime) / (1000);
		} else {
			cha = (beforeTime - afterTime) / (1000);
		}
		return cha;
	}

	
      
      
    /** 
     * 计算阴历 {@code year}年闰哪个月 1-12 , 没闰传回 0 
     * @param year 阴历年 
     * @return (int)月份 
     * @author liu 2015-1-5 
     */  
    private static int getLeapMonth(int year) {  
        return (int) (LUNAR_INFO[year - 1900] & 0xf);  
    }  
      
    /** 
     * 计算阴历{@code year}年闰月多少天 
     * @param year 阴历年 
     * @return (int)天数 
     * @author liu 2015-1-5 
     */  
    private static int getLeapMonthDays(int year) {  
        if(getLeapMonth(year)!=0){  
            if((LUNAR_INFO[year - 1900] & 0xf0000)==0){  
                return 29;  
            }else {  
                return 30;  
            }  
        }else{  
            return 0;  
        }  
          
    }  
      
    /** 
     * 计算阴历{@code lunarYeay}年{@code month}月的天数 
     * @param lunarYeay 阴历年 
     * @param month 阴历月 
     * @return (int)该月天数 
     * @throws Exception  
     * @author liu 2015-1-5 
     */  
    private static int getMonthDays(int lunarYeay, int month) throws Exception {  
        if ((month > 31) || (month < 0)) {  
            throw(new Exception("月份有错！"));  
        }  
        // 0X0FFFF[0000 {1111 1111 1111} 1111]中间12位代表12个月，1为大月，0为小月  
        int bit = 1 << (16-month);  
        if(((LUNAR_INFO[lunarYeay - 1900] & 0x0FFFF)&bit)==0){  
            return 29;  
        }else {  
            return 30;  
        }  
    }  
      
    /** 
     * 计算阴历{@code year}年的总天数 
     * @param year 阴历年 
     * @return (int)总天数 
     * @author liu 2015-1-5 
     */  
    private static int getYearDays(int year) {  
        int sum = 29*12;  
        for(int i=0x8000;i>=0x8;i>>=1){  
            if((LUNAR_INFO[year-1900]&0xfff0&i)!=0){  
                sum++;  
            }  
        }  
        return sum+getLeapMonthDays(year);  
    }  
      
    /** 
     * 计算两个阳历日期相差的天数。 
     * @param startDate 开始时间 
     * @param endDate 截至时间 
     * @return (int)天数 
     * @author liu 2015-1-5 
     */  
    private static int daysBetween(Date startDate, Date endDate) {  
        long between_days=(endDate.getTime()-startDate.getTime())/(1000*3600*24);  
          
        return Integer.parseInt(String.valueOf(between_days));      
    }  
      
    /** 
     * 检查阴历日期是否合法 
     * @param lunarYear 阴历年 
     * @param lunarMonth 阴历月 
     * @param lunarDay 阴历日 
     * @param leapMonthFlag 闰月标志 
     * @throws Exception 
     */  
    private static void checkLunarDate(int lunarYear, int lunarMonth, int lunarDay, boolean leapMonthFlag) throws Exception {  
        if ((lunarYear < MIN_YEAR) || (lunarYear > MAX_YEAR)) {  
            throw(new Exception("非法农历年份！"));  
        }  
        if ((lunarMonth < 1) || (lunarMonth > 12)) {  
            throw(new Exception("非法农历月份！"));  
        }  
        if ((lunarDay < 1) || (lunarDay > 30)) { // 中国的月最多30天  
            throw(new Exception("非法农历天数！"));  
        }  
  
        int leap = getLeapMonth(lunarYear);// 计算该年应该闰哪个月  
        if ((leapMonthFlag == true) && (lunarMonth != leap)) {  
            throw(new Exception("非法闰月！"));  
        }  
    }  
	 /** 
     * 阴历转换为阳历 
     * @param lunarDate 阴历日期,格式YYYYMMDD 
     * @param leapMonthFlag 是否为闰月 
     * @return 阳历日期,格式：YYYYMMDD 
     * @throws Exception  
     * @author liu 2015-1-5 
     */  
    public static String lunarToSolar(String lunarDate, boolean leapMonthFlag) throws Exception{  
        int lunarYear = Integer.parseInt(lunarDate.substring(0, 4));  
        int lunarMonth = Integer.parseInt(lunarDate.substring(4, 6));  
        int lunarDay = Integer.parseInt(lunarDate.substring(6, 8));  
          
        checkLunarDate(lunarYear, lunarMonth, lunarDay, leapMonthFlag);  
          
        int offset = 0;  
          
        for (int i = MIN_YEAR; i < lunarYear; i++) {  
            int yearDaysCount = getYearDays(i); // 求阴历某年天数  
            offset += yearDaysCount;  
        }  
        //计算该年闰几月  
        int leapMonth = getLeapMonth(lunarYear);  
          
        if(leapMonthFlag & leapMonth != lunarMonth){  
            throw(new Exception("您输入的闰月标志有误！"));  
        }  
          
        //当年没有闰月或月份早于闰月或和闰月同名的月份  
        if(leapMonth==0|| (lunarMonth < leapMonth) || (lunarMonth==leapMonth && !leapMonthFlag)){  
            for (int i = 1; i < lunarMonth; i++) {  
                int tempMonthDaysCount = getMonthDays(lunarYear, i);  
                offset += tempMonthDaysCount;  
            }  
  
            // 检查日期是否大于最大天  
            if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {  
                throw(new Exception("不合法的农历日期！"));  
            }  
            offset += lunarDay; // 加上当月的天数  
        }else{//当年有闰月，且月份晚于或等于闰月  
            for (int i = 1; i < lunarMonth; i++) {  
                int tempMonthDaysCount = getMonthDays(lunarYear, i);  
                offset += tempMonthDaysCount;  
            }  
            if (lunarMonth>leapMonth) {  
                int temp = getLeapMonthDays(lunarYear); // 计算闰月天数  
                offset += temp; // 加上闰月天数  
  
                if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {  
                    throw(new Exception("不合法的农历日期！"));  
                }  
                offset += lunarDay;  
            }else { // 如果需要计算的是闰月，则应首先加上与闰月对应的普通月的天数  
                // 计算月为闰月  
                int temp = getMonthDays(lunarYear, lunarMonth); // 计算非闰月天数  
                offset += temp;  
  
                if (lunarDay > getLeapMonthDays(lunarYear)) {  
                    throw(new Exception("不合法的农历日期！"));  
                }  
                offset += lunarDay;  
            }  
        }  
          
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date myDate = null;  
        myDate = formatter.parse(START_DATE);  
        Calendar c = Calendar.getInstance();  
        c.setTime(myDate);  
        c.add(Calendar.DATE, offset);  
        myDate = c.getTime();  
        return formatter.format(myDate);  
    }  
      
    /** 
     * 阳历日期转换为阴历日期 
     * @param solarDate 阳历日期,格式YYYYMMDD 
     * @return 阴历日期 
     * @throws Exception  
     * @author liu 2015-1-5 
     */  
    public static String solarToLunar(String solarDate){  
        int i;  
        int temp = 0;  
        int lunarYear;  
        int lunarMonth; //农历月份  
        int lunarDay; //农历当月第几天  
        boolean leapMonthFlag =false;  
        String nongli="";  
        try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
			Date myDate = null;  
			Date startDate = null;  
			try {  
			    myDate = formatter.parse(solarDate);  
			    startDate = formatter.parse(START_DATE);  
			} catch (ParseException e) {  
			    e.printStackTrace();  
			}  
			  
			int offset = daysBetween(startDate,myDate);  
			  
			for (i = MIN_YEAR; i <= MAX_YEAR; i++){  
			    temp = getYearDays(i);  //求当年农历年天数  
			    if (offset - temp < 1){  
			        break;  
			    }else{  
			        offset -= temp;  
			    }  
			}  
			lunarYear = i;  
  
			int leapMonth = getLeapMonth(lunarYear);//计算该年闰哪个月  
			//设定当年是否有闰月  
			if (leapMonth > 0){  
			    isLeapYear = true;  
			}else{  
			    isLeapYear = false;  
			}  
			  
			for (i = 1;  i<=12; i++) {  
			    if(i==leapMonth+1 && isLeapYear){  
			        temp = getLeapMonthDays(lunarYear);  
			        isLeapYear = false;  
			        leapMonthFlag = true;  
			        i--;  
			    }else{  
			        temp = getMonthDays(lunarYear, i);  
			    }  
			    offset -= temp;  
			    if(offset<=0){  
			        break;  
			    }  
			}  
			  
			offset += temp;  
			lunarMonth = i;  
			lunarDay = offset;  
			nongli="农历："+lunarYear+"年"+(leapMonthFlag&(lunarMonth==leapMonth)?"闰":"")+lunarMonth+"月"+lunarDay+"日";
		} catch (Exception e) {
			e.printStackTrace();
			
		}  
        return nongli;
    }  
      
	
}
