package com.ddxq.system.weixinmanager.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.system.weixinmanager.dao.WeiXinManagerDao;

import net.sf.json.JSONObject;
@Service("weiXinManagerService")
public class WeiXinManagerServiceImpl implements WeiXinManagerService {
	@Autowired
	WeiXinManagerDao weiXinManagerDao;
	@Override
	public JSONObject showAll() {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=weiXinManagerDao.showAll();
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public JSONObject showByDistrictId(int id) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=weiXinManagerDao.showByDistrictId(id);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
}
