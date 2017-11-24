package com.ddxq.admin.servs.yhby.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.common.cache.CacheUtil;
@Repository("yhbyDao")
public class YhbyDaoImpl implements YhbyDao{
	@Autowired
	private CacheUtil cacheUtil;
	@Autowired
	private BaseHBaseDAO baseHBaseDAO;
	@Override
	public boolean insertInfo(String redisKey, Map map) {	
		String docid=cacheUtil.getRandomString();
		String score=map.get("score").toString();
		if(score.trim().equals("")||score==null){
			long s=99999999;
			cacheUtil.zsetAdd(redisKey, docid, s);	
		}else{
			cacheUtil.zsetAdd(redisKey, docid, Integer.parseInt(score));	
		}	
		map.remove("score");
		map.put("docid", docid);
		cacheUtil.hashPut(docid, map);
		List<Map<String,String>> listC = new ArrayList();
		listC.add(map);
		try {
			baseHBaseDAO.addData(docid, "ddxq-servs", listC);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public List<Map> getDataFromDistrictIdAndColumn(String districtId, String servs) {
		String redisKey = "districtid:" + districtId + ":servs:" + servs;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map> list = new ArrayList<>();
		Set<String> set=cacheUtil.zsetRange(redisKey, 0, -1);
		for (String time: set){
			Map map = cacheUtil.hashGet(time);
			if (map == null) {
				continue;
			}
			map.put("truecreated", map.get("created"));
			String time1=map.get("created").toString();	
			map.put("created", sdf.format(new Date(Long.parseLong(time1))));
			
			map.put("trueupdated", map.get("updated"));
			String time2=map.get("updated").toString();	
			map.put("updated", sdf.format(new Date(Long.parseLong(time2))));
			list.add(map);			
		}		
		return list;
	}
	@Override
	public boolean editInfo(String redisKey, Map map) {		
		cacheUtil.hashPut(redisKey, map);	
		List<Map<String,String>> listC = new ArrayList();
		listC.add(map);
		try {
			baseHBaseDAO.addData(redisKey, "ddxq-servs", listC);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean removeInfo(String redisKey, String docid) {
		cacheUtil.zsetRemove(redisKey, docid);
		cacheUtil.hashRemove(docid);
		try {
			baseHBaseDAO.deleteAllColumn("ddxq-servs", docid);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
