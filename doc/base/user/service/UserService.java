package com.cn.xmf.base.user.service;


import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cn.xmf.model.user.*;
import com.cn.xmf.base.model.*;
import com.cn.xmf.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cn.xmf.base.user.dao.UserDao;
import com.cn.xmf.base.common.CommonService;
/**
 * Service(用户信息)
 * @author airufei
 * @version 2018-09-11
 */
@RestController
@RequestMapping(value = "/server/user/")
@SuppressWarnings("all")
public class UserService  {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserHelperService  userHelperService;
    @Autowired
    private CommonService commonService;
     private static Logger logger = LoggerFactory.getLogger(UserService.class);
      /**
	   * getList(获取用户信息带分页数据-服务)
       * @author airufei
       * @param json
	   * @return
	   */
	   @RequestMapping("getList")
	   public Partion  getList(@RequestBody JSONObject json)
	   {
          logger.info("getList(获取用户信息带分页数据-服务) 开始 json={}", json);
          if(json==null||json.size()<1)
          {
             return null;
          }
          Partion pt =null;
          try
           {
              int totalcount =userHelperService.getTotalCount(json);
              List<User> list= null;
              if(totalcount>0)
              {
                list= userDao.getList(json);
              }
               pt = new Partion(json, list, totalcount);
		   } catch (Exception e) {
            String msg = "getList(获取用户信息 异常 " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            String parms = null;
            if (json != null) {
                parms = json.toString();
            }
            commonService.sendDingMessage("base-service[getList]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
		  logger.info("getList(获取用户信息带分页数据-服务) 结束 ");
		  return pt;
	   }

	   /**
	   * getUserList(获取用户信息 不带分页数据-服务)
       * @author airufei
       * @param user
	   * @return
	   */
	   @RequestMapping("getUserList")
	   public List<User>  getUserList(@RequestBody User user)
	   {
	      String parms = JSON.toJSONString(user);
	      List<User> list=null;
          logger.info("getUserList(获取用户信息 不带分页数据-服务) 开始 parms={}", parms);
          if(user==null)
          {
             return list;
          }
          try {
	          list=userDao.getUserList(user);
	       } catch (Exception e) {
            String msg = "getUserList 异常 " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("base-service[getUserList]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	      logger.info("getUserList(获取用户信息 不带分页数据-服务) 结束");
	      return list;
	   }


    /**
	 * save (保存用户信息 数据-服务)
	 * @param user
     * @author airufei
	 * @return
	 */
    @RequestMapping("save")
	public User save(@RequestBody User user)
	{
	    String parms = JSON.toJSONString(user);
        logger.info("save (保存用户信息 数据-服务) 开始 parms={}", parms);
        if (user == null) {
            return user;
        }
        try {
	         user=userHelperService.save(user);
	     } catch (Exception e) {
            String msg = "save (保存用户信息 数据-服务) " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("base-service[save]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	    logger.info("save (保存用户信息 数据-服务) 结束");
	    return  user;
	}


	   /**
	   * getUser(获取用户信息单条数据-服务)
	   * @author airufei
	   * @param user
	   * @return
	   */
	   @RequestMapping("getUser")
	   public User  getUser(@RequestBody User user)
	   {
	      User ret=null;
	      String parms = JSON.toJSONString(user);
	      List<User> list=null;
          logger.info("getUser(获取用户信息单条数据-服务) 开始 parms={}", parms);
           if(user==null)
           {
             return ret;
           }
           try{
	            ret=userHelperService.getSignleUser(user);
	       } catch (Exception e) {
            String msg = "getUser(获取用户信息单条数据-服务) " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("base-service[getUser]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	       logger.info("getUser(获取用户信息单条数据-服务) 结束 ");
	      return ret;
	   }


	   /**
	   * getuserByNo(获取用户信息单条数据--带缓存)
	   * @param UserNo
	   * @param isCache
	   * @author airufei
	   * @return
	   */
	   @RequestMapping("getUserByNo")
	   public User  getUserByNo(String UserNo, boolean isCache)
	   {
	      User ret=null;
          logger.info("getuserByNo(获取用户信息单条数据--带缓存) 开始 UserNo={},isCache={}", UserNo,isCache);
           if(UserNo==null)
           {
             return ret;
           }
          try {
	          ret=userHelperService.getUserByNo(UserNo,isCache);
	       } catch (Exception e) {
            String msg = "getuserByNo(获取用户信息单条数据--带缓存) " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            String parms = UserNo;
            commonService.sendDingMessage("base-service[getuserByNo]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	       logger.info("getuserByNo(获取用户信息单条数据--带缓存) 单条数据-服务) 结束 "+JSON.toJSONString(ret));
	      return ret;
	   }

    /**
	 * delete(逻辑删除用户信息数据-服务)
	 * @param id
	 * @author airufei
	 * @return
	 */
     @RequestMapping("delete")
	 public boolean delete(Long id)
	 {
	    logger.info("delete(逻辑删除用户信息数据-服务) 开始 id={}", id);
        boolean isSuccess = false;
        if (id < 1) {
            return isSuccess;
        }
        User dt = userHelperService.getUserById(id);
        if (dt == null) {
            return isSuccess;
        }
	    userDao.delete(id);
	    isSuccess = true;
        logger.info("delete(逻辑删除用户信息数据-服务)结束 id={},isSuccess={}", id, isSuccess);
        return isSuccess;
	 }
}