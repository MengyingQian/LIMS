package com.ddxq.admin.xxl.xwzx.dao;

import java.util.List;
import java.util.Map;

import org.bson.util.StringRangeSet;

import net.sf.json.JSONObject;

public interface XwzxDao {
	public String getDistrictName(int districtId);
	 public List getXwzxListTime(String redisKey);
	 public void remove(String redisKey,String docid,String district,String info);
	 public List<Map>getDataFromDistrictIdAndColumn(String districtId, String cloumn);
	 public JSONObject insertNotice(JSONObject jobj);
}
