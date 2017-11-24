package com.ddxq.common.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

/**
 * 缓存接口，提供各种访问缓存的API,后续可以根据业务需求拓展
 * @author zxz
 * @version 1.0.0
 * @since 2015-02-01
 * */
public interface CacheUtil {
	
	/**
	 * 根据KEY加入缓存
	 * 
	 * @param key         缓存KEY
	 * @param value       缓存值
	 * */
	public void put(String key, String value);
	
	/**
	 * 根据KEY加入缓存
	 * 
	 * @param key         缓存KEY
	 * @param value       缓存值
	 * @param expireTime  超时时间
	 * */
	public void put(String key, String value, int expireTime);
	public void put(String rediskey, String key,Object value, int expireTime);
	public void put(String key, Map map);
	
	/**
	 * 根据KEY加入缓存
	 * 
	 * @param key         缓存KEY
	 * @param value       缓存值
	 * */
	public void put(String key, Object value);
	
	/**
	 * 根据KEY加入缓存
	 * 
	 * @param key         缓存KEY
	 * @param value       缓存值
	 * @param expireTime  超时时间
	 * */
	public void put(String key, Object value, int expireTime);
	
	/**
	 * 根据KEY获取缓存
	 * 
	 * @param key         缓存KEY
	 * */
	public String get(String key);
	public String get(String rediskey,String key);
	public List multiGet(String rediskey, List list);
	public Map getMap(String rediskey, String key); 
	
	/**
	 * 根据KEY获取缓存
	 * 
	 * @param key         缓存KEY
	 * @param className   获取的缓存值的类型
	 * */
	public <T> T get(String key, Class<T> className);
	
	/**
	 * 根据KEY获取缓存
	 * 
	 * @param redisKey    缓存KEY
	 * @param key         hashKEY
	 * @param className   获取的缓存值的类型
	 * */
	public <T> T get(String redisKey, String key, Class<T> className);
	
	/**
	 * 判断KEY在缓存中是否存在
	 * 
	 * @param key         缓存KEY
	 * */
	public boolean keyIsExist(String key);
	
	/**
	 * 根据KEY删除缓存
	 * 
	 * @param key         缓存KEY
	 * */
	public void remove(String key);
	
	/**
	 * 根据KEY批量删除缓存
	 * 
	 * @param keys         缓存KEY集合
	 * */
	public void removeBatch(List<String> keys);
	public void stringPut(String redisKey,String value);
	public void stringPutTime(String redisKey,String value, int expireTime);
	public String stringGet(String redisKey);
	public void  stringRemove(String redisKey);
	public void listRPush(String key, String value,int expireTime);
	public String listRPop(String key);
	public String listIndex(String key,int index);
	public void listLPop(String key);
	public Long listSize(String key) ;
	public void listSet(String key, long index, String value) ;
	public void listRemove(String redisKey, long count, String value);
	public void zsetAdd(String key, String value, double score);
	public boolean zsetRemove(String key, String value);
	public long zsetSize(String key);
	public void hashPut(String redisKey,Map map);
	public Map hashGet(String redisKey);
	public boolean hashHaveKey(String redisKey,String key);
	public void hashDeleteKey(String redisKey,String key);
	public void hashRemove(String redisKey);
	public void hashPut(String redisKey,String key,String value);
	public long hashSize(String redisKey);
	public String hashGet(String redisKey,String key);
	public boolean setAdd(String redisKey,String value);
	public Set<String> SetGet(String redisKey);
	public boolean setRemove(String redisKey,String value);
	public long setSize(String redisKey);
	public Set<TypedTuple<String>> zsetRangeWithScore(String key, long start, long end);
	public Set<String> zsetRange(String key, long start, long end);
	public Set<String> zsetRangeByScore(String key, long start, long end);
	public String zsetScore(String key,String member);
	public String getRandomString();
	public String getRandomNumber(int count);
}
