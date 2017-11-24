package com.ddxq.system.employees.service;

import java.util.Map;

import net.sf.json.JSONObject;

public interface EmployeesService {
	   public JSONObject getUserViaActorid(String districtId, String actorid);
	   public boolean editInfo(Map map) ;
	   public boolean  insertInfo(Map map);
	   public boolean removeInfo(String account);
}
