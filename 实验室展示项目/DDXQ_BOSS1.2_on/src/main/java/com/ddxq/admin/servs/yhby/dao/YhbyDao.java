package com.ddxq.admin.servs.yhby.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface YhbyDao {
	public boolean insertInfo(String redisKey, Map map);
	public boolean editInfo(String redisKey, Map map);
	public boolean removeInfo(String redisKey,String member);
	public List<Map> getDataFromDistrictIdAndColumn(String districtId, String servs);
}
