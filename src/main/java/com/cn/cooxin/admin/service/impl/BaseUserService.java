package com.cn.cooxin.admin.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.dao.IBaseUserDao;
import com.cn.cooxin.admin.service.IBaseUserService;
import com.cn.cooxin.code.entity.Partion;

/**
 * Service(用户信息)
 * 
 * @author airufei
 * @version 2017-02-13
 */
@Service
@SuppressWarnings("all")
public class BaseUserService implements IBaseUserService {

	@Resource
	private IBaseUserDao baseUserDao;

	/**
	 * 删除数据（逻辑删除）
	 * 
	 * @param question
	 * @return
	 */
	public void delete(long id) {
		baseUserDao.delete(id);
	}

	/**
	 * 单条数据增加
	 * 
	 * @param baseUser
	 * @return
	 */
	public void add(BaseUser baseUser) {
		if (baseUser.getId() > 0) {
			baseUserDao.updateById(baseUser);
		} else {
			baseUserDao.add(baseUser);
		}

	}

	/**
	 * 批量数据增加
	 * 
	 * @param baseUser
	 * @return
	 */
	public void addTrainRecordBatch(List<BaseUser> list) {
		baseUserDao.addTrainRecordBatch(list);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public BaseUser getBaseUserById(long id) {
		return baseUserDao.getBaseUserById(id);
	}

	/**
	 * 修改单条数据
	 * 
	 * @param id
	 * @return
	 */
	public void updateById(BaseUser baseUser) {
		baseUserDao.updateById(baseUser);
	}

	/**
	 * 获取分页数据
	 * 
	 * @param map
	 * @return
	 */
	public Partion getList(Map map) {
		List<BaseUser> list = baseUserDao.getList(map);
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
	public List<BaseUser> getBaseUserList(BaseUser baseUser) {
		return baseUserDao.getBaseUserList(baseUser);
	}

	/**
	 * 获取分页总记录数
	 * 
	 * @param map
	 * @return
	 */
	public int getTotalCount(Map map) {
		return baseUserDao.getTotalCount(map);
	}

	/**
	 * getBaseUser(获取单个用户)
	 * @param baseUser
	 * @return
	 */
	@Override
	public BaseUser getBaseUser(BaseUser baseUser) {
		BaseUser user = null;
		List<BaseUser> list = baseUserDao.getBaseUserList(baseUser);
		if (list != null && list.size() > 0) {
			user = list.get(0);
		}
		return user;
	}

	/**
	 * isExsitBaseUser(获取单个用户)
	 * 
	 * @param baseUser
	 * @return
	 */
	@Override
	public boolean isExsitBaseUser(BaseUser baseUser) {
		boolean result = false;
		List<BaseUser> list = baseUserDao.getBaseUserList(baseUser);
		if (list != null && list.size() > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * updatePwd:(重置密码)
	 * @Author airufei
	 * @param baseUser
	 */
	@Override
	public void updatePwd(BaseUser baseUser) {
		BaseUser base=getBaseUser(baseUser);
		if(base!=null&&base.getId()>0)
		{
			baseUserDao.updatePwd(base);
		}
		
	}

}