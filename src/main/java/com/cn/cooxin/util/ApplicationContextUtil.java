/**
 * Project Name:CooxinPro
 * File Name:ApplicationContextUtil.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年2月27日上午10:47:10
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * ClassName:ApplicationContextUtil(Spring 中获取bean 实例)
 * Date:     2017年2月27日 上午10:47:10
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class ApplicationContextUtil {

	private static ApplicationContext applicationContext;  
	  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        ApplicationContextUtil.applicationContext = applicationContext;  
    }  
  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    /**
     * getBean:(Spring 中获取bean 实例)
     * @Author airufei
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")  
    public static <T> T getBean(String name) {  
        return (T) getApplicationContext().getBean(name);  
    }  
      
    public static <T> T getBean(Class<T> clazz) {  
        return (T) getApplicationContext().getBean(clazz);  
    }  
  
    public static void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck) {  
        getApplicationContext().getAutowireCapableBeanFactory().autowireBeanProperties(existingBean, autowireMode, dependencyCheck);  
    }  
}

