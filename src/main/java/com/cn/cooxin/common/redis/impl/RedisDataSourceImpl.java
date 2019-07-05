/**
 * Project Name:CooxinPro
 * File Name:RedisDataSourceImpl.java
 * Package Name:com.cn.cooxin.common.redis.impl
 * Date:2017年1月19日上午11:50:37
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common.redis.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import com.cn.cooxin.common.redis.IRedisDataSource;

/**
 * ClassName:RedisDataSourceImpl
 * Date:     2017年1月19日 上午11:50:37
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
@Repository("redisDataSource")
@SuppressWarnings("all")
public class RedisDataSourceImpl implements IRedisDataSource {

	 private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);

	 @Autowired
	 private ShardedJedisPool    shardedJedisPool;
	 /**
	 * getRedisClient:(取得redis的客户端，可以执行命令了)
	 * @Author airufei
	 * @return
	 */
	public ShardedJedis getRedisClient() {
	        try {
	        	return  shardedJedisPool.getResource();
	        } catch (Exception e) {
	        	String msg="getRedisClent error"+ e;
	            log.error(msg);

	        }
	        catch (Error e) {
	        	String msg="getRedisClent error"+ e;
	            log.error(msg);
	        }
	        return null;
	    }

	    
		/**
		 * returnResource:(出现异常后，将资源返还给pool)
		 * @Author airufei
		 * @param shardedJedis
		 * @param broken
		 */
		public void returnResource(ShardedJedis shardedJedis, boolean broken) {
	        if (broken) {
	            shardedJedisPool.returnBrokenResource(shardedJedis);
	        } else {
	            shardedJedisPool.returnResource(shardedJedis);
	        }
	    }
}

