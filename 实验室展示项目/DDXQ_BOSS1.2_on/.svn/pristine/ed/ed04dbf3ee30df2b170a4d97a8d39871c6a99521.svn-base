package com.ddxq.system.employees.service;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;
import com.ddxq.system.employees.dao.EmployeesDao;

import net.sf.json.JSONObject;
@Service("employeesService")
public class EmployeesServiceImpl implements EmployeesService{
	@Autowired
	EmployeesDao employeesDao;
	@Autowired
	MD5Util mD5Util;
	@Autowired
	private CacheUtil cacheUtil;
	@Override
	public JSONObject getUserViaActorid(String districtId, String actorid) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=employeesDao.getUserViaActorid(districtId, actorid);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}

	@Override
	public boolean editInfo(Map map) {
		return employeesDao.editInfo(map);
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
		return employeesDao.insertInfo(map);
	}

	@Override
	public boolean removeInfo(String account) {
		return employeesDao.removeInfo(account);
	}
	
}
