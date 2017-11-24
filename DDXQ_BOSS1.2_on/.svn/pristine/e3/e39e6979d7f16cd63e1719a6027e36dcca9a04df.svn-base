package com.ddxq.system.showqrcode.dao;

import java.util.List;
import java.util.Map;

public interface ShowQRCodeDao {
	List<Map<String, Object>> showAll();
	List<Map<String, Object>> showByDistrictId(int start,int end);
	List<Map<String, Object>> showByDistrictIdAndDate(int start,int end,String dateFrom,String dateTo);
	List<Map<String, Object>> showByPage(int pageNumber,int pageSize);
	boolean updateNote(int start,int end,String note);
	boolean deleteByDistrictId(int start,int end);
	boolean deleteByDistrictIdAndDate(int start,int end,String dateFrom,String DateTo);
	boolean insertNewCode(List<Map<String, Object>> list);
	boolean checkExist(int sceneId);
}
