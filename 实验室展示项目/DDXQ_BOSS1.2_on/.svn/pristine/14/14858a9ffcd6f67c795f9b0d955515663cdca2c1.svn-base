package com.ddxq.admin.servs.yhby.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.admin.servs.yhby.dao.YhbyDao;
import com.ddxq.common.cache.CacheUtil;

import net.sf.json.JSONObject;
@Service("yhbyService")
public class YhbyServiceImpl implements YhbyService{
	@Autowired
	YhbyDao yhbyDao;
	@Override
	public void insertInfo(String redisKey, Map map) {
		yhbyDao.insertInfo(redisKey, map);
		
	}
	@Override
	public JSONObject getDataFromDistrictIdAndColumn(String districtId, String servs) {
		JSONObject retJobj = new JSONObject();
		if (districtId.trim().equals("") || districtId == "") {
			return new JSONObject();
		}
		List<Map> list = yhbyDao.getDataFromDistrictIdAndColumn(districtId, servs);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			System.out.println("sdsad" + map);
			map.put("districtId", Integer.parseInt(districtId));
			map.put("servs", parseToName(servs));
		}
		retJobj.put("total", list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	public String parseToName(String column) {
		switch (column) {
		case "gyrx":
			return "公益热线";
		case "tsjb":
			return "投诉举报";
		case "xqjg":
			return "小区机构";
		case "songshui":
			return "送水";
		case "songcai":
			return "送菜";
		case "songnai":
			return "送鲜奶";
		case "waimai":
			return "外卖";
		case "songmi":
			return "送米";	
		case "bjqh":
			return "保洁清护";
		case "bmay":
			return "保姆阿姨";
		case "wxst":
			return "维修疏通";
		case "lrhl":
			return "老人护理";
		case "kd":
			return "快递";
		case "yc":
			return "约车";
		case "by":
			return "搬运";
		case "mj":
			return "美甲";
		case "am":
			return "按摩";
		case "gyby":
			return "改衣补衣";
		case "xyyy":
			return "洗衣熨衣";
		case "sxbx":
			return "刷鞋补鞋";
		case "jj":
			return "家教";
		case "tcb":
			return "特长班";
		case "zybj":
			return "中医保健";
		case "sqys":
			return "社区医生";
			
		}
		return null;
	}
	@Override
	public void editInfo(String redisKey, Map map) {
		yhbyDao.editInfo(redisKey, map);
		
	}
	@Override
	public boolean removeInfo(JSONObject jobj) {
		int size=jobj.size();
		for (int i = 0; i < size; i++) {
			JSONObject j=jobj.getJSONObject(i+"");
			String districtId=j.getString("districtId");
			String servs=j.getString("servs");
			String docid=j.getString("docid");
			String redisKey="districtid:"+districtId+":servs:"+servs;
			yhbyDao.removeInfo(redisKey, docid);
		}		
		return true;
	}
}
