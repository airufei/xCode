/**
 * Project Name:hyd
 * File Name:HMACSHA1Util.java
 * Package Name:com.thinkgem.jeesite.common.utils
 * Date:2016年9月1日下午2:43:20
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
 */

package com.cn.cooxin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * ClassName:HMACSHA1Util (HMACSHA1加密算法) Date: 2016年9月1日 下午2:43:20
 * 
 * @Author airufei
 * @Version 1.0
 * @see
 */
public class HMACSHA1Util {

	 private static final String HMAC_SHA1 = "HmacSHA1";

	    public static byte[] getSignature(String data, String key) throws Exception {
	        return getSignature(data.getBytes(), key);
	    }

	    public static byte[] getSignature(byte[] data, String key) throws Exception {
	        Mac mac = Mac.getInstance(HMAC_SHA1);
	        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
	                mac.getAlgorithm());
	        mac.init(signingKey);
	        return mac.doFinal(data);
	    }

	    public static String getFileSha1(String path) throws IOException {
	        File file = new File(path);
	        FileInputStream in = new FileInputStream(file);
	        MessageDigest messagedigest;
	        try {
	            messagedigest = MessageDigest.getInstance("SHA-1");

	            byte[] buffer = new byte[1024 * 1024 * 10];
	            int len = 0;

	            while ((len = in.read(buffer)) > 0) {
	                // 该对象通过使用 update（）方法处理数据
	                messagedigest.update(buffer, 0, len);
	            }

	            // 对于给定数量的更新数据，digest 方法只能被调用一次。在调用 digest 之后，MessageDigest
	            // 对象被重新设置成其初始状态。
	            return getFormattedText(messagedigest.digest());//byte2hex(messagedigest.digest());
	        } catch (NoSuchAlgorithmException e) {
	            //NQLog.e("getFileSha1->NoSuchAlgorithmException###", e.toString());
	            e.printStackTrace();
	        } catch (OutOfMemoryError e) {
	            //NQLog.e("getFileSha1->OutOfMemoryError###", e.toString());
	            e.printStackTrace();
	            throw e;
	        } finally {
	            in.close();
	        }
	        return null;
	    }


	    private static String getFormattedText(final byte[] bytes) {
	        char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
	                '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	        int len = bytes.length;
	        StringBuilder buf = new StringBuilder(len * 2);
	        // 把密文转换成十六进制的字符串形式
	        for (int j = 0; j < len; j++) {
	            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
	            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
	        }
	        return buf.toString();
	    }
}
