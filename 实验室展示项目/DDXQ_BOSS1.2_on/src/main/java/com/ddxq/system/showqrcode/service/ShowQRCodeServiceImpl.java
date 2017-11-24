package com.ddxq.system.showqrcode.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.system.showqrcode.dao.ShowQRCodeDao;

import net.sf.json.JSONObject;
@Service("ShowQRCodeService")
public class ShowQRCodeServiceImpl implements ShowQRCodeService{
	@Autowired
	ShowQRCodeDao showQRCodeDao;
	@Override
	public JSONObject showAll() {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=showQRCodeDao.showAll();
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}

	@Override
	public JSONObject showByDistrictId(int start, int end) {	
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=showQRCodeDao.showByDistrictId(start, end);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public JSONObject showByDistrictIdAndDate(int startId, int endId, String dateFrom, String dateTo) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=showQRCodeDao.showByDistrictIdAndDate(startId, endId, dateFrom, dateTo);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public boolean deleteCode(int row) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExist(int sceneId) {
		return showQRCodeDao.checkExist(sceneId);
	}

	@Override
	public boolean insertNewCode(List<Map<String, Object>> list) {
		showQRCodeDao.insertNewCode(list);
		return false;
	}

	@Override
	public boolean deleteByDistrictId(int startId, int endId) {
		showQRCodeDao.deleteByDistrictId(startId,endId);
		return true;
	}

	@Override
	public JSONObject showByPage(int pageNumber, int pageSize,int total) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=showQRCodeDao.showByPage(pageNumber, pageSize);
		retJobj.put("total",total);
		retJobj.put("rows", list);
		return retJobj;
	}

	@Override
	public boolean insertNote(int start, int end, String note) {
		showQRCodeDao.updateNote(start, end, note);
		return true;
	}

	@Override
	public boolean deleteByDistrictIdAndDate(int startId, int endId, String dateFrom, String dateTo) {
		showQRCodeDao.deleteByDistrictIdAndDate(startId, endId, dateFrom, dateTo);
		return true;
	}




}
