package com.ddxq.admin.fwt.fwzs.service;

import net.sf.json.JSONObject;

public interface FwzsService {
	public JSONObject getInfo(String districtId);
	public JSONObject getInfo(String districtId,String type );
	public boolean insertInfo(JSONObject jobj);
	public boolean updateInfo(String districtId,JSONObject jobj) ;
	public boolean removeInfo(String districtId,String type,String docid,JSONObject jobj) ;
}
