package com.ddxq.boss.base.industry.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月15日 上午11:29:17 
* 类说明 
*/
@Repository("industryDao")
@SuppressWarnings("unchecked")
public class IndustryDaoImpl implements IndustryDao {
	@Autowired
	BaseDAO baseDAO;

	@Override
	public List<Map<String, Object>> getAllIndustry() {
		String sql = " select * from  industry ;";
		return  baseDAO.queryForList(sql);
	}

	@Override
	public String getIndustry_name(int industry_id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("industry_id", industry_id);
		String sql =  " select industry_name from  industry where industry_id=:industry_id ";
		map = baseDAO.queryForMap(sql, map);
		return (String)map.get("industry_name");
	}
}
