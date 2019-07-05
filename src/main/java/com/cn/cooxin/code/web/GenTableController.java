/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.cooxin.code.web;

import java.sql.Array;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.cooxin.code.entity.GenConfig;
import com.cn.cooxin.code.entity.GenTable;
import com.cn.cooxin.code.entity.GenTableColumn;
import com.cn.cooxin.code.entity.Partion;
import com.cn.cooxin.code.service.GenTableService;
import com.cn.cooxin.code.service.TableColumnService;
import com.cn.cooxin.util.GenUtils;
import com.cn.cooxin.util.JsonUtil;
import com.cn.cooxin.util.SpringMvcUtil;
import com.cn.cooxin.util.StringUtil;

/**
 * 业务表Controller
 *
 *
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "/admin/codetable")
@SuppressWarnings("all")
public class GenTableController {

    private static Logger logger = Logger.getLogger(GenTableController.class);
    @Autowired
    private GenTableService genTableService;

    @Autowired
    private TableColumnService tableColumnService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        return "admin/code/tableList";
    }

    @RequestMapping("")
    public String indexs(HttpServletRequest request, Model model) {
        return "admin/code/tableList";
    }

    @RequestMapping("/selectTbale")
    public String selectTbale(String tableName, HttpServletRequest request,
                              Model model) {
        return "admin/code/selectTable";
    }

    /**
     * getTable:(获取当前数据库的数据表)
     *
     * @param request
     * @param tableName
     * @param model
     * @return
     * @Author airufei
     */
    @ResponseBody
    @RequestMapping("/getTable")
    public List<GenTable> getTable(HttpServletRequest request,
                                   String tableName, Model model) {
        GenTable tb = new GenTable();
        if (StringUtil.isNoneBlank(tableName)) {
            tb.setName(tableName);
        }
        tb.setDbName("mysql");
        List<GenTable> list = genTableService.findTableListFormDb(tb);
        return list;
    }

    /**
     * list:(获取表信息分页)
     *
     * @param genTable
     * @param request
     * @param response
     * @param model
     * @return
     * @Author airufei
     */
    @ResponseBody
    @RequestMapping(value = {"/list", ""})
    public JSONObject list(GenTable genTable, HttpServletRequest request,
                           HttpServletResponse response, Model model) {
        JSONObject result = new JSONObject();
        String page = request.getParameter("page");
        String pageSizeStr = request.getParameter("rows");
        Map parms = SpringMvcUtil.getPageMap(page, pageSizeStr);// 设置分页参数
        Partion pt = genTableService.getList(parms);
        result.put("rows", pt.getList());
        result.put("total", pt.getTotalCount());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/isExsitTable")
    public Map isExsitTable(String tableName, HttpServletRequest request,
                            Model model) {
        String msg = "";
        Map result = new HashMap();
        boolean res = true;
        try {
            if (StringUtil.isBlank(tableName)) {
                msg = "没有选中的表";
                res = false;
            }
            if (res) {
                res = genTableService.checkTableName(tableName);
                if (!res) {
                    msg = tableName + " 表已经存在！请重新选择其他数据库表";
                }
            }

        } catch (Exception e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
            res = false;
        } catch (Error e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
            res = false;
        }
        result.put("result", res);
        result.put("msg", msg);
        return result;
    }

    /**
     * editTable:(去往编辑表信息页面)
     *
     * @param genTable
     * @param request
     * @param model
     * @return
     * @Author airufei
     */
    @RequestMapping(value = "/editTable")
    public String editTable(GenTable genTable, HttpServletRequest request,
                            Model model) {
        String msg = "";
        GenTable retTable = null;
        boolean res = true;
        if (genTable == null) {
            msg = "错误";
            res = false;
        }
        // 获取物理表字段
        else {
            retTable = genTableService.getTable(genTable);// 查询配置表
            if (retTable == null || (retTable != null && retTable.getId() < 1)) {
                retTable = genTableService.getTableFormDb(genTable);// 查询物理表
            }
            res = true;
        }
        GenConfig con = GenUtils.getConfig();
        model.addAttribute("tb", retTable);
        model.addAttribute("res", res);
        model.addAttribute("msg", msg);
        return "admin/code/tableEdit";
    }

    /**
     * ediTtableColumn:(去往编辑列信息页面)
     *
     * @param col
     * @param request
     * @param model
     * @return
     * @Author airufei
     */
    @RequestMapping(value = "/ediTtableColumn")
    public String ediTtableColumn(String tableName, HttpServletRequest request,
                                  Model model) {
        String msg = "";
        if (StringUtil.isBlank(tableName)) {
            return "表名不能为空！";
        }
        GenTableColumn col = new GenTableColumn();
        col.setTableName(tableName);
        List<GenTableColumn> colList = tableColumnService.getTableColumnList(col);//配置表
        if (colList == null || (colList != null && colList.size() < 1)) {
            colList = tableColumnService.getList(col);//物理表
        }
        List<GenTableColumn> newList = new ArrayList();
        for (GenTableColumn con : colList) {
            String sqlType = con.getJdbcType();
            if (sqlType != null && (sqlType.contains("int"))) {
                con.setJavaType("java.lang.Integer");
            } else if (sqlType != null && sqlType.contains("bigint")) {
                con.setJavaType("java.lang.Long");
            } else if (sqlType != null && (sqlType.contains("datetime") || sqlType.contains("date"))) {
                con.setJavaType("java.util.Date");
            } else if (sqlType != null && (sqlType.contains("decimal"))) {
                con.setJavaType("java.math.BigDecimal");
            } else {
                con.setJavaType("java.lang.String");
            }
            newList.add(con);
        }
        GenConfig con = GenUtils.getConfig();
        model.addAttribute("colList", newList);
        model.addAttribute("config", con);
        return "admin/code/tableColumnEdit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Map save(@RequestBody List<GenTable> list,
                    HttpServletRequest request, Model model) {
        String msg = "";
        Map result = new HashMap();
        boolean res = true;
        try {
            if (list == null || (list != null && list.size() < 1)) {
                msg = "无保存内容";
                res = false;
            }
            GenTable genTable = list.get(0);
            // 无保存内容
            if (genTable == null
                    || (genTable != null && StringUtil.isBlank(genTable
                    .getName()))) {
                msg = "无保存内容";
                res = false;
            }
            if (res) {
                genTableService.save(genTable);
                res = true;
                msg = "保存业务表'" + genTable.getName() + "'成功";
            }

        } catch (Exception e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
        } catch (Error e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
        }
        result.put("result", res);
        result.put("msg", msg);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/saveColumn")
    public Map saveColumn(@RequestBody List<GenTableColumn> list,
                          HttpServletRequest request, Model model) {
        String msg = "";
        Map result = new HashMap();
        boolean res = true;
        try {
            // 无保存内容
            if (list == null || (list != null && list.size() <= 0)) {
                msg = "无保存内容";
                res = false;
            }
            if (res) {
                JSONObject json = tableColumnService.add(list);
                res = json.getBooleanValue("res");
                msg = json.getString("msg");
            }

        } catch (Exception e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
        } catch (Error e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
        }
        result.put("result", res);
        result.put("msg", msg);
        return result;
    }

    /**
     * delete:(删除数据-物理删除)
     *
     * @param request
     * @param model
     * @return
     * @Author airufei
     */
    @ResponseBody
    @RequestMapping("/deleted")
    public Map delete(Long id, HttpServletRequest request, Model model) {
        String msg = "删除成功";
        Map result = new HashMap();
        boolean res = true;
        try {
            if (id != null && id > 0) {
                genTableService.deletedById(id);
                tableColumnService.deleteColumnByTableId(id);
            } else {
                msg = "请选择需要删除的数据";
                res = false;
            }
        } catch (Exception e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
            res = false;
        } catch (Error e) {

            logger.error("GenTableController:error===>" + e);
            msg = "服务器繁忙，请稍后再试";
            res = false;
        }
        result.put("result", res);
        result.put("msg", msg);
        return result;
    }

}
