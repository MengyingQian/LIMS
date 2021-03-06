package com.ddxq.admin.fwt.zpqz.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.admin.fwt.fwzs.dao.FwzsDao;
import com.ddxq.admin.fwt.zpqz.dao.ZpqzDao;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.common.cache.CacheUtil;

import net.sf.json.JSONObject;
@Service("zpqzService")
public class ZpqzServiceImpl implements ZpqzService{
	@Autowired
	ZpqzDao zpqzDao;
	@Autowired
	private CacheUtil cacheUtil;
	@Autowired
	BaseHBaseDAO baseHBaseDAO;
	@Override
	public JSONObject getInfo(String districtId) {
		List<Map> list = zpqzDao.getListInfo(districtId);
		formDate(list);
		JSONObject json = new  JSONObject();
		json.put("total", list.size());
		json.put("rows", list);
		return json;
	}
	@Override
	public JSONObject getInfo(String districtId,String type ) {
		List<Map> list = zpqzDao.getListInfo(districtId,type);
		formDate(list);
		JSONObject json = new  JSONObject();
		json.put("total", list.size());
		json.put("rows", list);
		return json;
	}
	@Override
	public boolean insertInfo(JSONObject jobj) {
		Map map=new HashMap();
		String docid =cacheUtil.getRandomString();
		String rediskey="districtid:"+jobj.getString("districtId")+":jobs:"+jobj.getString("type");
		cacheUtil.zsetAdd(rediskey, docid, 0-Double.parseDouble(jobj.getString("sort")));
		map.put("districtid", jobj.getString("districtId"));
		map.put("title", jobj.getString("title"));
		map.put("districtname", jobj.getString("districtname"));
		map.put("digest", jobj.getString("digest"));
		map.put("position", jobj.getString("position"));
		map.put("place", jobj.getString("place"));
		map.put("type", jobj.getString("type"));
		map.put("welfare", jobj.getString("welfare"));
		map.put("employer", jobj.getString("employer"));
		map.put("salary_from", jobj.getString("salary_from"));
		map.put("salary_to", jobj.getString("salary_to"));
		map.put("contact", jobj.getString("contact"));
		map.put("phone", jobj.getString("phone"));
		map.put("status", jobj.getString("status"));
		map.put("sort", (0-Double.parseDouble(jobj.getString("sort")))+"");
		map.put("docid", docid);
		map.put("created", String.valueOf(new Date().getTime()) );
		map.put("expired", String.valueOf(new Date().getTime()) );
		map.put("updated",String.valueOf(new Date().getTime()) );
		cacheUtil.hashPut(docid, map);
		map.put("description", jobj.getString("description"));
		List <Map<String,String>>list=new ArrayList<>();
		list.add(map);
		try {
			baseHBaseDAO.addData(docid, "ddxq-jobs",list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean updateInfo(String districtId,JSONObject jobj) {
		Map map=new HashMap();
		String rediskey="districtid:"+districtId+":jobs:"+jobj.getString("type");
		cacheUtil.zsetAdd(rediskey, jobj.getString("docid"), 0-Double.parseDouble(jobj.getString("sort")));
		map.put("districtid",  districtId);
		map.put("title", jobj.getString("title"));
		map.put("districtname", jobj.getString("districtname"));
		map.put("digest", jobj.getString("digest"));
		map.put("position", jobj.getString("position"));
		map.put("place", jobj.getString("place"));
		map.put("type", jobj.getString("type"));
		map.put("welfare", jobj.getString("welfare"));
		map.put("employer", jobj.getString("employer"));
		map.put("salary_from", jobj.getString("salary_from"));
		map.put("salary_to", jobj.getString("salary_to"));
		map.put("contact", jobj.getString("contact"));
		map.put("phone", jobj.getString("phone"));
		map.put("status", jobj.getString("status"));
		map.put("sort", (0-Double.parseDouble(jobj.getString("sort")))+"");
		map.put("docid",jobj.getString("docid"));
		map.put("updated",String.valueOf(new Date().getTime()) );
		cacheUtil.hashPut(jobj.getString("docid"), map);
		map.put("description", jobj.getString("description"));
		List <Map<String,String>>list=new ArrayList<>();
		list.add(map);
		try {
			baseHBaseDAO.addData(jobj.getString("docid"), "ddxq-jobs",list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean removeInfo(String districtId,String type,String docid,JSONObject jobj) {
		String rediskey="districtid:"+districtId+":jobs:"+type;
		cacheUtil.zsetRemove(rediskey, docid);
		cacheUtil.hashRemove(docid);
		try {
			baseHBaseDAO.deleteAllColumn("ddxq-jobs",docid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	private void formDate(List<Map> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Date ts= new Date(Long.valueOf((String) map.get("created")));
			Date ts2=new Date(Long.valueOf((String) map.get("updated")));
			Date ts3=new Date(Long.valueOf((String) map.get("expired")));
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			if(map.get("sort")==null){
				map.put("sort", "1");
			}else {
				map.put("sort", 0-Double.parseDouble(map.get("sort").toString()));
			}
			
	        String date = sdf.format(ts);
	        String date2 = sdf.format(ts2); 
	        String date3 = sdf.format(ts3);
	        map.put("expired", date3);
	        map.put("created", date);
	        map.put("updated", date2);
		}
	}
}
