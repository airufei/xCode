package com.cn.cooxin.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.cooxin.admin.entity.*;
import com.cn.cooxin.admin.dao.IDictDao;
import com.cn.cooxin.admin.service.IDictService;
import com.cn.cooxin.code.entity.Dict;
import com.cn.cooxin.code.entity.Partion;

/**
 * Service(字典数据)
 * @author airufei
 * @version 2017-02-26
 */
@Service
@SuppressWarnings("all")
public class DictService implements IDictService {

	@Resource
	private IDictDao dictDao;
	
	/**
	 * 删除数据（逻辑删除）
	 * @param question
	 * @return
	 */
	public void delete(long id)
	{
	    dictDao.delete(id);
	}
    /**
	 * 单条数据增加
	 * @param dict
	 * @return
	 */
	public Dict add(Dict dict)
	{
	    if(dict.getId()>0)
	    {
	       dictDao.updateById(dict);
	    }else
	    {
	       dictDao.add(dict);
	    }
	    return  dict;
	}

    /**
	 * 批量数据增加
	 * @param dict
	 * @return
	 */
	 public void addTrainRecordBatch(List<Dict> list)
	 {
	     dictDao.addTrainRecordBatch(list);
	 }

     /**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	 public Dict getDictById (long id)
	 {
	      return dictDao.getDictById(id);
	 }
      /**
	 * 修改单条数据
	 * @param id
	 * @return
	 */
	  public void updateById(Dict dict)
	  {
	     dictDao.updateById(dict);
	  }

	  /**
	   * 获取分页数据
	   * @param map
	   * @return
	   */
	   public Partion  getList(Map map)
	   {
	      List<Dict> list= dictDao.getList(map);
	      int totalcount = getTotalCount(map);
		  Partion pt = new Partion(map, list, totalcount);
		  return pt;
	   }
	   
	    /**
	   * 获取集合数据，不带分页
	   * @param map
	   * @return
	   */
	   public List<Dict>  getDictList(Dict dict)
	   {
	      return dictDao.getDictList(dict);
	   }
	  /**
	   * 获取分页总记录数
	   * @param map
	   * @return
	   */
	   public int  getTotalCount(Map map)
	   {
	      return dictDao.getTotalCount(map);
	   }
	
}