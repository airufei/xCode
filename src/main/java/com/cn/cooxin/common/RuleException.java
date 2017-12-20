/**
 * Project Name:CooxinPro
 * File Name:RuleException.java
 * Package Name:com.cn.cooxin.common
 * Date:2016年7月6日下午2:14:12
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common;
/**
 * ClassName:RuleException 异常处理类
 * Date:     2016年7月6日 下午2:14:12
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */

@SuppressWarnings("serial")
public class RuleException extends RuntimeException
{

	public RuleException()
	{
		super();
		
	}

	public RuleException(String message, Throwable cause)
	{
		super(message, cause);
		
	}

	public RuleException(String message)
	{
		super(message);
		
	}

	public RuleException(Throwable cause)
	{
		super(cause);
		
	}

}


