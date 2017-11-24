package com.ddxq.admin.weixinmanage.dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.common.cache.CacheUtil;
@Repository("weiXinmanageDao")
public class WeiXinManageDaoImpl implements WeiXinmanageDao{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	BaseHBaseDAO baseHBaseDAO;
	@Autowired
	CacheUtil	cacheUtil;
	@Override
	public List<Map<String, Object>> showByDistrictId(int id) {
		Map map=cacheUtil.hashGet("districtid:"+id+":users");
		Set set=map.keySet();
		List<Map<String, Object>> list=new ArrayList<>();
		for (Object object : set) {
			try {
				Map<String, Object> map2= baseHBaseDAO.getResultScannMap("ddxq-users", object.toString(), object.toString());
				map2.put("logs", "");
				list.add(map2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		formDate(list);
		return list;
	}
	private void formDate(List<Map<String, Object>> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			//Long ti=Long.parseLong(map.get("subscribe_time").toString());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	        String date = df.format(new Date(Long.parseLong(map.get("created").toString())));
	        //String date2 = df.format(new Date(Long.parseLong(map.get("updated").toString())));
	        //Date date3=new Date(ti);
	        //map.put("subscribe_time", df.format(date3));
	        map.put("created", date);	         
	       // map.put("updated", date2);
	        for (Map.Entry<String,Object> entry : map.entrySet() ){
	        	if (null == entry.getValue()){
	        		map.put(entry.getKey(), " ");
	        	}
	        }
		}
	}
}
