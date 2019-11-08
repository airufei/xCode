/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.entity;

import java.util.List;

import com.cn.cooxin.util.StringUtil;
import com.google.common.collect.Lists;

/**
 * 业务表字段Entity
 * 
 *
 * @version 2013-10-15
 */
public class GenTableColumn extends BaseEntitys {

	private static final long serialVersionUID = 1L;
	private long tableId; // 表id
	private String tableName; // 表名
	private String name; // 列名
	private String comments; // 描述
	private String jdbcType; // JDBC类型
	private String javaType; // JAVA类型
	private String javaField; // JAVA字段名
	private String isPk; // 是否主键（1：主键）
	private String isNull; // 是否可为空（1：可为空；0：不为空）
	private String isInsert; // 是否为插入字段（1：插入字段）

	private String isEdit; // 是否编辑字段（1：编辑字段）
	private String isList; // 是否列表字段（1：列表字段）
	private String isQuery; // 是否查询字段（1：查询字段）
	private String isEditPage; // 是否编辑字段（1：编辑字段）
	private String isInsertRequiredField; // 1 插入必须字段
	private String isUpdateRequiredField; //  1 修改必须字段
	private String isSort; //  1 排序字段

	private String queryType; // 查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）
	private String showType; // 字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）
	private String dictType; // 字典类型
	private Integer sort; // 排序（升序）
	private String nameLike; // 按名称模糊查询
	private String pk; // 当前表主键列表

	private String dbName = "mysql"; // 数据库名称

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public GenTableColumn() {
		super();
	}

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getJdbcType() {
		return StringUtil.lowerCase(jdbcType);
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaField() {
		return javaField;
	}

	public void setJavaField(String javaField) {
		this.javaField = javaField;
	}

	public String getIsPk() {
		return isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getIsEditPage() {
		return isEditPage;
	}

	public void setIsEditPage(String isEditPage) {
		this.isEditPage = isEditPage;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getDictType() {
		return dictType == null ? "" : dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

    public String getIsInsertRequiredField() {
        return isInsertRequiredField;
    }

    public void setIsInsertRequiredField(String isInsertRequiredField) {
        this.isInsertRequiredField = isInsertRequiredField;
    }

    public String getIsUpdateRequiredField() {
        return isUpdateRequiredField;
    }

    public void setIsUpdateRequiredField(String isUpdateRequiredField) {
        this.isUpdateRequiredField = isUpdateRequiredField;
    }

	public String getIsSort() {
		return isSort;
	}

	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}

	/**
	 * 获取列名和说明
	 * 
	 * @return
	 */
	public String getNameAndComments() {
		return getName() + (comments == null ? "" : "  :  " + comments);
	}

	/**
	 * 获取字符串长度
	 * 
	 * @return
	 */
	public String getDataLength() {
		String[] ss = StringUtil.split(
				StringUtil.substringBetween(getJdbcType(), "(", ")"), ",");
		if (ss != null && ss.length == 1) {// &&
											// "String".equals(getJavaType())){
			return ss[0];
		}
		return "0";
	}

	/**
	 * 获取简写Java类型
	 * 
	 * @return
	 */
	public String getSimpleJavaType() {
		return StringUtil.indexOf(getJavaType(), ".") != -1 ? StringUtil
				.substringAfterLast(getJavaType(), ".") : getJavaType();
	}

	/**
	 * 获取简写Java字段
	 * 
	 * @return
	 */
	public String getSimpleJavaField() {
		return StringUtil.substringBefore(getJavaField(), ".");
	}

	/**
	 * 获取Java字段，如果是对象，则获取对象.附加属性1
	 * 
	 * @return
	 */
	public String getJavaFieldId() {
		return StringUtil.substringBefore(getJavaField(), "|");
	}

	/**
	 * 获取Java字段，如果是对象，则获取对象.附加属性2
	 * 
	 * @return
	 */
	public String getJavaFieldName() {
		String[][] ss = getJavaFieldAttrs();
		return ss.length > 0 ? getSimpleJavaField() + "." + ss[0][0] : "";
	}

	/**
	 * 获取Java字段，所有属性名
	 * 
	 * @return
	 */
	public String[][] getJavaFieldAttrs() {
		String[] ss = StringUtil.split(
				StringUtil.substringAfter(getJavaField(), "|"), "|");
		String[][] sss = new String[ss.length][2];
		if (ss != null) {
			for (int i = 0; i < ss.length; i++) {
				sss[i][0] = ss[i];
				sss[i][1] = StringUtil.toUnderScoreCase(ss[i]);
			}
		}
		return sss;
	}

	/**
	 * 获取列注解列表
	 * 
	 * @return
	 */
	public List<String> getAnnotationList() {
		List<String> list = Lists.newArrayList();
		// 导入Jackson注解
		if ("This".equals(getJavaType())) {
			list.add("com.fasterxml.jackson.annotation.JsonBackReference");
		}
		if ("java.util.Date".equals(getJavaType())) {
			list.add("com.fasterxml.jackson.annotation.JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
		}
		// 导入JSR303验证依赖包
		if (!"1".equals(getIsNull()) && !"String".equals(getJavaType())) {
			list.add("javax.validation.constraints.NotNull(message=\""
					+ getComments() + "不能为空\")");
		} else if (!"1".equals(getIsNull()) && "String".equals(getJavaType())
				&& !"0".equals(getDataLength())) {
			list.add("org.hibernate.validator.constraints.Length(min=1, max="
					+ getDataLength() + ", message=\"" + getComments()
					+ "长度必须介于 1 和 " + getDataLength() + " 之间\")");
		} else if ("String".equals(getJavaType())
				&& !"0".equals(getDataLength())) {
			list.add("org.hibernate.validator.constraints.Length(min=0, max="
					+ getDataLength() + ", message=\"" + getComments()
					+ "长度必须介于 0 和 " + getDataLength() + " 之间\")");
		}
		return list;
	}

	/**
	 * 获取简写列注解列表
	 * 
	 * @return
	 */
	public List<String> getSimpleAnnotationList() {
		List<String> list = Lists.newArrayList();
		for (String ann : getAnnotationList()) {
			list.add(StringUtil.substringAfterLast(ann, "."));
		}
		return list;
	}

	/**
	 * 是否是基类字段
	 * 
	 * @return
	 */
	public Boolean getIsNotBaseField() {
		return !StringUtil.equals(getSimpleJavaField(), "id")
				&& !StringUtil.equals(getSimpleJavaField(), "remark")
				&& !StringUtil.equals(getSimpleJavaField(), "createtime")
				&& !StringUtil.equals(getSimpleJavaField(), "updatetime")
				&& !StringUtil.equals(getSimpleJavaField(), "updateTime")
				&& !StringUtil.equals(getSimpleJavaField(), "createTime")
				&& !StringUtil.equals(getSimpleJavaField(), "createdBy")
				&& !StringUtil.equals(getSimpleJavaField(), "updatedTime")
				&& !StringUtil.equals(getSimpleJavaField(), "updatedBy")
				&& !StringUtil.equals(getSimpleJavaField(), "status")
				&& !StringUtil.equals(getSimpleJavaField(), "createdTime")
				&& !StringUtil.equals(getSimpleJavaField(), "flag");
	}


}
