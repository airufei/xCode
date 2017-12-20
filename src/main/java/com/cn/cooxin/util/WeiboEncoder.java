/**
 * Project Name:CooxinPro
 * File Name:WeiboEncoder.java
 * Package Name:com.cn.cooxin.util
 * Date:2017年8月26日下午2:54:41
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.util;

import java.math.BigInteger;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;
/**
 * ClassName:WeiboEncoder (加密部分实现)
 * Date:     2017年8月26日 下午2:54:41
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class WeiboEncoder {

    private static BigInteger n = null;
    private static BigInteger e = null;
    
    /**
     * 使用Base64加密用户名(su的获取)
     * @param account
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String encodeAccount(String account){        
        return new String(Base64.encodeBase64(account.getBytes()));
    }
    
    /**
     * 使用RSAEncrypt对用户密码进行加密(sp的获取)
     * @param pwd
     * @param nStr
     * @param eStr
     * @return
     */
    public static String RSAEncrypt(String pwd, String nStr, String eStr){
        n = new BigInteger(nStr,16);
        e = new BigInteger(eStr,16);
        
        BigInteger r = RSADoPublic(pkcs1pad2(pwd,(n.bitLength()+7)>>3));
        String sp = r.toString(16);
        if((sp.length()&1) != 0 ) 
            sp = "0" + sp;
        return sp;
    }
    
    private static BigInteger RSADoPublic(BigInteger x){
         return x.modPow(e, n);
    }
    
    private static BigInteger pkcs1pad2(String s, int n){
        if(n < s.length() + 11) { // TODO: fix for utf-8
            System.err.println("Message too long for RSA");
            return null;
          }
        byte[] ba = new byte[n];
        int i = s.length()-1;
        while(i >= 0 && n > 0) {
            int c = s.codePointAt(i--);
            if(c < 128) { // encode using utf-8
              ba[--n] = new Byte(String.valueOf(c));
            }
            else if((c > 127) && (c < 2048)) {
              ba[--n] = new Byte(String.valueOf((c & 63) | 128));
              ba[--n] = new Byte(String.valueOf((c >> 6) | 192));
            }
            else {
              ba[--n] = new Byte(String.valueOf((c & 63) | 128));
              ba[--n] = new Byte(String.valueOf(((c >> 6) & 63) | 128));
              ba[--n] = new Byte(String.valueOf((c >> 12) | 224));
            }
          }
        ba[--n] = new Byte("0");
        
        byte[] temp = new byte[1];
        Random rdm = new Random(47L);
        
        while(n > 2) { // random non-zero pad
            temp[0] = new Byte("0");
            while(temp[0] == 0) 
                rdm.nextBytes(temp);
            ba[--n] = temp[0];
        }
        ba[--n] = 2;
        ba[--n] = 0;
        
        return new BigInteger(ba);
    }
}

