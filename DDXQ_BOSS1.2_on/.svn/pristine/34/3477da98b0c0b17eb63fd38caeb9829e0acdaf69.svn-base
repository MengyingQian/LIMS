package com.ddxq.admin.weixinmanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.admin.weixinmanage.dao.WeiXinmanageDao;

import net.sf.json.JSONObject;
@Service("weiXinManageService")
public class WeiXinManageServiceImpl implements WeiXinManageService{
	@Autowired
	WeiXinmanageDao weiXinManageDao;

	@Override
	public JSONObject showByDistrictId(int id) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=weiXinManageDao.showByDistrictId(id);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
}
