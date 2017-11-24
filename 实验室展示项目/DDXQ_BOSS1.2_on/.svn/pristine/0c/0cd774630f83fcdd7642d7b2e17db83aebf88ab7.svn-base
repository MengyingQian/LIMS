package com.ddxq.system.showdistrictinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.system.showdistrictinfo.dao.ShowDistrictInfoDao;

import net.sf.json.JSONObject;
@Service("ShowDistrictInfoService")
public class ShowDistrictInfoServiceImpl implements ShowDistrictInfoService{
	@Autowired
	ShowDistrictInfoDao showDistrictInfoDao;
	@Override
	public JSONObject showAll() {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=showDistrictInfoDao.showAll();
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public boolean addDistrictInfo(JSONObject jobj) {
		Object[] object=new Object[]{
				jobj.getString("districtId"),jobj.getString("areacode"),jobj.getString("name"),jobj.getString("alias"),
				jobj.getString("description"),jobj.getString("address"),jobj.getString("postcode"),jobj.getString("phone"),
				jobj.getString("site"),jobj.getString("busroute"),jobj.getString("qualitiy"),jobj.getString("category"),
				jobj.getString("level"),jobj.getString("tags"),jobj.getString("notes"),jobj.get("weathercode")
			
		};
		if(showDistrictInfoDao.checkExist(jobj.getString("districtId"))){
			return false;
		}
		boolean q=showDistrictInfoDao.addDistrictInfo(object);
		boolean w=showDistrictInfoDao.updateTicketAndUrl(jobj.getString("districtId"));
		return q&w;
	}
	@Override
	public JSONObject showByDistrictId(int start, int end) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=showDistrictInfoDao.showByDistrictId(start, end);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public boolean removeDistrictInfo(String districtId) {
		return  showDistrictInfoDao.removeDistrictInfo(districtId);
	}
	@Override
	public boolean editDistrictInfo(JSONObject jobj) {
		if(showDistrictInfoDao.checkExist(jobj.getString("districtId"),jobj.getString("id"))){
			return false;
		}
		Object[] object=new Object[]{jobj.getString("districtId"),
				jobj.getString("areacode"),jobj.getString("name"),jobj.getString("alias"),
				jobj.getString("description"),jobj.getString("address"),jobj.getString("postcode"),jobj.getString("phone"),
				jobj.getString("site"),jobj.getString("busroute"),jobj.getString("qualitiy"),jobj.getString("category"),
				jobj.getString("level"),jobj.getString("tags"),jobj.getString("notes"),jobj.get("weathercode"),
				jobj.getString("id")
			
		};
		boolean q=showDistrictInfoDao.editDistrictInfo(object);
		boolean w=showDistrictInfoDao.updateTicketAndUrl(jobj.getString("districtId"));
		return q&w;
	}
	@Override
	public JSONObject searchDistrict(String name) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=showDistrictInfoDao.searchDistrict(name);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public boolean insertBorder(String districtId, String border) {
		
		return showDistrictInfoDao.insertBorder(districtId, border);
	}
	@Override
	public boolean insertLocation(String x, String y, String districtId) {
		return showDistrictInfoDao.insertLocation(x,y, districtId);
		
	}

}
