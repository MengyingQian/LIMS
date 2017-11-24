package com.ddxq.admin.fwt.zpqz.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.boss.base.util.RandomString.RandomStringUtils;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;
@Repository("zpqzDao")
public class ZpqzDaoImpl implements ZpqzDao{
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
		String rediskey2="districtid:"+districtId+":jobs:apply";
		String rediskey1="districtid:"+districtId+":jobs:recruit";
		Set<String>set= cacheUtil.zsetRange(rediskey1, 0, -1);
		Set<String>set2= cacheUtil.zsetRange(rediskey2, 0, -1);
		List<Map>list=new ArrayList<>(); 
		for (String s:set){
			Map map1=cacheUtil.hashGet(s);
			try {
				Map map2=baseHBaseDAO.getResultScannMap("ddxq-jobs", s, s);
				map1.put("description",map2.get("description").toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(map1);
			
		}
		for (String s:set2){
			Map map1=cacheUtil.hashGet(s);
			try {
				Map map2=baseHBaseDAO.getResultScannMap("ddxq-jobs", s, s);
				map1.put("description",map2.get("description").toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(map1);
			
		}		
		return list;
	}
	@Override
	public List<Map> getListInfo(String districtId,String type) {
		String rediskey1="districtid:"+districtId+":jobs:"+type;
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
