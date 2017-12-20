package com.cn.cooxin.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class DESCryptNewUtil {

	private static Logger logger = Logger.getLogger(DESCryptNewUtil.class);

	
	/**
	 * encryptBasedDes:(数据加密，算法（DES）)
	 * @Author airufei
	 * @param data 明文
	 * @param DES_KEY 加密秘钥
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String encryptBasedDes(String data, String DES_KEY) {
		String encryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY.getBytes("UTF-8"));
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 加密，并把字节数组编码成字符串
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher
					.doFinal(data.getBytes("UTF-8")));
		} catch (Exception e) {
			logger.error("加密错误，错误信息：", e);
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	
	/**
	 * decryptBasedDes:(数据解密，算法（DES）)
	 * @Author airufei
	 * @param cryptData 密文
	 * @param DES_KEY 加密秘钥
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String decryptBasedDes(String cryptData, String DES_KEY) {
		String decryptedData = null;
		try {
			byte[] decryptStr = new sun.misc.BASE64Decoder()
					.decodeBuffer(cryptData.replace(" ", "+"));
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY.getBytes("UTF-8"));
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 解密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// 把字符串解码为字节数组，并解密
			byte[] decryResult = cipher.doFinal(decryptStr);
			decryptedData = new String(decryResult, "UTF-8");
		} catch (Exception e) {
			logger.error("解密错误，错误信息：", e);
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		 String DES_KEY="";
		JSONObject pmm = new JSONObject();
		pmm.put("tokenId", "fghjklkjhdsfghjk");
		pmm.put("version", "1.2.1");
		pmm.put("sourceType", "2");
		pmm.put("deviceCode", "code21133323");
		pmm.put("accountPassword", "2wsxzaq1");
		pmm.put("timestamp", "2016-08-17 10:52:27");
		pmm.put("accountNumber", "13456259903");
		//String str = "{\"accountNumber\":\"13456259903\"}";
		 String mapStr = "{\"accountNumber\":13456259903,\"version\":\"1.2.1\",\"version\":\"1.2.1\",\"sourceType\":\"2\",\"deviceCode\":\"sdddd2222111\",\"accountPassword\":\"2wsxzaq1\",\"timestamp\":\"2016-08-17 10:52:27\",\"tokenId\":\"fghjklkjhdsfghjk\"}";
		// String encryStr = DESCryptUtil.encryptBased3Des(str);,\"version\":\"1.2.1\"
		  DES_KEY="@#8$am07";
		  String encryStr = DESCryptNewUtil.encryptBasedDes("Hello,早上好！",DES_KEY);
		  logger.info(" encrypt: str = " + encryStr);
		logger.info(" decrypt: str = "
				+ DESCryptNewUtil.decryptBasedDes("ovyJh35K5ylQpCarcV3d4zKLNJ+S2ti/",DES_KEY));
	}
}