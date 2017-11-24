package com.ddxq.system.showdistrictinfo.dao;

import java.util.List;
import java.util.Map;

import org.bson.util.StringRangeSet;

public interface ShowDistrictInfoDao {
	
	boolean addDistrictInfo(Object[] obj);
	boolean editDistrictInfo(Object[] obj);
	boolean updateTicketAndUrl(String districtId);
	boolean removeDistrictInfo(String districtId);
	boolean checkExist(String districtId);
	boolean checkExist(String districtId,String id);
	boolean insertBorder(String districtId,String border);
	boolean insertLocation(String x,String y,String districtId);
	List<Map<String, Object>> showAll();
	List<Map<String, Object>> showByDistrictId(int start,int end);
	List<Map<String, Object>> searchDistrict(String name);
}
