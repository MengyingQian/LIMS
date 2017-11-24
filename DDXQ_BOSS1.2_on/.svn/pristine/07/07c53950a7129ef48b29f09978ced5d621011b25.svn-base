/**
 * FileName:    RedisExpireSet.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月13日 下午4:05:32
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月13日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.common.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.ddxq.common.constant.GlobalConstant;

/**
 * ClassName:RedisExpireSet <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月13日 下午4:05:32 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Repository("redisExpireSetDao")
public class RedisExpireSetDaoImpl implements RedisExpireSetDao{
	@Autowired
	private StringRedisTemplate redisTemplate;//redis操作模板
	
	public void put(String key, String value) {
		if (key==null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put("expireTime", key, value);
	}
	
	public boolean keyIsExist(String key) {
		if(key == null || "".equals(key)){
			return false;
		}
		return redisTemplate.opsForHash().hasKey("expireTime", key);
	}

	public void remove(String key) {
		if(key == null || "".equals(key)){
			return;
		}
		redisTemplate.opsForHash().delete("expireTime", key);
	}

	public void removeBatch(List<String> keys) {
		if(keys == null || keys.size()==0){
			return;
		}
		for (String key : keys) {
			redisTemplate.opsForHash().delete("expireTime", key);
		}
	}

	public Long getExpireTime(String key) {
		Object obj = redisTemplate.opsForHash().get("expireTime", key);
		if(obj == null){
			redisTemplate.opsForHash().put("expireTime", key, String.valueOf(GlobalConstant.Global_CacheTimeOut));
		    return (long) GlobalConstant.Global_CacheTimeOut;
		}else{
		    return Long.parseLong((String) obj);
		}
	}
}
