/**
 * Project Name:CooxinPro
 * File Name:IRedisDataSource.java
 * Package Name:com.cn.cooxin.common.redis
 * Date:2017年1月19日上午11:48:10
 * Copyright (c) 2017, hukailee@163.com All Rights Reserved.
 *
*/

package com.cn.cooxin.common.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * ClassName:IRedisDataSource
 * Date:     2017年1月19日 上午11:48:10
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public interface IRedisDataSource {

	/**
	 * getRedisClient:(取得redis的客户端，可以执行命令了。)
	 * @Author airufei
	 * @return
	 */
	public abstract ShardedJedis getRedisClient();

    /**
     * returnResource:(出现异常后，将资源返还给pool)
     * @Author airufei
     * @param shardedJedis
     * @param broken
     */
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}

