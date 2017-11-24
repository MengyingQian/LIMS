package com.ddxq.employee.seller.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
@Repository("sellerDao")
public class SellerDaoImpl implements SellerDao{
	
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
	public boolean insertInfo(String districtId,String menuid,String seller_id, Map map) {
		//菜单
		String menuAkey = "districtid:" + districtId + ":goods:menu:A";
/*		districtid:1:goods:menu:A
		districtid:1:goods:menu:02:B*/
		String menuBkey = "districtid:" + districtId + ":goods:menu:" + menuid.substring(0, 2) + ":B";
		String menuA = menuid.substring(0, 2);
		String menuB = menuid;
		Set menuAs = cacheUtil.zsetRange(menuAkey, 0, -1);
		if (!menuAs.contains(menuA)){
			cacheUtil.zsetAdd(menuAkey, menuA, getScore(menuA));
		}
		Set menuBs = cacheUtil.zsetRange(menuBkey, 0, -1);
		if (!menuBs.contains(menuB)){
			cacheUtil.zsetAdd(menuBkey, menuB, getScore(menuB));
		}
		String docid=cacheUtil.getRandomString();
		String detail = (String)map.get("detail");
		String photo_1 = (String)map.get("photo_1");
		String photo_2 = (String)map.get("photo_2");
		String photo_3 = (String)map.get("photo_3");
		map.remove("detail");
		map.remove("photo_1");
		map.remove("photo_2");
		map.remove("photo_3");
		map.put("docid", docid);
		cacheUtil.hashPut(docid, map);
	/*	districtid:1:goods:seller:40*/
		String sellerKey = "districtid:" + districtId + ":goods:seller:" + seller_id ;
		cacheUtil.setAdd(sellerKey, docid);	
		//存放 二级菜单下面的MD5（docid）的值，
/*		districtid:1:goods:05004*/
		String  menusetKey =  "districtid:" + districtId + ":goods:" + menuid;
		cacheUtil.zsetAdd(menusetKey, docid,99999);
		List<Map<String,String>> listC = new ArrayList();
		map.put("detail",detail );
		map.put("photo_1",photo_1);
		map.put("photo_2",photo_2);
		map.put("photo_3",photo_3);
		listC.add(map);
		try {
			baseHBaseDAO.addData(docid, "ddxq-goods", listC);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateInfo(String districtId, String menuid, String seller_id, Map map) {
		String docid = (String)map.get("docid");
		String detail = (String)map.get("detail");
		Map oldmap = cacheUtil.hashGet(docid);
		oldmap.put("caption", map.get("caption"));
		oldmap.put("price", map.get("price"));
		oldmap.put("unit", map.get("unit"));
		oldmap.put("label", map.get("label"));	
		oldmap.put("stock", map.get("stock"));
		oldmap.put("phone", map.get("phone"));
		oldmap.put("docid", map.get("docid"));
		cacheUtil.hashPut(docid, oldmap);
		List<Map<String,String>> listC = new ArrayList();
		oldmap.put("detail", map.get("detail"));
		listC.add(oldmap);
		try {
			baseHBaseDAO.addData(docid, "ddxq-goods", listC);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean removeInfo(String districtId, String menuid, String seller_id, Map map) {
		String docid = (String)map.get("docid");
		cacheUtil.hashRemove(docid);
		String sellerKey = "districtid:" + districtId + ":goods:seller:" + seller_id ;
		cacheUtil.setRemove(sellerKey, docid);	
		//存放 二级菜单下面的hash的值
		String  menusetKey =  "districtid:" + districtId + ":goods:" + menuid;
		cacheUtil.zsetRemove(menusetKey, docid);
		String menuAkey = "districtid:" + districtId + ":goods:menu:A";
		String menuBkey = "districtid:" + districtId + ":goods:menu:" + menuid.substring(0, 2) + ":B";
		long a = cacheUtil.zsetSize(menusetKey);
		if( cacheUtil.zsetSize(menusetKey) < 1 ){
			//删除二级菜单
			cacheUtil.zsetRemove(menuBkey, menuid);
		}
		long longB = cacheUtil.zsetSize(menuBkey);
		if (longB < 1){
			//删除一级菜单
			cacheUtil.zsetRemove(menuAkey, menuid.substring(0, 2));			
		}
		try {
			baseHBaseDAO.deleteAllColumn("ddxq-goods", docid);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		//删除今日优惠中的商品docid
		String salekey  =  "districtid:" + districtId + ":goods:00000"; 
		cacheUtil.zsetRemove(salekey, docid);
		long c = cacheUtil.zsetSize(salekey);
		if (c < 1){
			cacheUtil.zsetRemove(menuAkey,"00");			
		}
		return true;
	}
	
	@Override
	public List<Map> getDataFromDistrictIdAndColumn(String districtId) {
		String listKey="districtid:"+districtId+":cateid";
		List<Map> list = new ArrayList<>();
		for (String cateid:cacheUtil.SetGet(listKey)) {			
			String redisKey = "districtid:" + districtId + ":goods:" + cateid;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			Set<String> set=cacheUtil.zsetRange(redisKey, 0, -1);
			for (String time: set){
				Map map = cacheUtil.hashGet(time);
				if (map == null) {
					continue;
				}
				map.put("trueupdated", map.get("updated"));
				String time2=map.get("updated").toString();	
				map.put("updated", sdf.format(new Date(Long.parseLong(time2))));
				map.put("truecreated", map.get("created"));
				String time1=map.get("created").toString();	
				map.put("created", sdf.format(new Date(Long.parseLong(time1))));
				list.add(map);			
			}			
		}
		return list;
	}
	@Override
	public List<Map> getDataFromDistrictIdAndColumn(String districtId, String cateid) {
		List<Map> list = new ArrayList<>();
		String redisKey = "districtid:" + districtId + ":goods:" + cateid;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Set<String> set = cacheUtil.zsetRange(redisKey, 0, -1);
		for (String time : set) {
			Map map = cacheUtil.get(time, HashMap.class);
			if (map == null) {
				continue;
			}
			map.put("trueupdated", map.get("updated"));
			String time2 = map.get("updated").toString();
			map.put("updated", sdf.format(new Date(Long.parseLong(time2))));
			map.put("truecreated", map.get("created"));
			String time1=map.get("created").toString();	
			map.put("created", sdf.format(new Date(Long.parseLong(time1))));
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map> getSellerCommodity(String districtId, String sellerID) {
		String setKey = "districtid:" + districtId + ":goods:seller:" + sellerID;
		List<Map> result = new LinkedList<Map>();
		Set<String> set = cacheUtil.SetGet(setKey);
		for (String keymd5:set){
			Map  good = cacheUtil.hashGet(keymd5);
			Map hbase = new HashMap();
			try {
				hbase = baseHBaseDAO.getResultScannMap("ddxq-goods", keymd5, keymd5);
				good.put("photo_1", hbase.get("photo_1"));
				good.put("photo_2", hbase.get("photo_2"));
				good.put("photo_3", hbase.get("photo_3"));
				good.put("detail", hbase.get("detail"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			result.add(good);
		}
		return result;
	}
	@Override
	public Map getSellerInfo(String sellerID) {
		Map map = new HashMap();
		map.put("id", sellerID);
		String sql = "select employeeMobile from ddxq_employees where id=:id;";
		return basedao.queryForMap(sql, map);
	}
	
	private  int getScore(String cateid){
		Map map = new HashMap<>();
		String sql = "select ordernum from ddxq_goods_cates where cateid=:cateid";
		map.put("cateid", cateid);
		Map result = basedao.queryForMap(sql, map);
		if (null != result && null != result.get("ordernum") ){
			return (int)result.get("ordernum");
		}
		return 0;
	}
}
