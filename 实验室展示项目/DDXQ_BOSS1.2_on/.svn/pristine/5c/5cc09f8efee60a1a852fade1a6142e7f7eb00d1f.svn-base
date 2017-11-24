package com.ddxq.employee.kuaidi.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.boss.base.util.RandomString.RandomStringUtils;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;
@Repository("kuaiDiDao")
public class KuaiDiDaoImpl implements KuaiDiDao{

	@Autowired
	private BaseDAO basedao;
	
	@Override
	public Map getSellerInfo(String sellerID) {
		Map map = new HashMap();
		map.put("id", sellerID);
		String sql = "select employeeAddr,employeeMobile from ddxq_employees where id=:id;";
		return basedao.queryForMap(sql, map);
	}
}
