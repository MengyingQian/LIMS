package com.ddxq.employee.guanliyuan.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.cache.CacheUtil;
import com.ddxq.employee.guanliyuan.dao.GuanLiYuanDao;
import com.ddxq.employee.vrcode.service.GrzxService;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：+-
* 2016年11月30日 下午3:14:00 
* 类说明 
*/
@Service("guanLiYuanServiceImpl")
public class GuanLiYuanServiceImpl implements GuanLiYuanService {
	
	@Autowired
	private GuanLiYuanDao guanliyuandao;
	@Autowired
	private CacheUtil cacheUtil;

	@Override
	public boolean removeInfo(String account) {
		return guanliyuandao.removeInfo(account);
	}

	@Override
	public String getcert(String id) {
		Random rand = new Random(); 	
		String certcode = String.valueOf(rand.nextInt(8000) + 1000) + String.valueOf(rand.nextInt(80) + 10);
		cacheUtil.stringPutTime(certcode, id, 1800);
		return String.valueOf(certcode);
	}
	@Override
	public boolean getServicesStatus(String districtID, int type) {
		String rediskey = null;
		if (type == 1){// 超市
			rediskey="districtid:"+districtID+":goods:open";
		} else if (type == 2){// 直播
			rediskey="districtid:"+districtID+":servs:xqzb:open";
		} else if (type == 3 ){// 快递
			rediskey="districtid:"+districtID+":servs:kdds:open";
		} else if (type == 4 ){// 租赁
			rediskey="districtid:"+districtID+":servs:fwzs:open";
		}else if (type == 5 ){// 招聘
			rediskey="districtid:"+districtID+":servs:zpqz:open";
		}
		if(cacheUtil.stringGet(rediskey)==null||cacheUtil.stringGet(rediskey).equals("")||cacheUtil.stringGet(rediskey).equals("0")){
			return false;
		}
		return true;
	}
	@Override
	public boolean changServicesStatus(JSONObject obj) {
		String districtId = obj.getString("districtId");
		String status = obj.getString("status");
		int type = obj.getInt("type");
		String rediskey = null;
		if (type == 1){// 超市
			rediskey="districtid:"+districtId+":goods:open";
		} else if (type == 2){// 直播
			rediskey="districtid:"+districtId+":interact:xqzb:open";
		} else if (type == 3 ){// 快递
			rediskey="districtid:"+districtId+":servs:kdds:open";
		}else if (type == 4 ){// 快递
			rediskey="districtid:"+districtId+":servs:fwzs:open";
		}else if (type == 5 ){// 快递
			rediskey="districtid:"+districtId+":servs:zpqz:open";
		}
		if (status.equals("0")){
			cacheUtil.stringPut(rediskey, "0");
			return false;
		} else {
			cacheUtil.stringPut(rediskey, "1");
			return true;
		}
	}
	@Override
	public Map<String, Object> insertInfo(Map map) {
		return guanliyuandao.insertInfo(map);
	}
	@Override
	public JSONObject getUserViaActor(String districtId, String actor) {
		return guanliyuandao.getUserViaActor(districtId, actor);
	}
}
 