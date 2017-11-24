package com.ddxq.employees.info.dao;

import java.util.Map;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年12月12日 上午10:46:53 
* 类说明 
*/
public interface InfoDao {
	public boolean changepwd(String account,String pre,String pwd);
	public JSONObject getUserinfo(String account);
	public boolean editInfo(Map map);
}
 