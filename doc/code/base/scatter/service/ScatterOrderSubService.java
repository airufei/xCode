package com.miuzone.base.scatter.service;


import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.miuzone.model.scatter.*;
import com.miuzone.base.model.*;
import com.miuzone.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.miuzone.base.scatter.dao.ScatterOrderSubDao;
import com.miuzone.base.common.CommonService;
/**
 * Service(新智投宝订单子表)
 * @author airufei
 * @version 2018-08-29
 */
@RestController
@RequestMapping(value = "/server/scatter/")
@SuppressWarnings("all")
public class ScatterOrderSubService  {

	@Autowired
	private ScatterOrderSubDao scatterOrderSubDao;
	@Autowired
	private ScatterOrderSubHelperService  scatterOrderSubHelperService;
    @Autowired
    private CommonService commonService;
     private static Logger logger = LoggerFactory.getLogger(ScatterOrderSubService.class);
      /**
	   * getList(获取新智投宝订单子表带分页数据-服务)
       * @author airufei
       * @param json
	   * @return
	   */
	   @RequestMapping("getList")
	   public Partion  getList(@RequestBody JSONObject json)
	   {
          logger.info("getList(获取新智投宝订单子表带分页数据-服务) 开始 json={}", json);
          if(json==null||json.size()<1)
          {
             return null;
          }
          Partion pt =null;
          try
           {
              int totalcount =scatterOrderSubHelperService.getTotalCount(json);
              List<ScatterOrderSub> list= null;
              if(totalcount>0)
              {
                list= scatterOrderSubDao.getList(json);
              }
               pt = new Partion(json, list, totalcount);
		   } catch (Exception e) {
            String msg = "getList(获取新智投宝订单子表 异常 " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            String parms = null;
            if (json != null) {
                parms = json.toString();
            }
            commonService.sendDingMessage("base-service[getList]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
		  logger.info("getList(获取新智投宝订单子表带分页数据-服务) 结束 ");
		  return pt;
	   }

	   /**
	   * getScatterOrderSubList(获取新智投宝订单子表 不带分页数据-服务)
       * @author airufei
       * @param scatterOrderSub
	   * @return
	   */
	   @RequestMapping("getscatterOrderSubList")
	   public List<ScatterOrderSub>  getScatterOrderSubList(@RequestBody ScatterOrderSub scatterOrderSub)
	   {
	      String parms = JSON.toJSONString(scatterOrderSub);
	      List<ScatterOrderSub> list=null;
          logger.info("getScatterOrderSubList(获取新智投宝订单子表 不带分页数据-服务) 开始 parms={}", parms);
          if(scatterOrderSub==null)
          {
             return list;
          }
          try {
	          list=scatterOrderSubDao.getScatterOrderSubList(scatterOrderSub);
	       } catch (Exception e) {
            String msg = "getScatterOrderSubList 异常 " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("base-service[getScatterOrderSubList]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	      logger.info("getScatterOrderSubList(获取新智投宝订单子表 不带分页数据-服务) 结束");
	      return list;
	   }


    /**
	 * save (保存新智投宝订单子表 数据-服务)
	 * @param scatterOrderSub
     * @author airufei
	 * @return
	 */
    @RequestMapping("save")
	public ScatterOrderSub save(@RequestBody ScatterOrderSub scatterOrderSub)
	{
	    String parms = JSON.toJSONString(scatterOrderSub);
        logger.info("save (保存新智投宝订单子表 数据-服务) 开始 parms={}", parms);
        if (scatterOrderSub == null) {
            return scatterOrderSub;
        }
        try {
	         scatterOrderSub=scatterOrderSubHelperService.save(scatterOrderSub);
	     } catch (Exception e) {
            String msg = "save (保存新智投宝订单子表 数据-服务) " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("base-service[save]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	    logger.info("save (保存新智投宝订单子表 数据-服务) 结束");
	    return  scatterOrderSub;
	}


	   /**
	   * getScatterOrderSub(获取新智投宝订单子表单条数据-服务)
	   * @author airufei
	   * @param scatterOrderSub
	   * @return
	   */
	   @RequestMapping("getscatterOrderSub")
	   public ScatterOrderSub  getScatterOrderSub(@RequestBody ScatterOrderSub scatterOrderSub)
	   {
	      ScatterOrderSub ret=null;
	      String parms = JSON.toJSONString(scatterOrderSub);
	      List<ScatterOrderSub> list=null;
          logger.info("getScatterOrderSub(获取新智投宝订单子表单条数据-服务) 开始 parms={}", parms);
           if(scatterOrderSub==null)
           {
             return ret;
           }
           try{
	            ret=scatterOrderSubHelperService.getSignleScatterOrderSub(scatterOrderSub);
	       } catch (Exception e) {
            String msg = "getScatterOrderSub(获取新智投宝订单子表单条数据-服务) " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("base-service[getScatterOrderSub]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	       logger.info("getScatterOrderSub(获取新智投宝订单子表单条数据-服务) 结束 ");
	      return ret;
	   }


	   /**
	   * getscatterOrderSubByNo(获取新智投宝订单子表单条数据--带缓存)
	   * @param ScatterOrderSubNo
	   * @param isCache
	   * @author airufei
	   * @return
	   */
	   @RequestMapping("getscatterOrderSubByNo")
	   public ScatterOrderSub  getScatterOrderSubByNo(String ScatterOrderSubNo, boolean isCache)
	   {
	      ScatterOrderSub ret=null;
          logger.info("getscatterOrderSubByNo(获取新智投宝订单子表单条数据--带缓存) 开始 ScatterOrderSubNo={},isCache={}", ScatterOrderSubNo,isCache);
           if(ScatterOrderSubNo==null)
           {
             return ret;
           }
          try {
	          ret=scatterOrderSubHelperService.getScatterOrderSubByNo(ScatterOrderSubNo,isCache);
	       } catch (Exception e) {
            String msg = "getscatterOrderSubByNo(获取新智投宝订单子表单条数据--带缓存) " + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            String parms = ScatterOrderSubNo;
            commonService.sendDingMessage("base-service[getscatterOrderSubByNo]", parms, null, msg, this.getClass());
            e.printStackTrace();
          }
	       logger.info("getscatterOrderSubByNo(获取新智投宝订单子表单条数据--带缓存) 单条数据-服务) 结束 "+JSON.toJSONString(ret));
	      return ret;
	   }

    /**
	 * delete(逻辑删除新智投宝订单子表数据-服务)
	 * @param id
	 * @author airufei
	 * @return
	 */
     @RequestMapping("delete")
	 public boolean delete(long id)
	 {
	    logger.info("delete(逻辑删除新智投宝订单子表数据-服务) 开始 id={}", id);
        boolean isSuccess = false;
        if (id < 1) {
            return isSuccess;
        }
        ScatterOrderSub dt = scatterOrderSubHelperService.getScatterOrderSubById(id);
        if (dt == null) {
            return isSuccess;
        }
	    scatterOrderSubDao.delete(id);
	    isSuccess = true;
        logger.info("delete(逻辑删除新智投宝订单子表数据-服务)结束 id={},isSuccess={}", id, isSuccess);
        return isSuccess;
	 }
}