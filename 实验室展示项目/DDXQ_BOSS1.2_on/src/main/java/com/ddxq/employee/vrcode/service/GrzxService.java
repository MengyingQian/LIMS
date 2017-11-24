package com.ddxq.employee.vrcode.service;

import java.util.Map;

import net.sf.json.JSONObject;

public interface GrzxService {

	public Map<String,Object> getCode(String mobile,String change);
	public boolean yanzheng(JSONObject obj);
}
