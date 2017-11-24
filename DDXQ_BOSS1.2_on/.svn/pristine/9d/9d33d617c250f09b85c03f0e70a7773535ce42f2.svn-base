package com.ddxq.system.dictionary.service;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.cache.CacheUtil;
import com.ddxq.system.dictionary.dao.DictManagerDao;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年12月3日 上午9:39:16 
* 类说明 
*/
@Service("dictManagerService")
public class DictManagerServiceImpl implements DictManagerService {
	
	@Autowired
	private DictManagerDao dictmanagerdao;
	@Autowired
	private CacheUtil cacheUtil;

	@Override
	public JSONObject getMenu() {
		JSONObject json = new JSONObject();
		List <Map> menu = new LinkedList<>();
		List<Map> listA = dictmanagerdao.getMenuA();
		formDate(listA);
		for (Map map : listA){
			if (null == map.get("memo") ){
				map.put("memo", " ");
			}
			menu.add(map);
			List<Map> listB = dictmanagerdao.getMenuB((String)map.get("cateid"));
			formDate(listB);
			for (Map mapB : listB){
				if (null == mapB.get("memo") ){
					mapB.put("memo", " ");
				}
			}
			if(listB.size() >= 1 ){
				menu.addAll(listB);	
			}
		}
		json.put("total", menu.size());
		json.put("rows", menu);
		return json;
	}

	@Override
	public boolean insertInfo(JSONObject obj) {
		Map map = new HashMap();
		map.put("cateid", obj.get("cateid"));
		map.put("catename", obj.get("catename"));
		map.put("level", obj.get("level"));
		map.put("ordernum", obj.get("ordernum"));
		map.put("memo", obj.get("memo"));
		map.put("status", obj.get("status"));
		if (!dictmanagerdao.insertInfo(map)){
			return false;
		}
		updateRedisMenu();
		return true;
	}

	@Override
	public boolean updateInfo(JSONObject obj) {
		Map map = new HashMap();
		map.put("id", obj.get("id"));
		map.put("cateid", obj.get("cateid"));
		map.put("catename", obj.get("catename"));
		map.put("level", obj.get("level"));
		map.put("ordernum", obj.get("ordernum"));
		map.put("memo", obj.get("memo"));
		map.put("status", obj.get("status"));
		if (!dictmanagerdao.updateInfo(map)){
			return false;
		}
		updateRedisMenu();
		return true;
	}

	@Override
	public boolean removeInfo(JSONObject obj) {
		Map map = new HashMap();
		map.put("id", obj.get("id"));
		if (!dictmanagerdao.removeInfo(map)){
			return false;
		}
		updateRedisMenu();
		return true;
	}
	// write a common function to change redis menu
	private void updateRedisMenu(){
		List<Map> listA = dictmanagerdao.getMenuA();
		Map menuA = new HashMap();
		for (Map mapA : listA){
			menuA.put(mapA.get("cateid"), mapA.get("catename"));
			List<Map> listB = dictmanagerdao.getMenuB((String)mapA.get("cateid"));
			Map menuB = new HashMap();
			for(Map mapB : listB){
				menuB.put(mapB.get("cateid"), mapB.get("catename"));
			}
			String mebuBKey = "ddxq:goods:menu:" + mapA.get("cateid") + ":B";
			cacheUtil.hashRemove(mebuBKey);
			cacheUtil.hashPut(mebuBKey, menuB);
		}
		//change muenuA
		String menuAKey = "ddxq:goods:menu:A";
		cacheUtil.hashRemove(menuAKey);
		cacheUtil.hashPut(menuAKey, menuA);
	}
	private void formDate(List<Map> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Timestamp ts= (Timestamp) map.get("created");
			Timestamp ts2= (Timestamp) map.get("updated");
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	        String date3 = sdf.format(ts);
	        String date2 = sdf.format(ts2); 
	        map.put("created", date3);	         
	        map.put("updated", date2);
	        for (Map.Entry<String,Object> entry : map.entrySet() ){
	        	if (null == entry.getValue()){
	        		map.put(entry.getKey(), " ");
	        	}
	        }
		}
	}
}
 