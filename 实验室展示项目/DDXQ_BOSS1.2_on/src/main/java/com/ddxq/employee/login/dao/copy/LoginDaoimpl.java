package com.ddxq.employee.login.dao.copy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;

@Repository("loginDao")
public class LoginDaoimpl implements LoginDao{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	MD5Util mD5Util;
	@Override
	public int isHave(String name, String password) {
		String pwd="";
		try {
			pwd=mD5Util.StringToMD5_32(password);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		String sql="select account, pwd from ddxq_sysman where account=? and pwd=?; ";
		Map map=baseDAO.queryForMap(sql, new Object[]{name,pwd});
		String sql2="select employeeId, passWD from ddxq_employees where employeeId=? and passWD=?; ";
		Map map2=baseDAO.queryForMap(sql2, new Object[]{name,pwd});
		
		if(map.size()!=0){
			return 1;
		}
		
		if(map2.size()!=0){
			return 2;
		}
		return 0;
	}

	@Override
	public Map getMsg1(String name) {
		String sql="select id,account,actorid from ddxq_sysman where account=? ;";
		Map map=baseDAO.queryForMap(sql, new Object[]{name});
		return map;
	}
	@Override
	public Map getMsg2(String name) {
		String sql="select tb1.id,tb1.employeeActor,tb1.districtId,tb1.employeeId,tb2.alias from ddxq_employees as tb1 inner join ddxq_districts as tb2 on tb2.districtId=tb1.districtId  where tb1.employeeId=?;";
		Map map=baseDAO.queryForMap(sql, new Object[]{name});
		return map;
	}

	@Override
	public int haveAccount(String name) {
		String sql2="select * from ddxq_employees where employeeId=?;";
		Map map2=baseDAO.queryForMap(sql2, new Object[]{name});
		if(map2.size()==0){
			return 3;
		}
		int employeeActor = (int) map2.get("employeeActor");
		int status = Integer.valueOf((String) map2.get("status"));
		System.out.println("status: "+ status);
		if (status == 0 && employeeActor == 100){
			return 5;
		}else if (status == 0){
			return 4;
		}
		return 1;
	}

	@Override
	public boolean havePass(String name, String pass) {
		String pwd="";
		try {
			pwd=mD5Util.StringToMD5_32(pass);
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
			
		String sql2="select employeeId, passWD from ddxq_employees where employeeId=? and passWD=?; ";
		Map map2=baseDAO.queryForMap(sql2, new Object[]{name,pwd});
		
		if(map2.size()==0){
			return false;
		}else{
			return true;
		}
	}

}
