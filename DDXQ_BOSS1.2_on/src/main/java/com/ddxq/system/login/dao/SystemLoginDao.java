package com.ddxq.system.login.dao;

import java.util.Map;

public interface SystemLoginDao {
	public int check(String name,String  password);
	public Map getMsg(String name);
	public String getTypeName(String actorid);
}
