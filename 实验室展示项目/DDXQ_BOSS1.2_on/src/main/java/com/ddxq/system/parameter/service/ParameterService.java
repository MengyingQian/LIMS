package com.ddxq.system.parameter.service;

public interface ParameterService {
	public boolean addParameter(String rediskey,String value);
	public String search(String rediskey);
}
