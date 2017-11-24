package com.ddxq.admin.xxl.xqjs.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.common.cache.CacheUtil;
@Repository("xqjsDao")
public class XqjsDaoImpl implements XqjsDao {
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	BaseHBaseDAO baseHBaseDAO;
	@Override
	public void add(String districtId, String type,String content) {

		String rediskey="districtid:"+districtId+":configs:"+type;
		String docid=cacheUtil.getRandomString();
		cacheUtil.stringPut(rediskey, docid);
		List<Map<String,String>> list=new ArrayList<>();
		Map map=new HashMap<>();
		map.put("content", content);
		list.add(map);
		try {
			baseHBaseDAO.addData(docid, "ddxq-articles", list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
