package com.ddxq.boss.base.profession.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月13日 下午3:20:59 
* 类说明 
*/
@Repository("professionDao")
public class ProfessionDaoImpl implements ProfessionDao {
	
	@Autowired
	BaseDAO baseDAO;
	/**
	 * 得到职业信息
	 * <p>Title: AllProfessiongs</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.jzsn.weixin.base.dao.ProfessionDao#AllProfessiongs()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getAllProfessiongs() {
		String sql = " select * from  profession ;";
		return  baseDAO.queryForList(sql);
	}

	@Override
	public String getProfession_name(int profession_id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("profession_id", profession_id);
		String sql =  " select profession_name from  profession where profession_id=:profession_id ";
		map = baseDAO.queryForMap(sql, map);
		return (String)map.get("profession_name");
	}

}
