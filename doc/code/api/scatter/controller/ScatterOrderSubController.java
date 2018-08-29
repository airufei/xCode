package com.miuzone.api.scatter.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.miuzone.model.scatter.*;
import com.miuzone.base.model.*;
import com.miuzone.utils.*;
import com.miuzone.api.common.CommonService;
import com.miuzone.api.scatter.service.*;
/**
 * ScatterOrderSubController(新智投宝订单子表)
 * @author airufei
 * @version 2018-08-29
 */
@RestController
@RequestMapping("/scatter")
@SuppressWarnings("all")
public class ScatterOrderSubController {

    private static Logger logger = LoggerFactory.getLogger(ScatterOrderSubService.class);
	@Autowired
	private ScatterOrderSubService scatterOrderSubService;
	@Autowired
    private CommonService commonService;

	/**
	 * getList:(获取新智投宝订单子表分页查询接口)
	 * @Author airufei
	 * @param request
	 * @param parms
	 * @return
	 */
	@RequestMapping("/getList")
	public DataReturn getList(HttpServletRequest request, String parms){
		DataReturn dataReturn = new DataReturn();
        dataReturn.setCode(ResultCode.FAILURE);
	   try {
	        logger.info("getList:(获取新智投宝订单子表分页查询接口) 开始  parms={}", parms);
            if (StringUtil.isBlank(parms)) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            JSONObject json = JSONObject.parseObject(parms);
            if (json == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            int pageNo = json.getIntValue("pageNo");
            int pageSize = json.getIntValue("pageSize");
            JSONObject param = StringUtil.getPageJSONObject(pageNo, pageSize);
            param.putAll(json);
            Partion pt = scatterOrderSubService.getList(param);
            List<ScatterOrderSub> list = null;
            int totalCount = 0;
            if (pt != null) {
                list = (List<ScatterOrderSub>) pt.getList();
                totalCount = pt.getPageCount();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            dataReturn.setData(jsonObject);
            dataReturn.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            dataReturn.setMessage("服务繁忙，请稍后再试");
            String msg="getList:(获取新智投宝订单子表分页查询接口) 异常====>"+ StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("getList", parms, JSON.toJSONString(dataReturn), msg, this.getClass());
            e.printStackTrace();
        }
        logger.info("getList:(获取新智投宝订单子表分页查询接口) 结束  parms={}", parms);
        return dataReturn;
	}

     /**
     * getScatterOrderSubList:(获取新智投宝订单子表不分页查询接口)
     * @Author airufei
     * @param request
     * @param parms
     * @return
     */
    @RequestMapping("getscatterOrderSubList")
    public DataReturn getScatterOrderSubList(HttpServletRequest request, String parms) {
        DataReturn dataReturn = new DataReturn();
        dataReturn.setCode(ResultCode.FAILURE);
        try {
            logger.info("getScatterOrderSubList:(获取新智投宝订单子表不分页查询接口) 开始  parms={}", parms);
            if (StringUtil.isBlank(parms)) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            JSONObject json = JSONObject.parseObject(parms);
            if (json == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            ScatterOrderSub scatterOrderSub=json.toJavaObject(ScatterOrderSub.class);
            if (scatterOrderSub == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            List<ScatterOrderSub> list= scatterOrderSubService.getScatterOrderSubList(scatterOrderSub);
            dataReturn.setData(list);
            dataReturn.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            dataReturn.setMessage("服务器繁忙，请稍后再试");
            String msg="getScatterOrderSubList:(获取新智投宝订单子表不分页查询接口)====>"+ StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("getScatterOrderSubList", parms, JSON.toJSONString(dataReturn), msg, this.getClass());
            e.printStackTrace();
        }
        logger.info("getScatterOrderSubList:(获取新智投宝订单子表不分页查询接口) 结束  parms={},", parms);
        return dataReturn;
    }


 /**
     * getScatterOrderSubByNo:(查询新智投宝订单子表单条数据接口-带缓存)
     * @Author airufei
     * @param request
     * @param parms
     * @return
     */
    @RequestMapping("getscatterOrderSub")
    public DataReturn getScatterOrderSubByNo(HttpServletRequest request, String parms) {
        DataReturn dataReturn = new DataReturn();
        dataReturn.setCode(ResultCode.FAILURE);
        try {
            logger.info("getScatterOrderSubByNo:(查询新智投宝订单子表单条数据接口-带缓存) 开始  parms={}", parms);
            if (StringUtil.isBlank(parms)) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            JSONObject json = JSONObject.parseObject(parms);
            if (json == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            ScatterOrderSub scatterOrderSub=json.toJavaObject(ScatterOrderSub.class);
            if (scatterOrderSub == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            ScatterOrderSub retscatterOrderSub= scatterOrderSubService.getScatterOrderSub(scatterOrderSub);
            dataReturn.setData(retscatterOrderSub);
            dataReturn.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            dataReturn.setMessage("服务器繁忙，请稍后再试");
            String msg="getScatterOrderSubByNo:(查询新智投宝订单子表单条数据接口-带缓存)  异常====>"+StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("getScatterOrderSubByNo", parms, JSON.toJSONString(dataReturn), msg, this.getClass());
            e.printStackTrace();
        }
        logger.info("getScatterOrderSub:getScatterOrderSubByNo:(查询新智投宝订单子表单条数据接口-带缓存) 结束  parms={},", parms);
        return dataReturn;
    }

     /**
     * getScatterOrderSub:(查询新智投宝订单子表单条数据接口)
     * @Author airufei
     * @param request
     * @param parms
     * @return
     */
    @RequestMapping("getscatterOrderSub")
    public DataReturn getScatterOrderSub(HttpServletRequest request, String parms) {
        DataReturn dataReturn = new DataReturn();
        dataReturn.setCode(ResultCode.FAILURE);
        try {
            logger.info("getScatterOrderSub:(查询新智投宝订单子表单条数据接口) 开始  parms={}", parms);
            if (StringUtil.isBlank(parms)) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            JSONObject json = JSONObject.parseObject(parms);
            if (json == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            ScatterOrderSub scatterOrderSub=json.toJavaObject(ScatterOrderSub.class);
            if (scatterOrderSub == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            ScatterOrderSub retscatterOrderSub= scatterOrderSubService.getScatterOrderSub(scatterOrderSub);
            dataReturn.setData(retscatterOrderSub);
            dataReturn.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            dataReturn.setMessage("服务器繁忙，请稍后再试");
             String msg="getScatterOrderSub:(查询新智投宝订单子表单条数据接口) 异常====>"+StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("getScatterOrderSub", parms, JSON.toJSONString(dataReturn), msg, this.getClass());
            e.printStackTrace();
        }
        logger.info("getScatterOrderSub:(查询新智投宝订单子表单条数据接口) 结束  parms={},", parms);
        return dataReturn;
    }

	/**
	 * delete:(逻辑删除新智投宝订单子表数据接口)
	 * @Author airufei
     * @param request
     * @param parms
     * @return
	 */
	@RequestMapping("delete")
	public DataReturn delete(HttpServletRequest request, String parms){
	  DataReturn dataReturn = new DataReturn();
        dataReturn.setCode(ResultCode.FAILURE);
        try {
            logger.info("delete:(逻辑删除新智投宝订单子表数据接口) 开始  parms={}", parms);
            if (StringUtil.isBlank(parms)) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            JSONObject json = JSONObject.parseObject(parms);
            if (json == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            Long id = json.getLong("id");
            if (id != null && id > 0) {
                scatterOrderSubService.delete(id);
                dataReturn.setMessage("删除成功");
                dataReturn.setCode(ResultCode.SUCCESS);
            } else {
                dataReturn.setMessage("请选择需要删除的数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg="delete:(逻辑删除新智投宝订单子表数据接口) error===>" + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("delete", parms, JSON.toJSONString(dataReturn), msg, this.getClass());
            dataReturn.setMessage("服务器繁忙，请稍后再试");
        }
        logger.info("delete:(逻辑删除新智投宝订单子表数据接口) 结束  parms={}", parms);
        return dataReturn;
	}
	
	/**
	 * save:(保存新智投宝订单子表数据接口)
	 * @Author airufei
     * @param request
     * @param parms
     * @return
	 */
	@RequestMapping(value = "save")
    public DataReturn save(HttpServletRequest request, String parms) {
		DataReturn dataReturn = new DataReturn();
        dataReturn.setCode(ResultCode.FAILURE);
        try {
            logger.info("save:(保存新智投宝订单子表数据接口) 开始  parms={}", parms);
            if (StringUtil.isBlank(parms)) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            JSONObject json = JSONObject.parseObject(parms);
            if (json == null) {
                dataReturn.setMessage("参数为空");
                return dataReturn;
            }
            ScatterOrderSub scatterOrderSub=json.toJavaObject(ScatterOrderSub.class);
            // 无保存内容
            if (scatterOrderSub == null) {
                dataReturn.setMessage("无保存内容");
                return dataReturn;
            }
            scatterOrderSub.setCreateTime(new Date());
            scatterOrderSub.setUpdateTime(new Date());
            // 保存数据库
            ScatterOrderSub ret =scatterOrderSubService.save(scatterOrderSub);
            if(ret!=null)
            {
              dataReturn.setCode(ResultCode.SUCCESS);
               dataReturn.setMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg="save:(保存新智投宝订单子表数据接口) error===>" + StringUtil.getExceptionMsg(e);
            logger.error(msg);
            commonService.sendDingMessage("save", parms, JSON.toJSONString(dataReturn), msg, this.getClass());
            dataReturn.setMessage("服务器繁忙，请稍后再试");
            return dataReturn;
        }
        logger.info("save:(保存新智投宝订单子表数据接口) 结束  parms={}", parms);
        return dataReturn;
	}

}