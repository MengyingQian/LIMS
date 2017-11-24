package com.ddxq.system.parameter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.common.cache.CacheUtil;

@Repository("parameterDao")
public class ParameterDaoImpl  implements ParameterDao{
	@Autowired
	CacheUtil cacheUtil;
	@Override
	public boolean addParameter(String rediskey, String value) {
		cacheUtil.stringPut(rediskey, value);
		//cacheUtil.hashPut("ddxq:system", rediskey, value);
		return true;
	}
	@Override
	public String search(String rediskey) {
		
		return cacheUtil.stringGet(rediskey);
	}

}
