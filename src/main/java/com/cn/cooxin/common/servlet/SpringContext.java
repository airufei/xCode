/**
 * Project Name:CooxinPro
 * File Name:SpringContext.java
 * Package Name:com.cn.cooxin.common.servlet
 * Date:2017年3月8日下午1:45:08
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common.servlet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ClassName:SpringContext
 * Date:     2017年3月8日 下午1:45:08
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class SpringContext implements ApplicationContextAware {  
  
    private static ApplicationContext applicationContext; // Spring应用上下文环境  
  
    /* 
     * 
     * 实现了ApplicationContextAware 接口，必须实现该方法； 
     * 
     * 通过传递applicationContext参数初始化成员变量applicationContext 
     */  
  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
    	SpringContext.applicationContext = applicationContext;  
    }  
  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    @SuppressWarnings("unchecked")  
    public static <T> T getBean(String name) throws BeansException {  
        return (T) applicationContext.getBean(name);  
    }
}

