/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.service;

import java.util.ArrayList;
import java.util.List;

import com.cn.cooxin.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cn.cooxin.code.dao.IGenDataBaseDictDao;
import com.cn.cooxin.code.dao.IGenTableColumnDao;
import com.cn.cooxin.code.dao.IGenTableDao;
import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.GenTableColumn;
import com.cn.cooxin.util.GenUtils;

/**
 * 业务表Service
 *
 *
 * @version 2013-10-15
 */
@SuppressWarnings("all")
@Service
public class TableColumnService {


    @Autowired
    private IGenTableDao genTableDao;

    @Autowired
    private IGenTableColumnDao genTableColumnDao;

    @Autowired
    private IGenDataBaseDictDao genDataBaseDictDao;

    private static Logger logger = LoggerFactory.getLogger(TableColumnService.class);

    /**
     * getList:(根据表信息获取表的列，并初始化表列信息-----配置表列)
     *
     * @param col
     * @return
     * @Author airufei
     */
    public List<GenTableColumn> getTableColumnList(GenTableColumn col) {
        List<GenTableColumn> list = genTableColumnDao.getList(col);
        return list;
    }

    /**
     * getList:(根据表信息获取表的列，并初始化表列信息----物理表)
     *
     * @param col
     * @return
     * @Author airufei
     */
    public List<GenTableColumn> getList(GenTableColumn col) {
        List<GenTableColumn> list = genDataBaseDictDao.getTableColumnList(col);
        if (list != null) {
            GenTable gt = new GenTable();
            gt.setName(col.getTableName());
            GenTable table = genTableDao.getTable(gt);//根据表名查询表Id
            List<GenTableColumn> resList = new ArrayList();
            for (GenTableColumn item : list) {
                GenUtils.initColumnField(item);//初始化表列信息
                if (table != null) {
                    item.setTableId(table.getId());
                    item.setTableName(table.getName());
                }
                // 获取主键
                List<String> listPK = genDataBaseDictDao.getTablePK(item);
                for (String str : listPK) {
                    if (item.getName().contains(str)) {
                        item.setIsPk("0");
                    }
                }
                resList.add(item);
            }
        }
        return list;
    }

    /**
     * add:(添加表的列信息)
     *
     * @param list
     * @Author airufei
     */
    @Transactional(readOnly = false)
    public JSONObject add(List<GenTableColumn> list) {
        JSONObject json = new JSONObject();
        boolean res = true;
        json.put("msg", "成功");
        json.put("res", res);
        if (list == null || list.size() <= 0) {
            json.put("msg", "参数为空");
            json.put("res", false);
            return json;
        }
        for (GenTableColumn item : list) {
            String isInsertRequiredField = item.getIsInsertRequiredField();
            String isUpdateRequiredField = item.getIsUpdateRequiredField();
            if (StringUtil.isBlank(isInsertRequiredField)) {
                json.put("msg", "必须插入字段不能为空");
                json.put("res", false);
                return json;
            }
            if (StringUtil.isBlank(isUpdateRequiredField)) {
                json.put("msg", "必须修改字段不能为空");
                json.put("res", false);
                return json;
            }
            if (item != null && item.getTableId() > 0) {
                if (item.getId() > 0) {
                    genTableColumnDao.updateById(item);
                } else {
                    genTableColumnDao.add(item);
                }

            }
            String str = JSONObject.toJSONString(item);
            logger.info(str);
        }
        return json;
    }

    public void deleteColumnByTableId(Long id) {
        GenTableColumn col = new GenTableColumn();
        col.setTableId(id);
        genTableColumnDao.deleteColumnByTableId(col);
    }
}
