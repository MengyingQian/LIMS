package com.ddxq.system.showqrcode.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface ShowQRCodeService {
	JSONObject showAll();	
	JSONObject showByDistrictId(int startId,int endId);
	JSONObject showByDistrictIdAndDate(int startId,int endId,String dateFrom,String dateTo);
	JSONObject showByPage(int pageNumber, int pageSize,int total);
	boolean insertNote(int startId,int endId,String note);
	boolean deleteByDistrictId(int startId,int endId);
	boolean deleteByDistrictIdAndDate(int startId,int endId,String dateFrom,String dateTo);
	boolean deleteCode(int row);
	boolean checkExist(int sceneId);
	boolean insertNewCode(List<Map<String, Object>> list);
	//List<Map<String, Object>> showByDate();
	//List<Map<String, Object>> showByDistrictIdAndDate();
	
}
