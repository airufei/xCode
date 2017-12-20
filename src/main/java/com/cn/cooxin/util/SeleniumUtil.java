/**
 * Project Name:CooxinPro
 * File Name:SeleniumUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年4月4日下午9:56:35
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.util;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

/**
 * ClassName:SeleniumUtil (用一句话描述这个变量表示什么)
 * Date:     2017年4月4日 下午9:56:35
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class SeleniumUtil {

	/**
	 * switchToNewWindow:(切换到最新的窗口)
	 * 
	 * @Author airufei
	 * @param driver
	 */
	public static void switchToNewWindow(WebDriver driver) {
		String currentWindow = driver.getWindowHandle();// 得到当前句柄
		// 得到所有窗口的句柄
		Set<String> handles = driver.getWindowHandles();

		// 排除当前窗口的句柄，则剩下是新窗口
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			
			if (currentWindow == it.next())
				continue;
			driver.switchTo().window(it.next());
		}

	}
	
	public static boolean switchToNewWindow(WebDriver driver,String windowTitle){  
	    boolean flag = false;  
	    try {  
	        String currentHandle = driver.getWindowHandle();  
	        Set<String> handles = driver.getWindowHandles();  
	        for (String s : handles) {  
	            if (s.equals(currentHandle))  
	                continue;  
	            else {  
	                driver.switchTo().window(s);  
	                if (driver.getTitle().contains(windowTitle)) {  
	                    flag = true;  
	                    System.out.println("Switch to window: "  
	                            + windowTitle + " successfully!");  
	                    break;  
	                } else  
	                    continue;  
	            }  
	        }  
	    } catch (NoSuchWindowException e) {  
	        System.out.printf("Window: " + windowTitle  
	                + " cound not found!", e.fillInStackTrace());  
	        flag = false;  
	    }  
	    return flag;  
	}
}

