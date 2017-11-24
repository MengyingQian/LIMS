package com.ddxq.admin.xxl.xwzx.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.admin.xxl.xwzx.dao.XwzxDao;
import com.ddxq.boss.base.util.string2map.StringToMap;
import com.ddxq.common.cache.CacheUtil;
import com.ddxq.common.config.HostConfig;

import net.sf.json.JSONObject;

@Service("xwzxService")
public class XwzxServiceImpl implements XwzxService {
	
	@Autowired
	private XwzxDao xwzxDao;
	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	private HostConfig hostConfig;


	@Override
	public List getXwzxListTime(String redisKey) {

		return xwzxDao.getXwzxListTime(redisKey);
	}

	@Override
	public String getDistrictName(int districtId) {

		return xwzxDao.getDistrictName(districtId);
	}

	@Override
	public JSONObject getDataFromDistrictIdAndColumn(String districtId, String column) {
		JSONObject retJobj = new JSONObject();
		if (districtId.trim().equals("") || districtId == "") {
			return new JSONObject();
		}
		List<Map> list = xwzxDao.getDataFromDistrictIdAndColumn(districtId, column);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			map.put("districtId", Integer.parseInt(districtId));
			map.put("column", parseToName(column));
		}
		retJobj.put("total", list.size());
		retJobj.put("rows", list);
		return retJobj;
	}

	@Override
	public String parseToName(String column) {
		switch (column) {
		case "wytz":
			return "物业通知";
		case "xmqs":
			return "寻觅启事";
		case "sqgg":
			return "社区公告";
		case "xqhd":
			return "小区活动";
		case "stzz":
			return "社团组织";
		case "yewh":
			return "业委会";
		case "juwh":
			return "居委会";
		case "pchs":
			return "派出所";
		default:
			return "";
		}
	}

	@Override
	public String parseToEng(String column) {
		switch (column) {
		case "物业通知":
			return "wytz";

		case "寻觅启示":
			return "xmqs";
		case "社区公告":
			return "sqgg";

		case "小区活动":
			return "xqhd";

		}
		return null;
	}

	@Override
	public List<String> jsonListToList(JSONObject obj) {
		String districtId = "districtId";
		String newscenter = "newscenter";
		int size = obj.size();
		List<String> retList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Map<String, String> map = (Map<String, String>) obj.get(i + "");

			retList.add("districtid:" + ((Integer.parseInt(map.get(districtId)) + 100000) + "").substring(1) + ":"
					+ newscenter + ":" + map.get(newscenter) + ":" + map.get("time"));
		}
		return retList;
	}

	@Override
	public void remove(String redisKey,String docid,String district,String info) {
		xwzxDao.remove(redisKey,docid,district,info);
		
	}

	@Override
	public String getTrackserver() {
		return hostConfig.getMobile_domainName();
	}

	@Override
	public JSONObject insertNotice(JSONObject jobj) {
		return xwzxDao.insertNotice(jobj);
	}

	@Override
	public String getMessageType(String actorid) {
		switch (actorid) {
		case "110":
			return "pchs";
		case "200":
			return "wytz";
		case "300":
			return "juwh";
		case "500":
			return "yewh";
		default:
			return "";
		}
	}

}
