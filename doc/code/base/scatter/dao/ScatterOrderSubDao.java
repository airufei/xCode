package com.miuzone.base.scatter.dao;

import com.miuzone.model.scatter.*;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
/**
 * 新智投宝订单子表DAO接口
 * @author airufei
 * @version 2018-08-29
 */
@SuppressWarnings("all")
public interface ScatterOrderSubDao {
	
	/**
	 * 删除数据（逻辑删除）
	 * @param question
	 * @return
	 */
	public void delete(long id);
    /**
	 * 单条数据增加
	 * @param scatterOrderSub
	 * @return
	 */
	public void add(ScatterOrderSub scatterOrderSub);

    /**
	 * 批量数据增加
	 * @param scatterOrderSub
	 * @return
	 */
	 public void addTrainRecordBatch(List<ScatterOrderSub> list);

     /**
	 * 根据ID获取单条数据
	 * @param id
	 * @return
	 */
	 public ScatterOrderSub getScatterOrderSubById (long id);

      /**
	 * 修改单条数据
	 * @param id
	 * @return
	 */
	  public void updateById(ScatterOrderSub scatterOrderSub);

	  /**
	   * 获取分页数据
	   * @param map
	   * @return
	   */
	   public List<ScatterOrderSub>  getList(JSONObject map);
	   
	   
	   /**
	   * 获取集合数据，不带分页
	   * @param map
	   * @return
	   */
	   public List<ScatterOrderSub>  getScatterOrderSubList(ScatterOrderSub scatterOrderSub);

	   /**
	   * 获取单条数据
	   * @param map
	   * @return
	   */
	   public ScatterOrderSub  getSignleScatterOrderSub(ScatterOrderSub scatterOrderSub);

	  /**
	   * 获取分页记录总数
	   * @param map
	   * @return
	   */
	   public Integer  getTotalCount(Map map);
	   
}