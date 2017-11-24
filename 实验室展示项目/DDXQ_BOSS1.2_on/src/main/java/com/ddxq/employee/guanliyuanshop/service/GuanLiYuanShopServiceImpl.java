package com.ddxq.employee.guanliyuanshop.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.cache.CacheUtil;
import com.ddxq.employee.guanliyuanshop.dao.GuanLiYuanShopDao;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：+-
* 2016年11月30日 下午3:14:00 
* 类说明 
*/
@Service("guanLiYuanShopService")
public class GuanLiYuanShopServiceImpl implements GuanLiYuanShopService {
	
	@Autowired
	private GuanLiYuanShopDao shopdao;
	@Autowired
	private CacheUtil cacheUtil;
	@Override
	public JSONObject getshopinfo(String districtId, String typeid) {
		JSONObject obj = new JSONObject();
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		 /*districtid:1:goods:04002*/
		if (typeid.equals("1")){
			//全部商品
			List<String> listmenu = getmenu(districtId);
			for (String dange : listmenu){
				String redisKeyB = "districtid:" + districtId + ":goods:" + dange;
				Set<String> md5s = cacheUtil.zsetRange(redisKeyB, 0, -1);
				for (String md5 : md5s){
					Map shangpin = cacheUtil.hashGet(md5);
					list.add(shangpin);
				}
			}
		} else {
			//今日优惠
			String redisKeyB = "districtid:" + districtId + ":goods:" + "00000";
			Set<String> md5s = cacheUtil.zsetRange(redisKeyB, 0, -1);
			for (String md5 : md5s){
				Map shangpin = cacheUtil.hashGet(md5);
				list.add(shangpin);
			}
		}
		formDate(list);
		obj.put("total",list.size());
		obj.put("rows", list);
		return obj;
	}
	public List<String> getmenu(String districtId){
    	String distA="districtid:"+districtId+":goods:menu:A";
    	Set<String> setA=cacheUtil.zsetRange(distA, 0, -1);
    	List<String> setB =  new LinkedList<String>();	
    	System.out.println("all menuA: " + setA.toString());
    	for (String idA : setA) {
    		System.out.println(" menuA: " + idA);
    		if (!idA.equals("00")){
    			String distB="districtid:"+districtId+":goods:menu:" + idA + ":B";
    			Set<String> setBb = cacheUtil.zsetRange(distB, 0, -1);
    			System.out.println(" menuB: " + setBb.toString());
    			setB.addAll(setBb);
    		}else {
    			continue;
    		}
		}
    	//除了 00000 意外的所有二级菜单
    	System.out.println("all menu :" + setB.toString());
    	return setB;
	}
	private void formDate(List<Map<String, Object>> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Date ts = new Date( Long.valueOf((String)map.get("created")));
			Date ts2 = new Date( Long.valueOf((String)map.get("updated")));
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
	@Override
	public boolean insertinfo(String districtId, String md5) {
		String redisKeyA = "districtid:"+districtId+":goods:menu:A";
		cacheUtil.zsetAdd(redisKeyA, "00", 0);
		String redisconti = "districtid:" + districtId + ":goods:" + "00000";
		cacheUtil.zsetAdd(redisconti,md5, 0);
		return true;
	}
	@Override
	public boolean removeInfo(String districtId, String md5) {
		String redisconti = "districtid:" + districtId + ":goods:" + "00000";
		cacheUtil.zsetRemove(redisconti, md5);
		long size = cacheUtil.zsetSize(redisconti);
		String redisKeyA = "districtid:"+districtId+":goods:menu:A";
		if (size < 1){
			cacheUtil.zsetRemove(redisKeyA, "00");
		}
		return true;
	}
}
 