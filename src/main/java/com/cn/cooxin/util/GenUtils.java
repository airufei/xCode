/**
 * Project Name:CooxinPro
 * File Name:GenUtils.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年1月21日下午10:14:37
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import com.cn.cooxin.code.entity.GenCategory;
import com.cn.cooxin.code.entity.GenConfig;
import com.cn.cooxin.code.entity.GenScheme;
import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.GenTableColumn;
import com.cn.cooxin.code.entity.GenTemplate;
import com.cn.cooxin.common.Global;
import com.cn.cooxin.common.JaxbMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * ClassName:GenUtils (用一句话描述这个变量表示什么)
 * Date:     2017年1月21日 下午10:14:37
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class GenUtils {

	private static Logger logger = LoggerFactory.getLogger(GenUtils.class);

	/**
	 * 初始化列属性字段
	 * @param genTable
	 */
	public static void initColumnField(GenTableColumn column){
			// 设置字段说明
			if (StringUtil.isBlank(column.getComments())){
				column.setComments(column.getName());
			}
			
			// 设置java类型
			if (StringUtil.startsWithIgnoreCase(column.getJdbcType(), "CHAR")
					|| StringUtil.startsWithIgnoreCase(column.getJdbcType(), "VARCHAR")
					|| StringUtil.startsWithIgnoreCase(column.getJdbcType(), "NARCHAR")){
				column.setJavaType("String");
			}else if (StringUtil.startsWithIgnoreCase(column.getJdbcType(), "DATETIME")
					|| StringUtil.startsWithIgnoreCase(column.getJdbcType(), "DATE")
					|| StringUtil.startsWithIgnoreCase(column.getJdbcType(), "TIMESTAMP")){
				column.setJavaType("java.util.Date");
				column.setShowType("dateselect");
			}else if (StringUtil.startsWithIgnoreCase(column.getJdbcType(), "BIGINT")
					|| StringUtil.startsWithIgnoreCase(column.getJdbcType(), "NUMBER")){
				// 如果是浮点型
				String[] ss = StringUtil.split(StringUtil.substringBetween(column.getJdbcType(), "(", ")"), ",");
				if (ss != null && ss.length == 2 && Integer.parseInt(ss[1])>0){
					column.setJavaType("Double");
				}
				// 如果是整形
				else if (ss != null && ss.length == 1 && Integer.parseInt(ss[0])<=10){
					column.setJavaType("Integer");
				}
				// 长整形
				else{
					column.setJavaType("Long");
				}
			}
			
			// 设置java字段名
			column.setJavaField(StringUtil.toCamelCase(column.getName()));
			
			// 是否是主键
			column.setIsPk("0");

			// 插入字段
			column.setIsInsert("1");
			
			// 编辑字段
			if (!StringUtil.equalsIgnoreCase(column.getName(), "id")
					&& !StringUtil.equalsIgnoreCase(column.getName(), "create_by")
					&& !StringUtil.equalsIgnoreCase(column.getName(), "create_date")
					&& !StringUtil.equalsIgnoreCase(column.getName(), "del_flag")){
				column.setIsEdit("1");
			}

			// 列表字段
			if (StringUtil.equalsIgnoreCase(column.getName(), "name")
					|| StringUtil.equalsIgnoreCase(column.getName(), "title")
					|| StringUtil.equalsIgnoreCase(column.getName(), "remarks")
					|| StringUtil.equalsIgnoreCase(column.getName(), "update_date")){
				column.setIsList("1");
			}
			
			// 查询字段
			if (StringUtil.equalsIgnoreCase(column.getName(), "name")
					|| StringUtil.equalsIgnoreCase(column.getName(), "title")){
				column.setIsQuery("1");
			}
			
			// 查询字段类型
			if (StringUtil.equalsIgnoreCase(column.getName(), "name")
					|| StringUtil.equalsIgnoreCase(column.getName(), "title")){
				column.setQueryType("like");
			}

			
			// 创建时间、更新时间
			else if (StringUtil.startsWithIgnoreCase(column.getName(), "createtime")
					|| StringUtil.startsWithIgnoreCase(column.getName(), "updatetime")){
				column.setShowType("dateselect");
			}
			// 备注、内容
			else if (StringUtil.equalsIgnoreCase(column.getName(), "remark")
					|| StringUtil.equalsIgnoreCase(column.getName(), "content")){
				column.setShowType("textarea");
			}
			// 父级ID
			else if (StringUtil.equalsIgnoreCase(column.getName(), "parent_id")){
				column.setJavaType("This");
				column.setJavaField("parent.id|name");
				column.setShowType("treeselect");
			}
			// 所有父级ID
			else if (StringUtil.equalsIgnoreCase(column.getName(), "parent_ids")){
				column.setQueryType("like");
			}
			// 删除标记
			else if (StringUtil.equalsIgnoreCase(column.getName(), "flag")){
				column.setShowType("radiobox");
				column.setDictType("flag");
			}
		
	}
	
	/**
	 * 获取模板路径
	 * @return
	 */
	public static String getTemplatePath(){
		try{
			File file = new DefaultResourceLoader().getResource("").getFile();
			if(file != null){
				return file.getAbsolutePath() + File.separator + StringUtil.replaceEach(GenUtils.class.getName(), 
						new String[]{"util."+GenUtils.class.getSimpleName(), "."}, new String[]{"template", File.separator});
			}			
		}catch(Exception e){
			logger.error("{}", e);
		}

		return "";
	}
	
	/**
	 * XML文件转换为对象
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fileToObject(String fileName, Class<?> clazz){
		try {
			String pathName = "/code/" + fileName;
			Resource resource = new ClassPathResource(pathName); 
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();  
			while (true) {
				String line = br.readLine();
				if (line == null){
					break;
				}
				sb.append(line).append("\r\n");
			}
			if (is != null) {
				is.close();
			}
			if (br != null) {
				br.close();
			}
			return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
			logger.warn("Error file convert: {}", e.getMessage());
		} catch (JAXBException e) {
			logger.warn("Error file convert: {}", e.getMessage());
			
		}

		return null;
	}
	
	/**
	 * 获取代码生成配置对象
	 * @return
	 */
	public static GenConfig getConfig(){
		return fileToObject("config.xml", GenConfig.class);
	}

	/**
	 * 根据分类获取模板列表
	 * @param config
	 * @param genScheme
	 * @param isChildTable 是否是子表
	 * @return
	 */
	public static List<GenTemplate> getTemplateList(GenConfig config, String category, boolean isChildTable){
		List<GenTemplate> templateList = Lists.newArrayList();
		if (config !=null && config.getCategoryList() != null && category !=  null){
			for (GenCategory e : config.getCategoryList()){
				if (category.equals(e.getDictKey())){
					List<String> list = null;
					if (!isChildTable){
						list = e.getTemplate();
					}else{
						list = e.getChildTableTemplate();
					}
					if (list != null){
						for (String s : list){
							if (StringUtil.startsWith(s, GenCategory.CATEGORY_REF)){
								templateList.addAll(getTemplateList(config, StringUtil.replace(s, GenCategory.CATEGORY_REF, ""), false));
							}else{
								GenTemplate template = fileToObject(s, GenTemplate.class);
								if (template != null){
									templateList.add(template);
								}
							}
						}
					}
					break;
				}
			}
		}
		return templateList;
	}
	
	/**
	 * 获取数据模型
	 * @param genScheme
	 * @param genTable
	 * @return
	 */
	public static Map<String, Object> getDataModel(GenScheme genScheme,GenTable GenTable,List<GenTableColumn> list,int queryCount,int editCount){
		Map<String, Object> model = Maps.newHashMap();
		String moduleName=StringUtil.lowerCase(genScheme.getModuleName());
		model.put("packageName", StringUtil.lowerCase(genScheme.getPackageName()));
		model.put("lastPackageName", StringUtil.substringAfterLast((String)model.get("packageName"),"."));
		model.put("javaModuleName", moduleName);
		model.put("modulePageName", StringUtil.lowerCase(genScheme.getModulePageName()));
		model.put("subPackageName", StringUtil.lowerCase(genScheme.getSubPackageName()));
		model.put("subModuleName", StringUtil.lowerCase(genScheme.getSubModuleName()));
		model.put("className", StringUtil.uncapitalize(GenTable.getClassName()));
		model.put("ClassName", StringUtil.capitalize(GenTable.getClassName()));
		model.put("moduleName", moduleName);
		model.put("functionName", genScheme.getFunctionName());
		model.put("functionNameSimple", genScheme.getFunctionNameSimple());
		model.put("functionAuthor", StringUtil.isNotBlank(genScheme.getFunctionAuthor())?genScheme.getFunctionAuthor():"airufei");
		model.put("functionVersion", DateUtils.getDate());
		model.put("dbType", Global.getConfig("jdbc.type"));
		model.put("editCount", editCount);
		model.put("queryCount", queryCount);
		model.put("table", GenTable);
		model.put("colList", list);
		return model;
	}
	

	
	/**
	 * 生成到文件
	 * @param tpl
	 * @param model
	 * @param replaceFile
	 * @return
	 */
	public static String generateToFile(GenTemplate tpl, Map<String, Object> model, boolean isReplaceFile,String roPath,String moduleName){
		String msg=null;
		try {
			String path=Global.getProjectPath();
			if(roPath.contains("website"))//网站子站生成路径
			{
			    path=Global.getWebSiteHtmlFilePath();
			    File f=new File(path);
			    if(f.exists())
			    {
			    	
			    }
			  
			}else if((roPath.contains("sys")))//代码生成路径
			{
				path=Global.getProjectPath();
			}
			else//网站静态文件路径
			{
				path=Global.getHtmlFilePath();
			}
			if(StringUtil.isNotBlank(moduleName)&&"list".equals(roPath))
			{
				path=path+"/"+moduleName;
			}
			model.put("moduleName", moduleName);
			FileUtil.CreateDirectory(path);
			// 获取生成文件
			String fileName = path + File.separator + StringUtil.replaceEach(FreeMarkers.renderString(tpl.getFilePath() + "/", model), new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator})+ FreeMarkers.renderString(tpl.getFileName(), model);
			if(fileName!=null)
			{
				fileName=fileName.replace(",", "").replace("index_1", "index");
			}
			logger.info(" moduleName === " + moduleName +" ============fileName:" + fileName);
			// 获取生成文件内容
			String content = FreeMarkers.renderString(StringUtil.trimToEmpty(tpl.getContent()), model);
			logger.debug(" content === \r\n" + content);
			
			// 如果选择替换文件，则删除原文件
			if (isReplaceFile){
				FileUtils.deleteFile(fileName);
			}
			// 创建并写入文件
			if (FileUtils.createFile(fileName)){
				FileUtils.writeToFile(fileName, content, true);
				logger.debug(" file create === " + fileName);
				msg= "生成成功："+fileName+"<br/>";
			}else{
				logger.debug(" file extents === " + fileName);
				msg ="文件已存在："+fileName+"<br/>";
			}
		} catch (Exception e) {
			logger.warn("Error file convert: {}", e.getMessage());
		}
		catch (Error e) {
			logger.warn("Error=========================>"+e);
		}
		return msg;
	}
	
	public static void main(String[] args) {
		try {
			GenConfig config = getConfig();
			logger.warn(JaxbMapper.toXml(config));
		} catch (Exception e) {

		}
	}
	
}

