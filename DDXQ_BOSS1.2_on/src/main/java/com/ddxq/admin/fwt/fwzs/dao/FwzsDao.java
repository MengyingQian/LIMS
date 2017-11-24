package com.ddxq.admin.fwt.fwzs.dao;

import java.util.List;
import java.util.Map;

public interface FwzsDao {
	public List<Map> getListInfo(String districtId);
	public List<Map> getListInfo(String districtId,String type);
	public boolean insertInfo(String districtId, Map map);
}
