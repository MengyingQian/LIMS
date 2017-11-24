package com.ddxq.boss.base.openid.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.common.cache.CacheUtilT;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 上午10:55:11 
* 类说明 
*/
@Repository("OpenidIDRedisDAO")
public class OpenidIDRedisDaoImpl implements OpenidIDRedisDao {
	@Autowired 
	CacheUtilT cacheUtilT;
	
	private final static String redisKey="OPID";
	private final static int expireTime=-1;//超时时间限制
	

	@Override
	public void putOpenidID(String openid, String user_id) {
		
		cacheUtilT.put(redisKey, openid, user_id, expireTime);
	}

	@Override
	public String getOpenidID(String openid) {
		if(keyOpenidIDExist(openid) ){
			return cacheUtilT.get(redisKey, openid,String.class);
		}else{
			return null;
		}

	}

	@Override
	public boolean keyOpenidIDExist(String openid) {
		return cacheUtilT.keyIsExist(redisKey, openid);
	}

}
