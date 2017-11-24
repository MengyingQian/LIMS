package com.ddxq.system.login.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;
@Repository("systemLoginDao")
public class SystemLoginDaoImpl implements SystemLoginDao{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	MD5Util mD5Util;
	
	@Override
	public int check(String name, String password) {
		if(haveAccount(name)){
			if(havePass(name, password)){
				return 1;
			}else{
				return 2;
			}
		}else{
			return 3;
		}
	}
	public boolean haveAccount(String name) {
		String sql="select * from ddxq_sysman where account=? ;";	
		Map map=baseDAO.queryForMap(sql, new Object[]{name});
		if(map.size()==0){
			return false;
		}else{
			return true;
		}
		
	}

	public boolean havePass(String name, String pass) {
		String pwd="";
		try {
			pwd=mD5Util.StringToMD5_32(pass);
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
		
		String sql="select account, pwd from ddxq_sysman where account=? and pwd=?; ";
		Map map=baseDAO.queryForMap(sql, new Object[]{name,pwd});		
		if(map.size()==0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public Map getMsg(String name) {
		String sql="select * from ddxq_sysman where account=? ;";
		Map map=baseDAO.queryForMap(sql, new Object[]{name});
		map.put("type", 1);
		map.put("districtId", "-1");
		return map;
	}
	@Override
	public String getTypeName(String actorid) {
		switch(actorid){	
			case "1":
				return "总管理员";
			case "2":
				return "平台运营员";
			case "3":
				return "平台财务管理员";
			case "4":
				return "平台客服";	
			}
		return "";
	}

}
