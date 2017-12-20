/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.admin.service;

import java.util.List;
import java.util.Map;

import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.code.entity.Partion;

/**
 * Service(用户信息)
 * 
 * @author airufei
 * @version 2017-02-13
 */
@SuppressWarnings("all")
public interface IBaseUserService {

	/**
	 * 删除数据（逻辑删除）
	 * 
	 * @param question
	 * @return
	 */
	public void delete(long id);

	/**
	 * 单条数据增加
	 * 
	 * @param baseUser
	 * @return
	 */
	public void add(BaseUser baseUser);

	/**
	 * 批量数据增加
	 * 
	 * @param baseUser
	 * @return
	 */
	public void addTrainRecordBatch(List<BaseUser> list);

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public BaseUser getBaseUserById(long id);

	/**
	 * 修改单条数据
	 * 
	 * @param id
	 * @return
	 */
	public void updateById(BaseUser baseUser);

	/**
	 * 获取分页数据
	 * 
	 * @param map
	 * @return
	 */
	public Partion getList(Map map);

	/**
	 * 获取集合数据，不带分页
	 * 
	 * @param map
	 * @return
	 */
	public List<BaseUser> getBaseUserList(BaseUser baseUser);

	/**
	 * 获取单个用户信息
	 * 
	 * @param map
	 * @return
	 */
	public BaseUser getBaseUser(BaseUser baseUser);
	
	
	/**
	 * 用户是否存在
	 * 
	 * @param map
	 * @return
	 */
	public boolean isExsitBaseUser(BaseUser baseUser);

	/**
	 * 获取分页总数
	 * 
	 * @param map
	 * @return
	 */
	public int getTotalCount(Map map);

	/**
	 * updatePwd:(重置密码)
	 * @Author airufei
	 * @param baseUser
	 */
	public void updatePwd(BaseUser baseUser);

}