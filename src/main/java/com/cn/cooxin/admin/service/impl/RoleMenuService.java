package com.cn.cooxin.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.dao.IAdminMenuDao;
import com.cn.cooxin.admin.dao.IRoleMenuDao;
import com.cn.cooxin.admin.service.IRoleMenuService;
import com.cn.cooxin.code.entity.Partion;

/**
 * Service(角色菜单)
 * 
 * @author airufei
 * @version 2017-02-11
 */
@Service
@SuppressWarnings("all")
public class RoleMenuService implements IRoleMenuService {

	@Resource
	private IRoleMenuDao roleMenuDao;

	@Resource
	private IAdminMenuDao adminMenuDao;

	/**
	 * 删除数据（逻辑删除）
	 * 
	 * @param question
	 * @return
	 */
	public void delete(long id) {
		roleMenuDao.delete(id);
	}

	/**
	 * 单条数据增加
	 * 
	 * @param roleMenu
	 * @return
	 */
	public void add(RoleMenu roleMenu) {
		if (roleMenu.getId() > 0) {
			roleMenuDao.delete(roleMenu.getRoleid());
		}
		roleMenuDao.add(roleMenu);

	}

	/**
	 * 批量数据增加
	 * 
	 * @param roleMenu
	 * @return
	 */
	public void addTrainRecordBatch(List<RoleMenu> list) {
		roleMenuDao.addTrainRecordBatch(list);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public RoleMenu getRoleMenuById(long id) {
		return roleMenuDao.getRoleMenuById(id);
	}

	/**
	 * 修改单条数据
	 * 
	 * @param id
	 * @return
	 */
	public void updateById(RoleMenu roleMenu) {
		roleMenuDao.updateById(roleMenu);
	}

	/**
	 * 获取分页数据
	 * 
	 * @param map
	 * @return
	 */
	public Partion getList(Map map) {
		List<RoleMenu> list = roleMenuDao.getList(map);
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
	public List<RoleMenu> getRoleMenuList(RoleMenu roleMenu) {
		return roleMenuDao.getRoleMenuList(roleMenu);
	}

	/**
	 * 获取分页总记录数
	 * 
	 * @param map
	 * @return
	 */
	public int getTotalCount(Map map) {
		return roleMenuDao.getTotalCount(map);
	}

	/**
	 * getTree:(递归获取数据)
	 * 
	 * @Author airufei
	 * @param mu
	 * @return
	 */
	public Tree getNextTree(AdminMenu mu,Tree tr, List<RoleMenu> roList) {
		if(tr==null)
		{
			boolean ischecked = false;
			long mid = mu.getId();
			ischecked = isChecked(roList, mid);
			tr = getVTree(mu,ischecked);
		}
		AdminMenu mf = new AdminMenu();
		mf.setFid(mu.getId());// 当前节点Id
		List<AdminMenu> list = adminMenuDao.getAdminMenuList(mf);// 寻找它的子节点
		if (list != null && list.size() > 0) {
			List<Tree> trList = new ArrayList<Tree>();
			for (AdminMenu m : list) {
				mf = new AdminMenu();
				mf.setFid(m.getId());// 当前节点Id
				boolean ischecked = false;
				long mid = m.getId();
				ischecked = isChecked(roList, mid);
                Tree ch = getVTree(m,ischecked);
				List<AdminMenu> isnetx = adminMenuDao.getAdminMenuList(mf);// 寻找它的子节点
				if(isnetx==null||(isnetx!=null&&isnetx.size()<1))
				{
					ch.setState("open");
				}
				trList.add(ch);
				tr.setChildren(trList);
				getNextTree(m,ch,roList);
			}
			
		}else
		{
			tr.setState("open");
		}
		return tr;
	}

	

	/**
	 * isChecked:(当前菜单是否选中)
	 * @Author airufei
	 * @param roList
	 * @param mid 菜单Id
	 * @return
	 */
	private boolean isChecked(List<RoleMenu> roList,  long mid) {
		boolean ischecked = false;
		if (roList != null) {
			for (RoleMenu r : roList)// 当前节点是否需要选中
			{
				if (mid == r.getMenuid()) {
					ischecked = true;
				}
			}
		}
		return ischecked;
	}

	/**
	 * getTreeList:(获取完整树结构)
	 * 
	 * @Author airufei
	 * @return
	 */
	public List<Tree> getTreeList(long roleId) {
		List<Tree> list = null;
		AdminMenu mf = new AdminMenu();
		mf.setRoleId(roleId);
		mf.setLevel(1);
		List<AdminMenu> li = adminMenuDao.getAdminMenuList(mf);//顶级节点
		if (li != null && li.size() > 0) {
			List<RoleMenu> roList = null;
			if (roleId > 0) {
				RoleMenu rm = new RoleMenu();
				rm.setRoleid(roleId);
				roList = roleMenuDao.getRoleMenuList(rm);// 获取当前角色下的菜单Id
			}
			list = new ArrayList<Tree>();
			for (AdminMenu m : li) {
			   Tree ch = getNextTree(m,null,roList);
			  
			   list.add(ch);
			}
		}
		return list;

	}

	/**
	 * getVTree:(设置树结构的名称和id)
	 * 
	 * @Author airufei
	 * @param mu
	 * @return
	 */
	public Tree getVTree(AdminMenu mu,boolean ischecked) {
		Tree tr = new Tree();
		if (mu != null) {
			tr.setId(mu.getId());
			tr.setText(mu.getName());
			tr.setChecked(ischecked);
		}
		return tr;
	}
}