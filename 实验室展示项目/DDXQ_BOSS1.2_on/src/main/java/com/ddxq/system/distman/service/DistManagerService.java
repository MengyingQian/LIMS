package com.ddxq.system.distman.service;

import java.util.Map;

import net.sf.json.JSONObject;

public interface DistManagerService {
	   public JSONObject getUserViaActorid(String actorid);
	   public boolean editInfo(Map map) ;
	   public boolean  insertInfo(Map map);
	   public boolean removeInfo(String account);
}
