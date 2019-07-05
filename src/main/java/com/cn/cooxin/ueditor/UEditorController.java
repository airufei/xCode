/**
 * Project Name:CooxinPro
 * File Name:UEditorController.java
 * Package Name:com.cn.cooxin.ueditor
 * Date:2017年3月27日上午11:31:29
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.ueditor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:UEditorController Date: 2017年3月27日 上午11:31:29
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
@Controller
@RequestMapping("/ued")
public class UEditorController {

	private static Logger logger = Logger.getLogger(UEditorController.class);
	@RequestMapping(value = "/config")
	public void config(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try {
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error(e);

		}

	}
}
