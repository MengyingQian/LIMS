package com.ddxq.employee.vrcode.dao;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;


@Repository("grzxDao")
public class GrzxDaoImpl implements GrzxDao{
	
	@Autowired
	BaseDAO baseDao;
	@Autowired
	MD5Util mD5Util;
	@Autowired
    CacheUtil cacheUtil;
	private static final String TABLENAME="ddxq-users";
	@Override
	public boolean phoneIsExist(String mobile) {
		Map map = new HashMap<String,Object>();
		map.put("employeeId", mobile);
		String sql = "select count(*)  from ddxq_employees where employeeId=:employeeId;";
		Map res = baseDao.queryForMap(sql, map);
		if (null != res && (long)res.get("count(*)") > 0){
			return true;
		}
		return false;	
	}
	
}
