package com.ddxq.admin.fwt.fwzs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.boss.base.util.RandomString.RandomStringUtils;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;

@Repository("fwzsDao")
public class FwzsDaoImpl implements FwzsDao{
	@Autowired
	private CacheUtil cacheUtil;
	@Autowired
	private BaseHBaseDAO baseHBaseDAO;
	@Autowired
	private BaseDAO basedao;
	@Autowired
	private RandomStringUtils randomString;
	@Autowired
	private MD5Util md5util;
	@Override
	public List<Map> getListInfo(String districtId) {
		String rediskey1="districtid:"+districtId+":house:rent:A";
		String rediskey2="districtid:"+districtId+":house:rent:B";
		String rediskey3="districtid:"+districtId+":house:sale:A";
		String rediskey4="districtid:"+districtId+":house:sale:B";
		Set<String>set= cacheUtil.zsetRange(rediskey1, 0, -1);
		Set<String>set2= cacheUtil.zsetRange(rediskey2, 0, -1);
		Set<String>set3= cacheUtil.zsetRange(rediskey3, 0, -1);
		Set<String>set4= cacheUtil.zsetRange(rediskey4, 0, -1);
		List<Map>list=new ArrayList<>(); 
		for (String s:set){
			list.add(cacheUtil.hashGet(s));
		}
		for (String s:set2){
			list.add(cacheUtil.hashGet(s));
		}
		for (String s:set3){
			list.add(cacheUtil.hashGet(s));
		}
		for (String s:set4){
			list.add(cacheUtil.hashGet(s));
		}
		
		return list;
	}
	@Override
	public List<Map> getListInfo(String districtId,String type) {
		String rediskey1="districtid:"+districtId+":house:"+type;
		Set<String>set= cacheUtil.zsetRange(rediskey1, 0, -1);
		List<Map>list=new ArrayList<>(); 
		for (String s:set){
			list.add(cacheUtil.hashGet(s));
		}
			
		return list;
	}

	@Override
	public boolean insertInfo(String districtId, Map map) {
		// TODO Auto-generated method stub
		return false;
	}

}
