package com.cn.cooxin.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.dao.IBaseLogDao;
import com.cn.cooxin.admin.service.IBaseLogService;
import com.cn.cooxin.code.entity.Partion;

/**
 * Service(日志管理)
 * @author airufei
 * @version 2017-02-25
 */
@Service
@SuppressWarnings("all")
public class BaseLogService implements IBaseLogService {

	@Resource
	private IBaseLogDao baseLogDao;
	
	/**
	 * 删除数据（逻辑删除）
	 * @param question
	 * @return
	 */
	public void delete(long id)
	{
	    baseLogDao.delete(id);
	}
    /**
	 * 单条数据增加
	 * @param baseLog
	 * @return
	 */
	public void add(BaseLog baseLog)
	{
	    if(baseLog.getId()>0)
	    {
	       baseLogDao.updateById(baseLog);
	    }else
	    {
	       baseLogDao.add(baseLog);
	    }
	   
	}

    /**
	 * 批量数据增加
	 * @param baseLog
	 * @return
	 */
	 public void addTrainRecordBatch(List<BaseLog> list)
	 {
	     baseLogDao.addTrainRecordBatch(list);
	 }

     /**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	 public BaseLog getBaseLogById (long id)
	 {
	      return baseLogDao.getBaseLogById(id);
	 }
      /**
	 * 修改单条数据
	 * @param id
	 * @return
	 */
	  public void updateById(BaseLog baseLog)
	  {
	     baseLogDao.updateById(baseLog);
	  }

	  /**
	   * 获取分页数据
	   * @param map
	   * @return
	   */
	   public Partion  getList(Map map)
	   {
	      List<BaseLog> list= baseLogDao.getList(map);
	      int totalcount = getTotalCount(map);
		  Partion pt = new Partion(map, list, totalcount);
		  return pt;
	   }
	   
	    /**
	   * 获取集合数据，不带分页
	   * @param map
	   * @return
	   */
	   public List<BaseLog>  getBaseLogList(BaseLog baseLog)
	   {
	      return baseLogDao.getBaseLogList(baseLog);
	   }
	  /**
	   * 获取分页总记录数
	   * @param map
	   * @return
	   */
	   public int  getTotalCount(Map map)
	   {
	      return baseLogDao.getTotalCount(map);
	   }
	
}