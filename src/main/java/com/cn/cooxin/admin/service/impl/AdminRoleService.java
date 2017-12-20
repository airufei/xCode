package com.cn.cooxin.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.dao.IAdminMenuDao;
import com.cn.cooxin.admin.dao.IAdminRoleDao;
import com.cn.cooxin.admin.service.IAdminRoleService;
import com.cn.cooxin.code.entity.Partion;

/**
 * Service(角色管理)
 * 
 * @author airufei
 * @version 2017-02-11
 */
@Service
@SuppressWarnings("all")
public class AdminRoleService implements IAdminRoleService {

	@Resource
	private IAdminRoleDao adminRoleDao;
	
	@Resource
	private IAdminMenuDao adminMenuDao;

	/**
	 * 删除数据（逻辑删除）
	 * 
	 * @param question
	 * @return
	 */
	public void delete(long id) {
		adminRoleDao.delete(id);
		adminMenuDao.delete(id);//删除角色下的权限
	}

	/**
	 * 单条数据增加
	 * 
	 * @param adminRole
	 * @return
	 */
	public long add(AdminRole adminRole) {
		long id=adminRole.getId();
		if (id> 0) {
			adminRoleDao.updateById(adminRole);
			adminMenuDao.delete(id);//删除角色下的权限
		} else {
			id=adminRoleDao.add(adminRole);
		}
		return id;

	}

	/**
	 * 批量数据增加
	 * 
	 * @param adminRole
	 * @return
	 */
	public void addTrainRecordBatch(List<AdminRole> list) {
		adminRoleDao.addTrainRecordBatch(list);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public AdminRole getAdminRoleById(long id) {
		return adminRoleDao.getAdminRoleById(id);
	}

	/**
	 * 修改单条数据
	 * 
	 * @param id
	 * @return
	 */
	public void updateById(AdminRole adminRole) {
		adminRoleDao.updateById(adminRole);
		adminMenuDao.delete(adminRole.getId());//删除角色下的权限
	}

	/**
	 * 获取分页数据
	 * 
	 * @param map
	 * @return
	 */
	public Partion getList(Map map) {
		List<AdminRole> list = adminRoleDao.getList(map);
		int totalcount = getTotalCount(map);
		Partion pt = new Partion(map, list, totalcount);
		return pt;
	}

	/**
	 * 获取集合数据，不带分页
	 * 
	 * @param map
	 * @return
	 */
	public List<AdminRole> getAdminRoleList(AdminRole adminRole) {
		return adminRoleDao.getAdminRoleList(adminRole);
	}
     
	
	/**
	 * 获取分页总记录数
	 * 
	 * @param map
	 * @return
	 */
	public int getTotalCount(Map map) {
		return adminRoleDao.getTotalCount(map);
	}

}