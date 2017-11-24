package com.ddxq.employees.info.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.employees.info.dao.InfoDao;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年12月12日 上午10:48:29 
* 类说明 
*/
@Service("infoService")
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoDao infodao;
	
	@Override
	public JSONObject getinfo(String id) {
		return  infodao.getUserinfo(id);
	}

	@Override
	public boolean editInfo(Map map) {
		return infodao.editInfo(map);
	}

	@Override
	public boolean changepwd(String id, String pre, String pwd) {
		return infodao.changepwd(id, pre, pwd);
	}
}
 