package com.ddxq.system.showdistrictinfo.service;

import net.sf.json.JSONObject;

public interface ShowDistrictInfoService {
	boolean editDistrictInfo(JSONObject jobj);
	boolean addDistrictInfo(JSONObject obj);
	JSONObject searchDistrict(String name);
	public JSONObject showByDistrictId(int start, int end) ;
	JSONObject showAll();
	boolean removeDistrictInfo(String districtId);
	boolean insertBorder(String  districtId,String border);
	boolean insertLocation(String  x,String y,String districtId);
}
