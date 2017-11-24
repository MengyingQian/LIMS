package com.ddxq.system.poweruser.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.system.poweruser.dao.PowerUserDao;

import net.sf.json.JSONObject;
@Service("userService")
public class PowerUserServiceImpl implements PowerUserService{
	@Autowired
	PowerUserDao userDao;
	@Autowired
	MD5Util mD5Util;
	@Override
	public JSONObject getUserViaActorid(String actorid) {
		JSONObject retJobj = new JSONObject();
		List<Map<String, Object>> list=userDao.getUserViaActorid(actorid);
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public boolean editInfo(Map map) {
		try {
			map.put("password",mD5Util.StringToMD5_32(map.get("password").toString()));

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDao.editInfo(map);
	}
	@Override
	public boolean removeInfo(String account) {
		// TODO Auto-generated method stub
		return userDao.removeInfo(account);
	}
	@Override
	public boolean insertInfo(Map map) {
		
		return userDao.insertInfo(map);
	}

}
