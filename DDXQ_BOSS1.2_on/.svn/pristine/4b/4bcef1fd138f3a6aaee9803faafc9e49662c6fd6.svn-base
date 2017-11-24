package com.ddxq.system.distman.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.system.distman.dao.DistManagerDao;

import net.sf.json.JSONObject;
@Service("distManagerService")
public class DistManagerServiceImpl implements DistManagerService{
	@Autowired
	DistManagerDao distManagerDao;
	@Autowired
	MD5Util mD5Util;
	@Override
	public JSONObject getUserViaActorid(String actorid) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=distManagerDao.getUserViaActorid(actorid);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}

	@Override
	public boolean editInfo(Map map) {
		// TODO Auto-generated method stub
		return distManagerDao.editInfo(map);
	}

	@Override
	public boolean insertInfo(Map map) {
		String password=map.get("passWD").toString();
		String passwd="";
		try {
			passwd=mD5Util.StringToMD5_32(password);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		map.put("passWD", passwd);
		return distManagerDao.insertInfo(map);
	}

	@Override
	public boolean removeInfo(String account) {
		// TODO Auto-generated method stub
		return distManagerDao.removeInfo(account);
	}

}
