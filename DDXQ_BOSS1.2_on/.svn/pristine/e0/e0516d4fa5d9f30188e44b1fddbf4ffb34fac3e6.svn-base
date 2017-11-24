package com.ddxq.boss.base.shourufanwei.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
/**
 * 
 * ClassName:ShouruFanweiDaoImpl
 * Description:TODO
 * @author Liu
 * @date 2016年5月20日 上午10:41:19
 *
 */
@Repository("shouruFanweiDao")
public class ShouruFanweiDaoImpl implements ShouruFanweiDao{
	@Autowired
	BaseDAO baseDao;
	
	public List<Map<String, Object>> getShouruFanwei(){
		String sql = "select * from income;";
		List<Map<String, Object>> resList = baseDao.queryForList(sql);
		return resList;
	}
}
