package com.ddxq.boss.base.dao.zhiye;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

@Repository("zhiyeDao")
public class ZhiyeDaoImpl implements ZhiyeDao{
	@Autowired
	BaseDAO baseDao;
    
	public List<Map<String, Object>> getAllZhiye(){
		String sql = "select * from profession;";
		List<Map<String, Object>> resList = baseDao.queryForList(sql);
		return resList;
	}
}
