package com.ddxq.boss.base.config.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

@Repository("configMySQLDao")
public class ConfigMySQLDaoImpl implements ConfigMySQLDao{
	
	@Autowired
	BaseDAO baseDAO;
	
    public Map<String, Object> getconfig(){
    	String sql = "select * from config;";
    	Map<String, Object> map = baseDAO.queryForMap(sql);
    	return map;
    }
    
    public Map<String, Object> getconfigViaIndex(int index){
    	String sql = "select * from config where id="+index+";";
    	Map<String, Object> map = baseDAO.queryForMap(sql);
    	return map;
    }
}
