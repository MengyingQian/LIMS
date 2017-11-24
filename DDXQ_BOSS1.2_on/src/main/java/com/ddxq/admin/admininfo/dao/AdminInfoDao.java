package com.ddxq.admin.admininfo.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface AdminInfoDao {
	public boolean changepwd(String account,String pre,String pwd);
	 public JSONObject getUserViaAccount(String account);
	 public boolean editInfo(Map map);
}
