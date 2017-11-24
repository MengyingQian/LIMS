package com.ddxq.common.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月19日 下午4:05:52 
* 类说明 
*/
@Component("CacheUtilT")
public class CacheUtilTImpl implements CacheUtilT {
	
	@Autowired
	private StringRedisTemplate redisTemplate;//redis操作模板
	
	@Autowired
	private CacheExpireSet cacheExpireSet;//缓存工具类
	
	@Override
	public void put(String redisKey, String key, Object value, int expireTime) {
		if (key==null || "".equals(key)||null==redisKey||"".equals(redisKey)) {
			return;
		}
		redisTemplate.opsForHash().put(redisKey, key, JSON.toJSONString(value));
		//如果不等于-1，则该cacheName配置有过期时间
		if(expireTime != -1){
			redisTemplate.expire(redisKey, expireTime, TimeUnit.SECONDS);
		}
		
	}


	@Override
	public <T> T get(String redisKey, String key, Class<T> className) {
		Object obj = redisTemplate.opsForHash().get(redisKey, key);
		if(obj == null){
			return null;
		}
		return JSON.parseObject(""+obj, className);
	
	}

	@Override
	public boolean keyIsExist(String redisKey, String key) {
		if(key == null || "".equals(key)||null==redisKey||"".equals(redisKey)){
			return false;
		}
		return redisTemplate.opsForHash().hasKey(redisKey, key);
	}


	@Override
	public void remove(String redisKey, String key) {
		if(key == null || "".equals(key) || redisKey == null || "".equals(redisKey) ){
			return;
		}
		redisTemplate.opsForHash().delete(redisKey, key);
	}

}
