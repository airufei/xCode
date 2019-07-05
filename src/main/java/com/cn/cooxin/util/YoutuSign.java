package com.cn.cooxin.util;

import java.util.Random;
public class YoutuSign {

	/**
    Sign    时效性签名
    @param  appId       http://open.youtu.qq.com/上申请的业务ID
    @param  secret_id   http://open.youtu.qq.com/上申请的密钥id
    @param  secret_key  http://open.youtu.qq.com/上申请的密钥key
    @param  expired     签名过期时间
    @param  userid      业务账号系统,没有可以不填
    @param  mySign      生成的签名
        @return 0表示成功
*/
public static int Sign(String appId, String secret_id, String secret_key,
		long expired, String userid, StringBuffer mySign) {
	return SignBase(appId, secret_id, secret_key, expired, "3041722595", mySign);
}



/**
 * SignBase:(腾讯优图接口签名)
 * @Author airufei
 * @param appId 优图项目id
 * @param secret_id 
 * @param secret_key
 * @param expired 过期时间
 * @param userid 用户id
 * @param url 
 * @param mySign
 * @return
 */
private static int SignBase(String appId, String secret_id,
		String secret_key, long expired, String userid,
		StringBuffer mySign) {
	if (empty(secret_id) || empty(secret_key))
	{
        return -1;
	}
	String puserid = "";
	if (!empty(userid))
	{
		if (userid.length() > 64)
		{
            return -2;
		}
		puserid = userid;
	}
    long now = System.currentTimeMillis() / 1000;    
    int rdm = Math.abs(new Random().nextInt());
    String plain_text = "a=" + appId + "&k=" + secret_id + "&e=" + expired + "&t=" + now + "&r=" + rdm + "&u=" + puserid ;//+ "&f=" + fileid.toString();

    byte[] bin = hashHmac(plain_text, secret_key);

    byte[] all = new byte[bin.length + plain_text.getBytes().length];
    System.arraycopy(bin, 0, all, 0, bin.length);
    System.arraycopy(plain_text.getBytes(), 0, all, bin.length, plain_text.getBytes().length);
    
    mySign.append(Base64Util.encode(all));
    
    return 0;
}

/**
 * hashHmac:(HmacSHA1算法)
 * @Author airufei
 * @param plain_text
 * @param accessKey
 * @return
 */
private static byte[] hashHmac(String plain_text, String accessKey) {
	
	try {
		return HMACSHA1Util.getSignature(plain_text, accessKey);
	} catch (Exception e) {

		return null;
	}
}


public static boolean empty(String s){
	return s == null || s.trim().equals("") || s.trim().equals("null");
}




}
