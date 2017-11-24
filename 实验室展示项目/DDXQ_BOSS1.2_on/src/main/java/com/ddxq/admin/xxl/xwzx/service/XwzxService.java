package com.ddxq.admin.xxl.xwzx.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface XwzxService {
	 public List getXwzxListTime(String redisKey);
	 public String getDistrictName(int districtId);
	 public JSONObject getDataFromDistrictIdAndColumn(String districtId,String cloumn);
	 public String parseToName(String column);
	 public String parseToEng(String name);
	 public List<String> jsonListToList(JSONObject obj);
	 public void remove(String redisKey,String docid,String districtId,String info);
	 public String getTrackserver();
	 public JSONObject insertNotice(JSONObject jobj);
	 public String getMessageType(String actorid);
}
