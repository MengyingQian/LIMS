package com.ddxq.zshiro.authentication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.zshiro.authentication.dao.QuanXianDao;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年10月17日 上午11:23:31 
* 类说明 
*/
@Service("quanXianServiceImpl")
public class QuanXianServiceImpl implements QuanXianService {
	
	@Autowired
	private QuanXianDao quanxiandao;

	@Override
	public Map<String, Object> getUser(String mobile) {
		return quanxiandao.getUser(mobile);
	}

	@Override
	public List<String> getRoles(String user_id) {
		Map<String,Object> user = quanxiandao.getUserRole(user_id);
		List<String> list = new ArrayList<String>();
		// anyone has role "salesman" 
		if("salesman".equals((String)user.get("role"))  ){
			list.add("salesman");
		}else{
			list.add("salesman");
			list.add((String)user.get("role") );
		}
		return list;
		
	}

	@Override
	public List<String> getPerms(String mobile) {
		return null;
	}
	

}
 