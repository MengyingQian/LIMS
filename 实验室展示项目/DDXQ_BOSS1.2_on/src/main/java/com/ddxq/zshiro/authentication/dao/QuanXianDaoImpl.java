package com.ddxq.zshiro.authentication.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddxq.base.dao.BaseDAO;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年10月17日 上午11:22:51 
* 类说明 
*/

@Component("quanXianDaoImpl")
public class QuanXianDaoImpl implements QuanXianDao {
	
	@Autowired
	private BaseDAO baseDao;
	
	@Override
	public Map<String, Object> getUser(String mobile) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mobile", mobile);
		String sql =" select * from users where mobile=:mobile ";
		return baseDao.queryForMap(sql, map); 
		
	}

	@Override
	public Map<String, Object> getUserRole(String user_id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		String sql =" select tb1.* ,tb2.dictionary_eng_name as role from users as tb1  "
				+ "LEFT join dictionary as tb2 on tb1.company_position_id=tb2.dictionary_id "
				+ " where tb1.user_id=:user_id and tb2.table_name='users' and tb2.column_name='company_position_id' ;";
		return baseDao.queryForMap(sql, map); 
		
	}

}
 