package com.ddxq.employee.seller.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.cache.CacheUtil;
import com.ddxq.employee.seller.dao.SellerDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("sellerService")
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private CacheUtil cacheUtil;
	
	@Override
	public JSONObject getDataFromDistrictIdAndColumn(String districtId) {
		JSONObject retJobj = new JSONObject();
		if (districtId.trim().equals("") || districtId == "") {
			return new JSONObject();
		}
		List<Map> list = sellerDao.getDataFromDistrictIdAndColumn(districtId);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			map.put("districtId",districtId);
		}
		retJobj.put("total", list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public JSONObject getDataFromDistrictIdAndColumn(String districtId, String cateid) {
		JSONObject retJobj = new JSONObject();
		if (districtId.trim().equals("") || districtId == "") {
			return new JSONObject();
		}
		List<Map> list = sellerDao.getDataFromDistrictIdAndColumn(districtId,cateid);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			map.put("districtId",districtId);
		}
		retJobj.put("total", list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public JSONObject getGoods(String districtId, String sellerId) {
		List<Map> list = sellerDao.getSellerCommodity(districtId, sellerId);
		formDate(list);
		JSONObject json = new  JSONObject();
		json.put("total", list.size());
		json.put("rows", list);
		return json;
	}
	@Override
	public Map getSellerInfo(String sellerID) {
		return sellerDao.getSellerInfo(sellerID);
	}
	@Override
	public List<Map> getModelMenu() {
		List<Map> list = new ArrayList<Map>();
		String rediskeyA = "ddxq:goods:menu:A";
		Map menuA = cacheUtil.hashGet(rediskeyA);
		Set set =menuA.keySet();
		String rediskeyB=null;
		for (Object s : set){
			Map map1 = new HashMap();
			if (s.equals("00")){
				continue;
			} else{
				map1.put("idA", s);
				map1.put("nameA", menuA.get(s));
				list.add(map1);
			} 
		}
		return list;
	}
	@Override
	public JSONObject getMenuB(String idA) {
		JSONObject  obj = new JSONObject();
		JSONArray array = new JSONArray();
		String redisKeyB = "ddxq:goods:menu:" + idA + ":B";
		Map menuB = cacheUtil.hashGet(redisKeyB);
		Set set = menuB.keySet();
		for (Object s : set){
			JSONObject  obj1 = new JSONObject();
			obj1.put("idB", s);
			obj1.put("nameB", menuB.get(s));
			array.add(obj1);
		}
		obj.put("menuB", array);
		return obj;
	}
	@Override
	public boolean insertInfo(JSONObject jobj) {
		Map map=new HashMap();
		map.put("districtId", jobj.getString("districtId"));
		map.put("caption", jobj.getString("caption"));
		map.put("price", jobj.getString("price"));
		map.put("unit", jobj.getString("unit"));
		map.put("menuid", jobj.getString("menuid"));
		map.put("label", jobj.getString("label"));	
		map.put("seller", jobj.getString("seller"));
		map.put("stock", jobj.getString("stock"));
		map.put("phone", jobj.getString("phone"));
		map.put("photo_min", jobj.getString("photo_min"));
		map.put("photo_1", jobj.getString("photo_1"));
		map.put("photo_2", jobj.getString("photo_2"));
		map.put("photo_3", jobj.getString("photo_3"));
		map.put("detail", jobj.getString("detail"));
		map.put("created", String.valueOf(new Date().getTime()) );
		map.put("updated",String.valueOf(new Date().getTime()) );
		String districtId=jobj.getString("districtId");
		String menuid=jobj.getString("menuid");
		return sellerDao.insertInfo(districtId,menuid,jobj.getString("seller_id"),map);
	}
	private void formDate(List<Map> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Date ts= new Date(Long.valueOf((String) map.get("created")));
			Date ts2=new Date(Long.valueOf((String) map.get("updated")));
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	        String date3 = sdf.format(ts);
	        String date2 = sdf.format(ts2); 
	        map.put("created", date3);	         
	        map.put("updated", date2);
/*	        if (null == map.get("detail")){
	        	map.put("detail"," ");
	        }
	        if (null == map.get("photo_1")){
	        	map.put("photo_1"," ");
	        }
	        if (null == map.get("photo_2")){
	        	map.put("photo_2"," ");
	        }
	        if (null == map.get("photo_3")){
	        	map.put("photo_3"," ");
	        }*/
	        for (Map.Entry<String,Object> entry : map.entrySet() ){
	        	if (null == entry.getValue()){
	        		map.put(entry.getKey(), " ");
	        	}
	        }
		}
	}
	@Override
	public boolean updateInfo(JSONObject jobj) {
		Map map=new HashMap();
		
/*		retData.districtId=$("#districtId2").numberbox('getValue');	
		retData.caption=$("#caption2").textbox('getValue');
		retData.price=$("#price2").textbox('getValue');
		retData.unit=$("#unit2").textbox('getValue');
		retData.stock=$("#stock2").numberbox('getValue');	
		retData.phone=$("#phone2").numberbox('getValue');
		retData.docid=$("#docid2").val();	
		retData.detail=$("#detail2").textbox('getValue');*/	
		map.put("districtId", jobj.getString("districtId"));
		map.put("caption", jobj.getString("caption"));
		map.put("price", jobj.getString("price"));
		map.put("unit", jobj.getString("unit"));
		map.put("seller", jobj.getString("seller"));
		map.put("label", jobj.getString("label"));	
		map.put("stock", jobj.getString("stock"));
		map.put("phone", jobj.getString("phone"));
		map.put("detail", jobj.getString("detail"));
		map.put("updated",String.valueOf(new Date().getTime()) );
		map.put("docid",jobj.getString("docid") );
		
		String districtId=jobj.getString("districtId");
		String menuid=jobj.getString("menuid");
		return sellerDao.updateInfo(districtId,menuid,jobj.getString("seller"),map);
	}
	@Override
	public boolean removeInfo(JSONObject jobj) {
		Map map=new HashMap();
		map.put("districtId", jobj.getString("districtId"));
		map.put("seller", jobj.getString("seller"));
		map.put("docid", jobj.getString("docid"));
		String districtId=jobj.getString("districtId");
		String menuid=jobj.getString("menuid");
		return sellerDao.removeInfo(districtId, menuid, jobj.getString("seller"), map);
	}
	@Override
	public JSONObject removeMenu() {
		JSONObject  obj = new JSONObject();
		String  menusetKey =  "districtid:1:goods:menu:A";
		cacheUtil.zsetRemove(menusetKey, "00");
		cacheUtil.zsetRemove(menusetKey, "01");
		cacheUtil.zsetRemove(menusetKey, "08");
		cacheUtil.zsetRemove(menusetKey, "09");
		cacheUtil.zsetRemove(menusetKey, "12");
		return obj;
	}
	
}
