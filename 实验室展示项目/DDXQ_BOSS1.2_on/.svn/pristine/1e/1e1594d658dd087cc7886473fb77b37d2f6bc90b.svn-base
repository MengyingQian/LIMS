package com.ddxq.boss.base.dictionary.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年8月31日 上午11:37:38 
* 类说明 
*/
@Repository("dictionaryDao")
public class DictionaryDaoImpl implements DictionaryDao{
	
	@Autowired
	private BaseDAO baseDAO;



	@Override
	public List<Map<String, Object>> getEducation() {
		String sql =" select * from dictionary where is_used=1 and column_name='education' ;";
		return baseDAO.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getAge() {
		String sql =" select * from dictionary where is_used=1 and column_name='age_scope' ;";
		return baseDAO.queryForList(sql);
	}

}
