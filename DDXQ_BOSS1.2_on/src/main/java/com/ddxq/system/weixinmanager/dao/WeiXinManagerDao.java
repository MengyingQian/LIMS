package com.ddxq.system.weixinmanager.dao;

import java.util.List;
import java.util.Map;

public interface WeiXinManagerDao {
	List<Map<String, Object>> showAll();
	List<Map<String, Object>> showByDistrictId(int id);
}
