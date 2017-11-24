package com.ddxq.system.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.system.parameter.dao.ParameterDao;

@Service("parameterService")
public class ParameterServiceImpl implements ParameterService{
	@Autowired
	ParameterDao parameterDao;
	@Override
	public boolean addParameter(String rediskey, String value) {
		return parameterDao.addParameter(rediskey, value);
	}
	@Override
	public String search(String rediskey) {
		
		return parameterDao.search(rediskey);
	}

}
