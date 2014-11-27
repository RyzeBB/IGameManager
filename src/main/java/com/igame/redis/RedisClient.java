/** 
 * @Title: RedisClient.java  
 * @Package: com.heygam.redis  
 * @Date: 2014年11月27日下午4:53:32 
 * @Description: TODO(用一句话描述该文件做什么)  
 * @Version: V1.0
 * Copyright (c) 2014, allen.ime@gmail.com All Rights Reserved. 
 * 
 */

package com.igame.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.igame.redis.abs.IRedis;

/**
 * @ClassName: RedisClient
 * @Author: Allen allen.ime@gmail.com
 * @Date: 2014年11月27日 下午4:53:32
 * @Description: TODO(这里用一句话描述这个类的作用)
 */
public class RedisClient extends IRedis {
	private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

	// =====================key==========================
	/**
	 * @Description: 清空数据
	 */
	public void flushDB() {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.flushDB();
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * @Description: 键值是否存在
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		boolean result = false;
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				result = jedis.exists(key);
			}

		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return result;
	}

	// =============String==========================

	/**
	 * @Description: 存储数据
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.set(key, value);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * @Description: 获取数据
	 * @param key
	 * @return
	 */
	public String get(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.get(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * @Description: 存储数据( 若key不存在，则存储)
	 * @param key
	 * @param value
	 */
	public void setnx(String key, String value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.setnx(key, value);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * @Description: 追加数据
	 * @param key
	 * @param value
	 */
	public void append(String key, String value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.append(key, value);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * @Description: 截取value的值
	 * @param key
	 * @param start
	 * @param end
	 */
	public void getrange(String key, long start, long end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.getrange(key, start, end);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * @Description: 设置多个key-value
	 * @param keyValues
	 */
	public void mset(String... keyValues) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.mset(keyValues);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * @Description:获取多个key对应的值
	 * @param keys
	 * @return
	 */
	public List<String> mget(String... keys) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.mget(keys);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * @Description: 删除
	 * @param keys
	 */
	public void del(String... keys) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.del(keys);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	// =============list==========================
	public void lpush(String key, String... values) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.lpush(key, values);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 获取list长度
	 * 
	 * @param key
	 * @return
	 */
	public long llen(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.llen(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return 0;
	}

	/**
	 * 根据下表范围取值
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key, long start, long end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.lrange(key, start, end);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 获取列表所有值
	 * 
	 * @param key
	 * @return
	 */
	public List<String> lrangeAll(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.lrange(key, 0, -1);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 修改列表中单个值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void lset(String key, long index, String value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.lset(key, index, value);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 获取列表指定下标的值
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key, long index) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.lindex(key, index);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 删除列表指定下标的值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void lrem(String key, long index, String value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.lrem(key, index, value);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 删除区间以外的数据
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void ltrim(String key, long start, long end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.ltrim(key, start, end);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 列表出栈
	 * 
	 * @param key
	 * @return
	 */
	public String lpop(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.lpop(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	// =============set==========================
	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void sadd(String key, String... members) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.sadd(key, members);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 判断value是否在列表中
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public boolean sismember(String key, String member) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.sismember(key, member);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return false;
	}

	/**
	 * 整个列表值
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> smembers(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.smembers(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 删除指定元素
	 * 
	 * @param key
	 * @param members
	 */
	public void srem(String key, String members) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.srem(key, members);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */
	public String spop(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.spop(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 交集
	 * 
	 * @param keys
	 * @return
	 */
	public Set<String> sinter(String... keys) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.sinter(keys);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 并集
	 * 
	 * @param keys
	 * @return
	 */
	public Set<String> sunion(String... keys) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.sunion(keys);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 差集
	 * 
	 * @param keys
	 * @return
	 */
	public Set<String> sdiff(String... keys) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.sdiff(keys);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	// =============SortedSet==========================
	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param score
	 * @param member
	 */
	public void zadd(String key, double score, String member) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zadd(key, score, member);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 批量添加数据
	 * 
	 * @param key
	 * @param scoreMembers
	 */
	public void zadd(String key, Map<Double, String> scoreMembers) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zadd(key, scoreMembers);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 元素个数
	 * 
	 * @param key
	 * @return
	 */
	public long zcard(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.zcard(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return 0;
	}

	/**
	 * 元素下标
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public double zscore(String key, String value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.zscore(key, value);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return 0;
	}

	/**
	 * 集合子集
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrange(String key, long start, long end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.zrange(key, start, end);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 下表区间元素个数
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public long zcount(String key, String min, String max) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.zcount(key, min, max);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return 0;
	}

	/**
	 * 删除元素
	 * 
	 * @param key
	 * @param members
	 */
	public void zrem(String key, String... members) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zrem(key, members);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	// =============hash==========================
	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param key2
	 * @param member
	 */
	public void hset(String key, String key2, String member) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.hset(key, key2, member);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

	/**
	 * 是否存在
	 * 
	 * @param key
	 * @param key2
	 * @return
	 */
	public boolean hexists(String key, String key2) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.hexists(key, key2);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return false;
	}

	/**
	 * 获取指定的值
	 * 
	 * @param key
	 * @param key2
	 * @return
	 */
	public String hget(String key, String key2) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.hget(key, key2);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 批量获取指定的值
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget(String key, String... fields) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.hmget(key, fields);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 获取所有的keys
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> hkeys(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.hkeys(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 获取所有的values
	 * 
	 * @param key
	 * @return
	 */
	public List<String> hvals(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.hvals(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @param fields
	 */
	public void hdel(String key, String... fields) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.hdel(key, fields);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("redis error", e);
		} finally {
			if (flag) {
				jedisPool.returnResource(jedis);
			} else {
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}
}
