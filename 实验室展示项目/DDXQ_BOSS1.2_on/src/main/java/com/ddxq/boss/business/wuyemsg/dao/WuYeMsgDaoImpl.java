package com.ddxq.boss.business.wuyemsg.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
@Repository("wuYeMsgDao")
public class WuYeMsgDaoImpl implements WuYeMsgDao{
	@Autowired
	BaseDAO baseDAO;
	@Override
	public List getOpenIdViaDistrictId(int districtId) {
		String sql="select openId from ddxq_residents where districtId= ?;";
		ArrayList list=(ArrayList) baseDAO.queryForList(sql,new Object[]{districtId});
		System.out.println(list);
		return list;
	}

}
