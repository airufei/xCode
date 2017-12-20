package com.cn.cooxin.admin.service;
import java.util.List;
import java.util.Map;

import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.code.entity.Partion;


/**
 * Service(日志管理)
 * @author airufei
 * @version 2017-02-25
 */
 @SuppressWarnings("all")
public interface IBaseLogService {
	
	/**
	 * 删除数据（逻辑删除）
	 * @param question
	 * @return
	 */
	public void delete(long id);
    /**
	 * 单条数据增加
	 * @param baseLog
	 * @return
	 */
	public void add(BaseLog baseLog);

    /**
	 * 批量数据增加
	 * @param baseLog
	 * @return
	 */
	 public void addTrainRecordBatch(List<BaseLog> list);

     /**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	 public BaseLog getBaseLogById (long id);

     /**
	 * 修改单条数据
	 * @param id
	 * @return
	 */
	  public void updateById(BaseLog baseLog);

	  /**
	   * 获取分页数据
	   * @param map
	   * @return
	   */
	   public Partion  getList(Map map);
	   
	   /**
	   * 获取集合数据，不带分页
	   * @param map
	   * @return
	   */
	   public List<BaseLog>  getBaseLogList(BaseLog baseLog);
	   
	   /**
	   * 获取分页总数
	   * @param map
	   * @return
	   */
	   public int  getTotalCount(Map map);
	
}