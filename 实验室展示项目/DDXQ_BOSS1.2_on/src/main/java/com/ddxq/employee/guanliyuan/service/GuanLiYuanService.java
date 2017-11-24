package com.ddxq.employee.guanliyuan.service;

import java.util.Map;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年11月30日 下午3:13:14 
* 类说明 
*/
public interface GuanLiYuanService {
	  public boolean removeInfo(String account);
	  public String getcert(String id);
	  public boolean getServicesStatus(String districtID, int type);
	  public boolean changServicesStatus(JSONObject obj);
	  public Map<String,Object> insertInfo(Map map);
	  public JSONObject getUserViaActor(String districtId, String actor);
	  
}
 