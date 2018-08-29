package com.miuzone.base.scatter.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.miuzone.model.scatter.*;
import com.miuzone.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.miuzone.base.common.CacheHelperService;
import com.miuzone.base.scatter.dao.ScatterOrderSubDao;
/**
 * Service(新智投宝订单子表)
 * @author airufei
 * @version 2018-08-29
 */
@Service
@SuppressWarnings("all")
public class ScatterOrderSubHelperService  {

	@Autowired
	private ScatterOrderSubDao scatterOrderSubDao;
	@Autowired
    private CacheHelperService cacheHelperService;
    private static Logger logger = LoggerFactory.getLogger(ScatterOrderSubService.class);
	  /**
	   * 获取分页总记录数
	   * @param map
	   * @return
	   */
	   public int  getTotalCount(JSONObject map)
	   {
	      int resCount=0;
	      Integer totalCount =scatterOrderSubDao.getTotalCount(map);
	      if(totalCount!=null)
	      {
	        resCount=totalCount;
	      }
	      return resCount;
	   }


    /*
     * save(保存新智投宝订单子表)
     * @param scatterOrderSub
     * @author airufei
     * @date 2018/1/30 14:59
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public ScatterOrderSub save(ScatterOrderSub scatterOrderSub) {
        if (scatterOrderSub == null) {
            return scatterOrderSub;
        }
        if (scatterOrderSub.getId() != null && scatterOrderSub.getId() > 0) {
            updateById(scatterOrderSub);
        } else {
            scatterOrderSubDao.add(scatterOrderSub);
        }
        return scatterOrderSub;
    }

     /**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	 public ScatterOrderSub getScatterOrderSubById (long id)
	 {
	      return scatterOrderSubDao.getScatterOrderSubById(id);
	 }

    /**
	 * 获取单条数据
	 * @param scatterOrderSub
	 * @author airufei
	 * @return
	 */
	 public ScatterOrderSub getSignleScatterOrderSub(ScatterOrderSub scatterOrderSub)
	 {
	      return scatterOrderSubDao.getSignleScatterOrderSub(scatterOrderSub);
	 }

    /**
	   * getscatterOrderSubByNo(获取新智投宝订单子表单条数据--带缓存)
	   * @param isCache
	   * @param ScatterOrderSubNo
	   * @author airufei
	   * @return
	*/
	public ScatterOrderSub getScatterOrderSubByNo(String ScatterOrderSubNo, boolean isCache)
	{
	    ScatterOrderSub ret=null;
	    if (StringUtil.isBlank(ScatterOrderSubNo)) {
            return ret;
        }
        String key = CacheConstantUtil.ORDER_BASE_ +"getScatterOrderSubByNo"+ ScatterOrderSubNo;
        String redisCache = null;
        if (isCache) {
            redisCache = cacheHelperService.getRedisCache(ScatterOrderSubNo);
        }
        if (StringUtil.isNotBlank(redisCache)) {
            ret = JSONObject.parseObject(redisCache, ScatterOrderSub.class);
        }
        if (ret != null) {
            return ret;
        }
        ScatterOrderSub parms=new ScatterOrderSub();
        parms.setScatterOrderSubNo(ScatterOrderSubNo);
        ret=getSignleScatterOrderSub(parms);
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
	  public void updateById(ScatterOrderSub scatterOrderSub)
	  {
	     scatterOrderSubDao.updateById(scatterOrderSub);
	  }
	
}