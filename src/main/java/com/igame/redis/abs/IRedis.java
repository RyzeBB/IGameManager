/** 
 * @Title: AbsRedis.java  
 * @Package: com.heygam.redis.abs  
 * @Date: 2014年11月27日下午4:46:33 
 * @Description: TODO(用一句话描述该文件做什么)  
 * @Version: V1.0
 * Copyright (c) 2014, allen.ime@gmail.com All Rights Reserved. 
 * 
 */

package com.igame.redis.abs;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: AbsRedis
 * @Author: Allen allen.ime@gmail.com
 * @Date: 2014年11月27日 下午4:46:33
 * @Description: TODO(这里用一句话描述这个类的作用)
 */
public class IRedis {
	protected JedisPool jedisPool;// 非切片连接池
//	protected ShardedJedisPool shardedJedisPool;// 切片连接池

	public IRedis() {
		initialPool();
//		initialShardedPool();

	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(false);

		jedisPool = new JedisPool(config, "192.168.100.104", 6379);
	}

	/**
	 * 初始化切片池
	 */
//	private void initialShardedPool() {
//		// 池基本配置
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxActive(20);
//		config.setMaxIdle(5);
//		config.setMaxWait(1000l);
//		config.setTestOnBorrow(false);
//		// slave链接
//		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//		shards.add(new JedisShardInfo("192.168.100.104", 6379, "master"));
//
//		// 构造池
//		shardedJedisPool = new ShardedJedisPool(config, shards);
//	}
}
