/*
package com.java.nba.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

*/
/**
 * @author daiyun
 * @date 2019-4-11
 *//*

public class RedisUtils {

	private RedisUtils(){}

	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

	private static RedisTemplate<Serializable, Object> redisTemplate;

	public static void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		RedisUtils.redisTemplate = redisTemplate;
	}

	*/
/**
	 * 设置key，value值
	 * @param key
	 * @param value
	 * @return
	 *//*

	public static boolean set(String key, Object value){
		return set(key, value, 0L);
	}

	*/
/**
	 * redis设置值
	 * @param key
	 * @param value
	 * @param expireTime  过期时间
	 * @return
	 *//*

	public static boolean set(String key, Object value, Long expireTime){
		boolean result = false;
		try{
			ValueOperations<Serializable, Object> operation = redisTemplate.opsForValue();
			operation.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e){
			logger.error("set key error", e);
		}
		return result;
	}

	*/
/**
	 * 根据key获取值
	 * @param key
	 * @return
	 *//*

	public static Object get(String key){

		ValueOperations<Serializable, Object> operation = redisTemplate.opsForValue();
		return operation.get(key);

	}

	*/
/**
	 * 判断key值是否存在
	 * @param key
	 * @return
	 *//*

	public static boolean hasKey(String key){
		return redisTemplate.hasKey(key);
	}


}
*/
