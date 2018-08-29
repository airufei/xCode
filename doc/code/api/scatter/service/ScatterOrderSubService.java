package com.miuzone.api.scatter.service;
import java.util.List;
import java.util.Map;
import com.miuzone.config.HttpBasicAuthConfig;
import com.miuzone.base.model.*;
import com.miuzone.model.scatter.*;
import com.miuzone.utils.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONObject;
/**
 * Service(新智投宝订单子表)
 * @author airufei
 * @version 2018-08-29
 */
@SuppressWarnings("all")

@FeignClient(value = "${base-service.application}", configuration = HttpBasicAuthConfig.class)// 配置远程服务名以及自定义权限验证配置
@RequestMapping("/server/scatter/")// 配置远程服务路径

public interface ScatterOrderSubService  {

    /**
     * list:(查询新智投宝订单子表 带分页数据)
     * @Author airufei
     * @return
     */
    @RequestMapping(value = "getList", consumes = MediaType.APPLICATION_JSON_VALUE)
    Partion getList(@RequestBody JSONObject map);


    /**
     * getScatterOrderSubList:(查询新智投宝订单子表 不带分页数据)
     * @Author airufei
     * @return
     */
    @RequestMapping(value = "getscatterOrderSubList", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ScatterOrderSub> getScatterOrderSubList(@RequestBody ScatterOrderSub scatterOrderSub);


     /**
     * getScatterOrderSub:(查询新智投宝订单子表单个实体数据)
     * @Author airufei
     * @return
     */
    @RequestMapping(value = "getscatterOrderSub", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ScatterOrderSub getScatterOrderSub(@RequestBody ScatterOrderSub scatterOrderSub);

    /**
	   * getscatterOrderSubByNo(获取新智投宝订单子表单条数据--带缓存)
	   * @param ScatterOrderSubNo
	   * @param isCache
	   * @return
	 */
	@RequestMapping(value = "getscatterOrderSubByNo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public getScatterOrderSubByNo(@RequestParam("ScatterOrderSubNo") String ScatterOrderSubNo,@RequestParam("isCache") boolean isCache);

    /**
     * save:(保存新智投宝订单子表数据)
     * @Author airufei
     * @return
     */
    @RequestMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    ScatterOrderSub save(@RequestBody ScatterOrderSub scatterOrderSub);


    /**
     * delete:(删除新智投宝订单子表数据)
     * @Author airufei
     * @return
     */
    @RequestMapping(value = "delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean delete(@RequestBody long id);
	
}