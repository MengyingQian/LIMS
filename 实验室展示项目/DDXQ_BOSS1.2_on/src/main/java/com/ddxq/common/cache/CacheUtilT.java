package com.ddxq.common.cache;

import java.util.List;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月19日 下午4:05:11 
* 类说明 
*/
public interface CacheUtilT {

	/**
	 * 根据KEY加入缓存
	 * 
	 * @param key         缓存KEY
	 * @param value       缓存值
	 * @param expireTime  超时时间
	 * */
	public void put(String redisKey,String key, Object value, int expireTime);
	

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
	public boolean keyIsExist(String redisKey,String key);
	/**
	 * 
	 * @Title: remove
	 * @Description: TODO(在redis中删除)
	 * @ @param redisKey
	 * @ @param key   
	 * @return: void   
	 * @throws
	 */
	public void remove(String redisKey,String key);
	
}
