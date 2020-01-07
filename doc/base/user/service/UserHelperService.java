package com.cn.xmf.base.user.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cn.xmf.model.user.*;
import com.cn.xmf.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cn.xmf.base.common.CacheHelperService;
import com.cn.xmf.base.user.dao.UserDao;
/**
 * Service(用户信息)
 * @author airufei
 * @version 2018-09-11
 */
@Service
@SuppressWarnings("all")
public class UserHelperService  {

	@Autowired
	private UserDao userDao;
	@Autowired
    private CacheHelperService cacheHelperService;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
	  /**
	   * 获取分页总记录数
	   * @param map
	   * @return
	   */
	   public int  getTotalCount(JSONObject map)
	   {
	      int resCount=0;
	      Integer totalCount =userDao.getTotalCount(map);
	      if(totalCount!=null)
	      {
	        resCount=totalCount;
	      }
	      return resCount;
	   }


    /*
     * save(保存用户信息)
     * @param user
     * @author airufei
     * @date 2018/1/30 14:59
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public User save(User user) {
        if (user == null) {
            return user;
        }
        if (user.getId() != null && user.getId() > 0) {
            updateById(user);
        } else {
            userDao.add(user);
        }
        return user;
    }

     /**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	 public User getUserById (long id)
	 {
	      return userDao.getUserById(id);
	 }

    /**
	 * 获取单条数据
	 * @param user
	 * @author airufei
	 * @return
	 */
	 public User getSingleUser(User user)
	 {
	      return userDao.getSingleUser(user);
	 }

    /**
	   * getuserByNo(获取用户信息单条数据--带缓存)
	   * @param isCache
	   * @param UserNo
	   * @author airufei
	   * @return
	*/
	public User getUserByNo(String UserNo, boolean isCache)
	{
	    User ret=null;
	    if (StringUtil.isBlank(UserNo)) {
            return ret;
        }
        String key = CacheConstantUtil.ORDER_BASE_ +"getUserByNo"+ UserNo;
        String redisCache = null;
        if (isCache) {
            redisCache = cacheHelperService.getRedisCache(UserNo);
        }
        if (StringUtil.isNotBlank(redisCache)) {
            ret = JSONObject.parseObject(redisCache, User.class);
        }
        if (ret != null) {
            return ret;
        }
        User parms=new User();
        parms.setUserNo(UserNo);
        ret=getSingleUser(parms);
        if (ret != null && isCache) {
            cacheHelperService.saveRedis(key, JSON.toJSONString(ret), 60 * 5);
        }
        return ret;
	}
	 /**
	 * 修改单条数据
	 * @param id
	 * @return
	 */
	  public void updateById(User user)
	  {
	     userDao.updateById(user);
	  }
	
}