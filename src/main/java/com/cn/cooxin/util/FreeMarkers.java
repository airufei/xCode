/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * FreeMarkers工具类
 *
 * @version 2013-01-15
 */
public class FreeMarkers {
	private static Logger logger = Logger.getLogger(FreeMarkers.class);
	
	@SuppressWarnings("deprecation")
	public static String renderString(String templateString, Map<String, ?> model) {
		String str="";
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString), new Configuration());
			t.process(model, result);
			str= result.toString();
		} catch (Exception e) {

			logger.error("FreeMarkers"+e);
		}
		return str;
	}

	public static String renderTemplate(Template template, Object model) {
		String str="";
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			str=  result.toString();
		} catch (Exception e) {

			logger.error("FreeMarkers"+e);
		}
		return str;
	}

	@SuppressWarnings("deprecation")
	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}
	
	public static void main(String[] args) throws IOException {

	}
	
}
