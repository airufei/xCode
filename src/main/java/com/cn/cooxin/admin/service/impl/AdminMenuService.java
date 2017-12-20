package com.cn.cooxin.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cn.cooxin.util.StringUtil;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.dao.IAdminMenuDao;
import com.cn.cooxin.admin.service.IAdminMenuService;
import com.cn.cooxin.code.entity.Partion;

/**
 * Service(菜单管理)
 * 
 * @author airufei
 * @version 2017-02-11
 */
@Service
@SuppressWarnings("all")
public class AdminMenuService implements IAdminMenuService {

	@Resource
	private IAdminMenuDao adminMenuDao;

	/**
	 * 删除数据（逻辑删除）
	 * 
	 * @param question
	 * @return
	 */
	public void delete(long id) {
		adminMenuDao.delete(id);
		long fid=id;
		adminMenuDao.deleteByFid(id);
	}

	/**
	 * 单条数据增加
	 * 
	 * @param adminMenu
	 * @return
	 */
	public void add(AdminMenu adminMenu) {
		long fid = adminMenu.getFid();
		int isadmin=adminMenu.getIsadmin();
		String url = adminMenu.getUrl();
		int level = 1;
		if (fid > 1) {
			AdminMenu mu = getAdminMenuById(fid);
			if (mu != null) {
				level = mu.getLevel() + 1;
			}
		}
		adminMenu.setLevel(level);
		if (adminMenu.getId() > 0) {
			adminMenuDao.updateById(adminMenu);
			if (StringUtil.isNotBlank(url)&&isadmin==0) {
				adminMenu.setLevel(level + 1);
				adminMenu.setFid(adminMenu.getId());
				adminMenuDao.updateByFId(adminMenu);// 修改下面的按钮级别
			}
		} else {
			adminMenuDao.add(adminMenu);
			long id = adminMenu.getId();
			if (id > 0 && StringUtil.isNotBlank(url)&&isadmin==0) {// 向菜单地址下增加四个个按钮
				adminMenu.setId(0);
				adminMenu.setLevel(level + 1);
				adminMenu.setName("新增按钮");
				adminMenu.setFid(id);
				adminMenu.setIsbutton(1);
				adminMenuDao.add(adminMenu);
				adminMenu.setId(0);
				adminMenu.setIsbutton(2);
				adminMenu.setName("编辑按钮");
				adminMenuDao.add(adminMenu);
				adminMenu.setId(0);
				adminMenu.setIsbutton(3);
				adminMenu.setName("删除按钮");
				adminMenuDao.add(adminMenu);
				adminMenu.setId(0);
				adminMenu.setIsbutton(4);
				adminMenu.setName("查询按钮");
				adminMenuDao.add(adminMenu);
			}
		}

	}

	/**
	 * 批量数据增加
	 * 
	 * @param adminMenu
	 * @return
	 */
	public void addTrainRecordBatch(List<AdminMenu> list) {
		adminMenuDao.addTrainRecordBatch(list);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public AdminMenu getAdminMenuById(long id) {
		return adminMenuDao.getAdminMenuById(id);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public AdminMenu getAdminMenu(AdminMenu adminMenu) {
		AdminMenu ret = null;
		List<AdminMenu> list = adminMenuDao.getAdminMenuList(adminMenu);
		if (list != null && list.size() > 0) {
			ret = list.get(0);
		}
		return ret;
	}

	/**
	 * 修改单条数据
	 * 
	 * @param id
	 * @return
	 */
	public void updateById(AdminMenu adminMenu) {
		adminMenuDao.updateById(adminMenu);
	}

	/**
	 * 获取分页数据
	 * 
	 * @param map
	 * @return
	 */
	public Partion getList(Map map) {
		List<AdminMenu> list = adminMenuDao.getList(map);
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
	public List<AdminMenu> getAdminMenuList(AdminMenu adminMenu) {
		return adminMenuDao.getAdminMenuList(adminMenu);
	}
    

	/**
	 * getNextMenu:(递归获取菜单数据)
	 * 
	 * @Author airufei
	 * @param mu
	 * @return
	 */
	public AdminMenuTree getNextMenu(AdminMenu am,long roleId) {
		AdminMenuTree amTree=new AdminMenuTree();
		long mid = am.getId();
		amTree.setMu(am);
		AdminMenu mf = new AdminMenu();
		mf.setFid(am.getId());// 当前节点Id
		mf.setRoleId(roleId);
		List<AdminMenu> list = adminMenuDao.getAdminMenuList(mf);// 寻找它的子节点
		if (list != null && list.size() > 0) {
			List<AdminMenu> trList = new ArrayList<AdminMenu>();
			for (AdminMenu m : list) {
				trList.add(m);
				amTree.setChildren(trList);
				getNextMenu(m,roleId);
			}
		}
		return amTree;
	}
	
	/**
	 * getMenuList:(获取完整菜单树结构)
	 * 
	 * @Author airufei
	 * @return
	 */
	public List<AdminMenuTree> getMenuList(long roleId) {
		List<AdminMenuTree> list = null;
		AdminMenu mf = new AdminMenu();
		mf.setRoleId(roleId);
		mf.setLevel(1);
		List<AdminMenu> li = adminMenuDao.getAdminMenuList(mf);//顶级节点
		if (li != null && li.size() > 0) {
			list = new ArrayList<AdminMenuTree>();
			for (AdminMenu m : li) {
			AdminMenuTree ch = getNextMenu(m,roleId);
			   list.add(ch);
			}
		}
		return list;

	}
	/**
	 * 获取分页总记录数
	 * 
	 * @param map
	 * @return
	 */
	public int getTotalCount(Map map) {
		return adminMenuDao.getTotalCount(map);
	}

}