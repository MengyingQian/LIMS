package com.ddxq.boss.base.country.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月12日 下午2:35:32 
* 类说明 
*/
@Repository("countryDao")
public class CountryDaoImpl implements CountryDao {
	@Autowired
	BaseDAO baseDAO;
	
	/**
	 * 根据name，返回数据
	 * <p>Title: NameGetID</p>
	 * <p>Description: </p>
	 * @param country_name
	 * @return
	 * @see com.jzsn.weixin.base.dao.CountryDao#NameGetID(java.lang.String)
	 */
	@Override
	public String NameGetID(String country_name) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("country_name", country_name);
			Map<String,Object> result= baseDAO.queryForMap(" select * from country  where country_name = :country_name ", map);
			String country_id= (String)result.get("country_id");
			if(null==country_id || "".equals(country_id) ){
				return "1";//默认设置为中国
			}
			return country_id;
	}

}
