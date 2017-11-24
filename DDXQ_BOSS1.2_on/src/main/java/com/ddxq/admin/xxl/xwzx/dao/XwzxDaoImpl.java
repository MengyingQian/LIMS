package com.ddxq.admin.xxl.xwzx.dao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.common.cache.CacheUtil;

import net.sf.json.JSONObject;
@Repository("xwzxDao")
public class XwzxDaoImpl implements XwzxDao{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	BaseHBaseDAO baseHBaseDAO;
	@Override
	public String getDistrictName(int districtId) {
		String sql="select alias from ddxq_districts where districtId=?; ";
		Map map=baseDAO.queryForMap(sql, new Object[]{districtId});
		return (String) map.get("alias");
	}
	@Override
	 public List getXwzxListTime(String redisKey){
		Long size=cacheUtil.listSize(redisKey);
		List list=new ArrayList<>();
		 for (int i = 0; i < size; i++) {
			list.add(cacheUtil.listIndex(redisKey, i));
		}
		 return list;
	 }
	@Override
	public List<Map> getDataFromDistrictIdAndColumn(String districtId, String column) {
		String redisKey = "districtid:" + districtId + ":info:" + column;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map> list = new ArrayList<>();
		
		for (String docid :cacheUtil.zsetRange(redisKey, 0, -1)) {	
			System.out.println(docid);
			if(docid.equals("")){
				continue;
			}
			System.out.println(cacheUtil.hashGet(docid));
			Map map = cacheUtil.hashGet(docid);
			if (map.size()==0) {
				continue;
			}
			map.put("districtId", districtId);
			map.put("truecreated", map.get("created"));
			map.put("created", sdf.format(new Date(Long.parseLong((String)map.get("created")))));	
			map.put("trueupdated", map.get("updated"));
			map.put("updated", sdf.format(new Date(Long.parseLong((String)map.get("updated")))));					
			try {
				List<Map<String, Object>> res = baseHBaseDAO.getResultScanList("ddxq-news", docid, docid);
				if (res.size() == 0) {
					continue;
				} else {
					map.put("content", res.get(0).get("content"));
					list.add(map);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void remove(String redisKey,String docid,String district,String info) {
				cacheUtil.zsetRemove(redisKey, docid);		
				cacheUtil.hashRemove(docid);
				//未读消息
				String usersKey="districtid:"+district+":users";
				String unread="stats:districtid:"+district+":newscenter:unread:"+info;
				Set set=cacheUtil.hashGet(usersKey).keySet();
				if(set.size()!=0){
					for(Object obj: set){
						String openid=obj.toString();
						String value=cacheUtil.hashGet(unread, openid);
						if(value.equals("")){
							//cacheUtil.hashPut(unread, openid, docid);
						}else{
							value=value.replace(docid, "");
							cacheUtil.hashPut(unread, openid, value);
						}	
				    }
				}
		try {
			baseHBaseDAO.deleteAllColumn("ddxq-news", docid);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public JSONObject insertNotice(JSONObject jobj) {
		JSONObject retJobj = new JSONObject();
		String districtId=jobj.getString("districtId");
		String info=jobj.getString("info");//info is type
		//处理次数验证问题
		String recordkey = "districtid:" + districtId + ":inforecd:" + info;
		String num = cacheUtil.stringGet(recordkey);
		int numint = 0;
		if (null == num ){
			cacheUtil.stringPutTime(recordkey, "1", getMiao());
		}else  {
			numint = Integer.valueOf(num);
			if (numint >= 3){
				retJobj.put("success",false);
				retJobj.put("districtId",districtId);
				retJobj.put("info",info);
				retJobj.put("url", "/ddxq/admin/xxl/xwzx/searchData?districtIdFrom="+districtId+"&column="+info);
				return retJobj;
			}else {
				numint++;
				cacheUtil.stringPutTime(recordkey, String.valueOf(numint), getMiao());
			}
		}
		String docid=cacheUtil.getRandomString();
		String score=jobj.getString("score");
		String redisKey="districtid:"+districtId+":info:"+info;
		if(score.trim()=="" ||score.trim().equals("")||score==null){
			long s=9999999999999l-new Date().getTime();
			cacheUtil.zsetAdd(redisKey, docid, s);	
		}else{
			cacheUtil.zsetAdd(redisKey, docid, Integer.parseInt(score));	
		}		
		String title=jobj.getString("title");
		String content=jobj.getString("content");
		String link=jobj.getString("link");
		String author=jobj.getString("author");
		String publisher=jobj.getString("publisher");
		String subtitle=jobj.getString("subtitle");
		Map row = new HashMap();
		List<Map<String,String>> list = new ArrayList();
		row.put("content", content);
		list.add(row);
		try {//放置内容
			baseHBaseDAO.addData(docid, "ddxq-news", list);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		//未读消息
		String usersKey="districtid:"+districtId+":users";
		String unread="stats:districtid:"+districtId+":newscenter:unread:"+info;
		Set set=cacheUtil.hashGet(usersKey).keySet();
		if(set.size()!=0){
			for(Object obj: set){
				String openid=obj.toString();
				String value=cacheUtil.hashGet(unread, openid);
				if(value.equals("")){
					cacheUtil.hashPut(unread, openid, docid);
				}else{
					cacheUtil.hashPut(unread, openid, value+" "+docid);
				}	
		    }
		}
		
		//放置redis
		Map map=new HashMap<>();
		map.put("subtitle",subtitle);
		map.put("title", title);
		map.put("docid", docid);
		map.put("link", link);
		map.put("author", author);
		map.put("publisher",publisher);
		map.put("created", new Date().getTime()+"");
		map.put("updated", new Date().getTime()+"");
		cacheUtil.hashPut(docid, map);
		retJobj.put("success",true);
		retJobj.put("districtId",districtId);
		retJobj.put("info",info);
		retJobj.put("url", "/ddxq/admin/xxl/xwzx/searchData?districtIdFrom="+districtId+"&column="+info);
		return retJobj;
	}
	//get second 
	public  int getMiao(){
		Calendar curDate = Calendar.getInstance();
		Calendar tommorowDate = new GregorianCalendar(curDate
				.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
				.get(Calendar.DATE) + 1, 0, 0, 0);
		return (int)(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;
	}
}
