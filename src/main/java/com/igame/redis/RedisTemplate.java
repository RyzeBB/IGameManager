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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.igame.commons.util.SerializableUtil;
import com.igame.redis.abs.IRedis;

/**
 * @ClassName: RedisClient
 * @Author: Allen allen.ime@gmail.com
 * @Date: 2014年11月27日 下午4:53:32
 * @Description: TODO(这里用一句话描述这个类的作用)
 */
@Component
public class RedisTemplate extends IRedis {
	private static final Logger logger = LoggerFactory.getLogger(RedisTemplate.class);

	public static final long EXPIRE_TIME = 22222;

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
				result = jedis.exists(key.getBytes());
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
	public <T> void set(String key, T value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.set(key.getBytes(), SerializableUtil.ObjectToByte(value));
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
	public <T> T get(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				byte[] bytes = jedis.get(key.getBytes());
				if (bytes != null) {
					return SerializableUtil.BytesToObject(bytes);
				}
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
	public void setnx(String key, Object value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.setnx(key.getBytes(), SerializableUtil.ObjectToByte(value));
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
	public void lpush(String key, Object... values) {
		byte[][] valuesBute = null;
		if (values != null && values.length > 0) {
			valuesBute = new byte[values.length][];
			for (int i = 0; i < values.length; i++) {
				valuesBute[i] = SerializableUtil.ObjectToByte(values[i]);
			}
		} else {
			return;
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.lpush(key.getBytes(), valuesBute);
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
	public <T> List<T> lrange(String key, int start, int end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				List<byte[]> bytes_list = jedis.lrange(key.getBytes(), start, end);
				if (bytes_list != null && !bytes_list.isEmpty()) {
					List<T> list = new ArrayList<T>(bytes_list.size());
					for (byte[] t : bytes_list) {
						T obj = SerializableUtil.BytesToObject(t);
						list.add(obj);
					}
					return list;
				}
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
	public <T> List<T> lrangeAll(String key) {
		return lrange(key, 0, -1);
	}

	/**
	 * 修改列表中单个值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void lset(String key, int index, Object value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.lset(key.getBytes(), index, SerializableUtil.ObjectToByte(value));
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
	public <T> T lindex(String key, int index) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				byte[] bytes = jedis.lindex(key.getBytes(), index);
				if (bytes != null && bytes.length > 0) {
					return SerializableUtil.BytesToObject(bytes);
				}
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
	 * 删除列表的值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void lrem(String key, int count, Object value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.lrem(key.getBytes(), count, SerializableUtil.ObjectToByte(value));
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
	public void ltrim(String key, int start, int end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.ltrim(key.getBytes(), start, end);
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
	public <T> T lpop(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				byte[] bytes = jedis.lpop(key.getBytes());
				if (bytes != null && bytes.length > 0) {
					return SerializableUtil.BytesToObject(bytes);
				}
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
	public void sadd(String key, Object... members) {

		byte[][] valuesBute = null;
		if (members != null && members.length > 0) {
			valuesBute = new byte[members.length][];
			for (int i = 0; i < members.length; i++) {
				valuesBute[i] = SerializableUtil.ObjectToByte(members[i]);
			}
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.sadd(key.getBytes(), valuesBute);
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
	public boolean sismember(String key, Object member) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.sismember(key.getBytes(), SerializableUtil.ObjectToByte(member));
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
	public <T> Set<T> smembers(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				Set<byte[]> bytes = jedis.smembers(key.getBytes());
				if (bytes != null && !bytes.isEmpty()) {
					Set<T> ts = new HashSet<T>();
					for (byte[] _byte : bytes) {
						T obj = SerializableUtil.BytesToObject(_byte);
						ts.add(obj);
					}
					return ts;
				}
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
	public void srem(String key, Object... members) {
		byte[][] membersByte = null;
		if (members != null && members.length > 0) {
			membersByte = new byte[members.length][];
			for (int i = 0; i < members.length; i++) {
				membersByte[i] = SerializableUtil.ObjectToByte(members[i]);
			}
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.srem(key.getBytes(), membersByte);
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
	public <T> T spop(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				byte[] bytes = jedis.spop(key.getBytes());
				if (bytes != null && bytes.length > 0) {
					return SerializableUtil.BytesToObject(bytes);
				}
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
	public <T> Set<T> sinter(String... keys) {
		byte[][] keysByte = null;
		if (keys != null && keys.length > 0) {
			keysByte = new byte[keys.length][];
			for (int i = 0; i < keys.length; i++) {
				keysByte[i] = keys[i].getBytes();
			}
		}
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				Set<byte[]> bytes = jedis.sinter(keysByte);
				if (bytes != null && !bytes.isEmpty()) {
					Set<T> ts = new HashSet<T>();
					for (byte[] _byte : bytes) {
						T obj = SerializableUtil.BytesToObject(_byte);
						ts.add(obj);
					}
					return ts;
				}
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
	public <T> Set<T> sunion(String... keys) {
		byte[][] keysByte = null;
		if (keys != null && keys.length > 0) {
			keysByte = new byte[keys.length][];
			for (int i = 0; i < keys.length; i++) {
				keysByte[i] = keys[i].getBytes();
			}
		}
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				Set<byte[]> bytes = jedis.sunion(keysByte);
				if (bytes != null && !bytes.isEmpty()) {
					Set<T> ts = new HashSet<T>(bytes.size());
					for (byte[] _byte : bytes) {
						T obj = SerializableUtil.BytesToObject(_byte);
						ts.add(obj);
					}
					return ts;
				}
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
	public <T> Set<T> sdiff(String... keys) {
		byte[][] keysByte = null;
		if (keys != null && keys.length > 0) {
			keysByte = new byte[keys.length][];
			for (int i = 0; i < keys.length; i++) {
				keysByte[i] = keys[i].getBytes();
			}
		}
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				Set<byte[]> bytes = jedis.sdiff(keysByte);
				if (bytes != null && !bytes.isEmpty()) {
					Set<T> ts = new HashSet<T>(bytes.size());
					for (byte[] _byte : bytes) {
						T obj = SerializableUtil.BytesToObject(_byte);
						ts.add(obj);
					}
					return ts;
				}
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
	public void zadd(String key, double score, Object member) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zadd(key.getBytes(), score, SerializableUtil.ObjectToByte(member));
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
	
	public void zadd(String key, Long member) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zadd(key.getBytes(), member, String.valueOf(member).getBytes());
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
	public void zadd(String key, Map<Double, Object> scoreMembers) {

		Map<Double, byte[]> scoreMembersByte = new HashMap<Double, byte[]>();

		for (Entry<Double, Object> entry : scoreMembers.entrySet()) {
			scoreMembersByte.put(entry.getKey(), SerializableUtil.ObjectToByte(entry.getValue()));
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zadd(key.getBytes(), scoreMembersByte);
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
	 * @Description: List<Long>
	 * @param key
	 * @param scoreMembers
	 */
	public void zaddLongList(String key, List<Long> scoreMembers) {

		Map<Double, byte[]> scoreMembersByte = new HashMap<Double, byte[]>();

		for (Long value : scoreMembers) {
			scoreMembersByte.put((double) (value), String.valueOf(value).getBytes());
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zadd(key.getBytes(), scoreMembersByte);
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
				return jedis.zcard(key.getBytes());
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
	public double zscore(String key, Object value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.zscore(key.getBytes(), SerializableUtil.ObjectToByte(value));
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

	public double zscoreLong(String key, Long value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.zscore(key.getBytes(), String.valueOf(value).getBytes());
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
	public <T> Set<T> zrange(String key, int start, int end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				Set<byte[]> bytes = jedis.zrange(key.getBytes(), start, end);
				if (bytes != null && !bytes.isEmpty()) {
					Set<T> ts = new HashSet<T>(bytes.size());
					for (byte[] _byte : bytes) {
						T obj = SerializableUtil.BytesToObject(_byte);
						ts.add(obj);
					}
					return ts;
				}

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
	 * @Description: zset for Long
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<Long> zrangeLong(String key, int start, int end) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				Set<byte[]> bytes = jedis.zrange(key.getBytes(), start, end);
				if (bytes != null && !bytes.isEmpty()) {
					Set<Long> ts = new HashSet<Long>(bytes.size());
					for (byte[] _byte : bytes) {
						Long obj = Long.parseLong(new String(_byte));
						ts.add(obj);
					}
					return ts;
				}
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
	public long zcount(String key, double min, double max) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.zcount(key.getBytes(), min, max);
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

	public <T> Set<T> zrangeByScore(String key, double min, double max) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				Set<byte[]> bytes = jedis.zrangeByScore(key.getBytes(), min, max);
				Set<T> ts = new HashSet<T>(bytes.size());
				for (byte[] _byte : bytes) {
					T obj = SerializableUtil.BytesToObject(_byte);
					ts.add(obj);
				}
				return ts;
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
	 * 删除元素
	 * 
	 * @param key
	 * @param members
	 */
	public void zrem(String key, Object... members) {
		byte[][] keysByte = new byte[members.length][];
		for (int i = 0; i < members.length; i++) {
			keysByte[i] = SerializableUtil.ObjectToByte(members[i]);
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.zrem(key.getBytes(), keysByte);
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
	public void hset(String key, String field, Object value) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.hset(key.getBytes(), field.getBytes(), SerializableUtil.ObjectToByte(value));
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
	 * @Description:；批量导入
	 * @param key
	 * @param values
	 */
	public void hset(String key, Map<String, Object> values) {
		Map<byte[], byte[]> valuesByte = new HashMap<byte[], byte[]>();

		for (Entry<String, Object> entry : values.entrySet()) {
			valuesByte.put(entry.getKey().getBytes(), SerializableUtil.ObjectToByte(entry.getValue()));
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				jedis.hmset(key.getBytes(), valuesByte);
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
	public boolean hexists(String key, String field) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				return jedis.hexists(key.getBytes(), field.getBytes());
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
	public <T> T hget(String key, String field) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
				if (bytes != null && bytes.length > 0) {
					return SerializableUtil.BytesToObject(bytes);
				}
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
	public <T> List<T> hmget(String key, String... fields) {
		byte[][] keysByte = new byte[fields.length][];
		for (int i = 0; i < fields.length; i++) {
			keysByte[i] = fields[i].getBytes();
		}

		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				List<byte[]> bytes = jedis.hmget(key.getBytes(), keysByte);
				if (bytes != null && !bytes.isEmpty()) {
					List<T> ts = new ArrayList<T>(bytes.size());
					for (byte[] _byte : bytes) {
						T obj = SerializableUtil.BytesToObject(_byte);
						ts.add(obj);
					}
					return ts;
				}
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
	public <T> List<T> hvals(String key) {
		boolean flag = true;
		Jedis jedis = jedisPool.getResource();
		try {
			if (jedis != null) {
				List<byte[]> bytes = jedis.hvals(key.getBytes());
				if (bytes != null && !bytes.isEmpty()) {
					List<T> ts = new ArrayList<T>(bytes.size());
					for (byte[] _byte : bytes) {
						T obj = SerializableUtil.BytesToObject(_byte);
						ts.add(obj);
					}
					return ts;
				}
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