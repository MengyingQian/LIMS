package com.ddxq.common.cache;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.hdfs.server.namenode.SequentialBlockIdGenerator;
import org.bson.util.StringRangeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.MD5.MD5;

/**
 * 缓存实现类，提供各种访问缓存的API实现
 * @author zxz
 * @version 1.0.0
 * @since 2015-02-01
 * */
@Component("cacheUtil")
public class CacheUtilImpl implements CacheUtil {

	@Autowired
	MD5Util mD5Util;
	@Autowired
	private StringRedisTemplate redisTemplate;//redis操作模板
	
	@Autowired
	private CacheExpireSet cacheExpireSet;//缓存工具类
	
	@Override
	public void put(String key, String value) {
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(key, key, value);
		long expireTime = cacheExpireSet.getExpireTime(key);
		//如果不等于-1，则该cacheName配置有过期时间
		if(expireTime != -1){
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public void put(String key, Object value) {
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(key, key, JSON.toJSONString(value));
		long expireTime = cacheExpireSet.getExpireTime(key);
		//如果不等于-1，则该cacheName配置有过期时间
		if(expireTime != -1){
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public <T> T get(String key, Class<T> className) {
		Object obj = redisTemplate.opsForHash().get(key, key);
		if(obj == null){
			return null;
		}
		return JSON.parseObject(""+obj, className);
	}

	@Override
	public boolean keyIsExist(String key) {
		if(key == null || "".equals(key)){
			return false;
		}
		return redisTemplate.opsForHash().hasKey(key, key);
	}

	@Override
	public void remove(String key) {
		if(key == null || "".equals(key)){
			return;
		}
		redisTemplate.opsForHash().delete(key, key);
	}

	@Override
	public void removeBatch(List<String> keys) {
		if(keys == null || keys.size()==0){
			return;
		}
		for (String key : keys) {
			redisTemplate.opsForHash().delete(key, key);
		}
	}

	@Override
	public String get(String key) {
		Object obj = redisTemplate.opsForHash().get(key, key);
		if(obj == null){
			return null;
		}else{
			return String.valueOf(obj);
		}
	}

	@Override
	public void put(String key, String value, int expireTime) {
		/** 
		 * @Title:        put 
		 * @Description:  TODO(这里用一句话描述这个方法的作用) 
		 * @param:          
		 * @return:          
		 * @throws 
		 * @author        zxz
		 * @Date          2016年4月19日 上午9:12:57 
		 */
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(key, key, value);
		//如果不等于-1，则该cacheName配置有过期时间
		if(expireTime != -1){
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public void put(String key, Object value, int expireTime) {
		/** 
		 * @Title:        put 
		 * @Description:  TODO(这里用一句话描述这个方法的作用) 
		 * @param:          
		 * @return:          
		 * @throws 
		 * @author        zxz
		 * @Date          2016年4月19日 上午9:12:57 
		 */
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(key, key, JSON.toJSONString(value));
		//如果不等于-1，则该cacheName配置有过期时间
		if(expireTime != -1){
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public <T> T get(String redisKey, String key, Class<T> className) {
		/** 
		 * @Title:        get 
		 * @Description:  TODO(这里用一句话描述这个方法的作用) 
		 * @param:          
		 * @return:          
		 * @throws 
		 * @author        zxz
		 * @Date          2016年4月19日 上午11:09:23 
		 */
		
		Object obj = redisTemplate.opsForHash().get(redisKey, key);
	
		if(obj == null){
			return null;
		}
		return JSON.parseObject(""+obj, className);
	}

	@Override
	public void put(String key, Map map) {
		// TODO Auto-generated method stub
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().putAll(key, map);
		long expireTime = cacheExpireSet.getExpireTime(key);
		//如果不等于-1，则该cacheName配置有过期时间
		if(expireTime != -1){
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public String get(String rediskey, String key) {
		// TODO Auto-generated method stub
		String obj = (String) redisTemplate.opsForHash().get(rediskey, key);
		if(obj == null){
			return null;
		}
		return obj;
	}
	@Override
	public List multiGet(String rediskey, List list) {
		// TODO Auto-generated method stub
		List obj = (List) redisTemplate.opsForHash().multiGet(rediskey, list);
		if(obj == null){
			return null;
		}
		return obj;
	}

	@Override
	public void put(String rediskey, String key, Object value, int expireTime) {
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(rediskey, key, JSON.toJSONString(value));		
		//如果不等于-1，则该cacheName配置有过期时间
		if(expireTime != -1){
			redisTemplate.expire(rediskey, expireTime, TimeUnit.SECONDS);
		}
		
	}

	@Override
	public Map getMap(String rediskey, String key) {
		// TODO Auto-generated method stub
		
		Object obj = redisTemplate.opsForHash().get(rediskey, key);
		
		if(obj == null){
			return null;
		}
		return JSON.parseObject(""+obj, HashMap.class);
	}

	@Override
	public void listRPush(String key, String value, int expireTime) {
		// TODO Auto-generated method stub
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForList().rightPush(key, value);
		
		if(expireTime != -1){
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public String listRPop(String key) {
		// TODO Auto-generated method stub
		String obj =  redisTemplate.opsForList().rightPop(key);
		if(obj == null){
			return null;
		}
		return obj;
		
	}
	@Override
	public String listIndex(String key,int index) {
		// TODO Auto-generated method stub
		String obj = redisTemplate.opsForList().index(key ,index);
		if(obj == null){
			return null;
		}
		return obj;
		
	}
	@Override
	public Long listSize(String key) {
		// TODO Auto-generated method stub
		Long obj =  redisTemplate.opsForList().size(key);
		if(obj == null){
			return null;
		}
		return obj;
		
	}
	@Override
	public void listLPop(String key) {
		redisTemplate.opsForList().leftPop(key);
		
	}
	@Override
	 public void listSet(String key, long index, String value) {  
		 redisTemplate.opsForList().set(key, index, value);  		
	}  
	@Override
	 public void listRemove(String redisKey, long count, String value) {  
		 redisTemplate.opsForList().remove(redisKey, count,  value);  		
	} 
	@Override
	public void hashPut(String redisKey,Map map){
		Set set=map.keySet();
		for (Object key:set) {
			
			redisTemplate.opsForHash().put(redisKey, key.toString(), map.get(key).toString());
		}		
	}
	@Override
	public void hashPut(String redisKey,String key,String value){
		
		redisTemplate.opsForHash().put(redisKey, key, value);
			
	}
	@Override
	public boolean hashHaveKey(String redisKey,String key){
	
		return redisTemplate.opsForHash().hasKey(redisKey, key);
	}
	@Override
	public long hashSize(String redisKey){
		
		return redisTemplate.opsForHash().size(redisKey);
	}
	@Override
	public void hashDeleteKey(String redisKey,String key){
		
		redisTemplate.opsForHash().delete(redisKey, key);
	}
	@Override
	public Map hashGet(String redisKey){
		 Set set=redisTemplate.opsForHash().keys(redisKey);
		 Map map=new HashMap<>();
		 for(Object key:set){
			 map.put(key.toString(),redisTemplate.opsForHash().get(redisKey, key.toString()));
		 }
		 return map;
	}
	@Override
	public String hashGet(String redisKey,String key){
		if(redisTemplate.opsForHash().get(redisKey, key)==null){
				return "";
		}
		return redisTemplate.opsForHash().get(redisKey, key).toString();
	}
	@Override
	public void hashRemove(String redisKey){
		 Set set=redisTemplate.opsForHash().keys(redisKey);
		 Map map=new HashMap<>();
		 for(Object key:set){
			redisTemplate.opsForHash().delete(redisKey, key.toString());;
		 }
	}
	  
	@Override
	public void zsetAdd(String key, String member, double score){
		if (member == null) {
            return;
        }
        redisTemplate.opsForZSet().add(key, member, score);
	}
	@Override
	public boolean zsetRemove(String key, String member){
		 if (member == null) {
	            return true;
	        }
	       return redisTemplate.opsForZSet().remove(key, member);
	}
	@Override
	public long zsetSize(String key){		
		return redisTemplate.opsForZSet().size(key);
	}
	@Override
	public Set<String> zsetRange(String key, long start, long end){
		Set<String> set=redisTemplate.opsForZSet().range(key, start, end);
		return set;
	}
	@Override
	public Set<TypedTuple<String>> zsetRangeWithScore(String key, long start, long end){
		Set<TypedTuple<String>> set=redisTemplate.opsForZSet().rangeWithScores(key, start, end);
		
		
		return set;
	}
	@Override
	public Set<String> zsetRangeByScore(String key, long start, long end){
		Set<String> set=redisTemplate.opsForZSet().rangeByScore(key, start, end);
		return set;
	}
	@Override
	public String zsetScore(String key,String member){
		return redisTemplate.opsForZSet().score(key, member).intValue()+"";
	}
	@Override
	public boolean setAdd(String redisKey,String value){
		return redisTemplate.opsForSet().add(redisKey, value);
	}
	@Override
	public Set<String> SetGet(String redisKey){
		
		return redisTemplate.opsForSet().members(redisKey);
	}
	@Override
	public boolean setRemove(String redisKey,String value){
		return redisTemplate.opsForSet().remove(redisKey, value);
	}
	@Override
	public long setSize(String redisKey){
		return redisTemplate.opsForSet().size(redisKey);
	}
	

	@Override
	public void stringPut(String redisKey,String value) {
		redisTemplate.opsForValue().set(redisKey, value);
	}
	@Override
	public void stringPutTime(String redisKey,String value, int expireTime) {//s
		redisTemplate.opsForValue().set(redisKey, value);
		if(expireTime != -1){
			redisTemplate.expire(redisKey, expireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public String stringGet(String redisKey) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().get(redisKey);
	}

	@Override
	public void stringRemove(String redisKey) {
		redisTemplate.opsForValue().set(redisKey, "", 0);
		
	}
	@Override
	public String getRandomNumber(int count){
		String base = "0123456789";
		Random random = new Random();     
		StringBuffer sb = new StringBuffer();     
		for (int i = 0; i < count; i++) {     
		    int number = random.nextInt(base.length());     
		    sb.append(base.charAt(number));     
		}
		return sb.toString();
	}
	@Override
	public String getRandomString() { //length表示生成字符串的长度  
		MD5 m = new MD5();
	    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < 64; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    } 

	    String result="";
		try {
			result = mD5Util.StringToMD5_32(sb.toString());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return  result;

	}
		
}
