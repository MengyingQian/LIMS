package com.ddxq.boss.base.config.dao;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository("configRedisDao")
public class ConfigRedisDaoImpl implements ConfigRedisDao{
	@Autowired
	private StringRedisTemplate redisTemplate;//redis操作模板
	
	public void addConfigintoRedis(JSONObject config){
		redisTemplate.opsForHash().put("config", "number_of_banghuigu", String.valueOf(config.get("number_of_banghuigu")));
		redisTemplate.opsForHash().put("config", "max_rank", String.valueOf(config.get("max_rank")));
		redisTemplate.opsForHash().put("config", "limit_count", String.valueOf(config.get("limit_count")));
		redisTemplate.opsForHash().put("config", "bianwen_count_per_page", String.valueOf(config.get("bianwen_count_per_page")));
		redisTemplate.opsForHash().put("config", "bianwen_count_in_redis", String.valueOf(config.get("bianwen_count_in_redis")));
	}
	
	public int getNumber_of_banghuigu(){
    	int number_of_banghuigu = Integer.parseInt((String) redisTemplate.opsForHash().get("config", "number_of_banghuigu"));
    	return number_of_banghuigu;
    }
	
	public int getMax_rank(){
    	int getMax_rank = Integer.parseInt((String) redisTemplate.opsForHash().get("config", "max_rank"));
    	return getMax_rank;
    }
	
	public int getLimit_count(){
    	int limit_count = Integer.parseInt((String) redisTemplate.opsForHash().get("config", "limit_count"));
    	return limit_count;
    }
	
	public Integer getBianwen_count_per_page(){
		String per_page_string = null;
		per_page_string = (String) redisTemplate.opsForHash().get("config", "bianwen_count_per_page");
		System.out.println("per_page_string is:"+per_page_string);
		if ( null==per_page_string ){
			return null;
		}
    	int bianwen_count_per_page = Integer.parseInt(per_page_string);
    	return bianwen_count_per_page;
    }
	
	public int getBianwen_count_in_redis(){
    	int bianwen_count_in_redis = Integer.parseInt((String) redisTemplate.opsForHash().get("config", "bianwen_count_in_redis"));
    	return bianwen_count_in_redis;
    }
}
