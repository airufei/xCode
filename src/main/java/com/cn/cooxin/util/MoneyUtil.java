/**
 * Project Name:CooxinPro
 * File Name:MoneyUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年2月26日下午9:13:09
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ClassName:MoneyUtil (金额数据工具类)
 * Date:     2017年2月26日 下午9:13:09
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class MoneyUtil {

	/**
	 * getMoney:(金额保留两位小数)
	 * 
	 * @Author airufei
	 * @param f
	 * @return
	 */
	public  static String getDubbleMoney(double f) {
		String f1 ="0.00";
		if (f > 0) {
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
			 f1=df.format(f);
		}
		return f1;
	}

	
	/**
	 * getListMoney:(获取随机金额数集合)
	 * @Author airufei
	 * @param listCount
	 * @param max
	 * @param min
	 * @return
	 */
	public static List<Integer> getListMoney(int listCount, int max, int min) {
		List<Integer> list = new ArrayList<Integer>();
		boolean isNext = true;
		while (isNext) {
			Random rom = new Random();
			int newM = rom.nextInt(max);
			boolean check = CheckMoney(newM, min);
			if (check) {
				list.add(newM);
				int listSize = list.size();
				if (listSize >= listCount) {
					isNext = false;
				}
			}
		}
		return list;
	}

	/**
	 * CheckMoney:(检查金额)
	 * @Author airufei
	 * @param newM
	 * @param max
	 * @param min
	 * @return
	 */
	public static boolean CheckMoney(int newM,int min) {
		boolean result = true;
		if (newM < min) {
			result = false;
		}
		return result;
	}

	/**
	 * getRandromMoney:(获取随机数)
	 * 
	 * @Author airufei
	 * @return
	 */
	public static double getRandromMoney() {
		Random rom = new Random();
		return rom.nextDouble();
	}

}

