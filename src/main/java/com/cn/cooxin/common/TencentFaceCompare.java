package com.cn.cooxin.common;


import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cn.cooxin.util.YoutuUtil;

/**
 * @author Administrator
 *
 */
@Service(value="tencentFace")
@SuppressWarnings("all")
public class TencentFaceCompare {
	
	private String m_appid;
	private String m_secret_id;
	private String m_secret_key;
    public JSONObject getFaceCompare(String imageA,String imageB)
    {
    	JSONObject json=null;
    	YoutuUtil youtu=new YoutuUtil(m_appid, m_secret_id, m_secret_key, YoutuUtil.API_TENCENTYUN_END_POINT);
		return json;
    	
    }
	
}
