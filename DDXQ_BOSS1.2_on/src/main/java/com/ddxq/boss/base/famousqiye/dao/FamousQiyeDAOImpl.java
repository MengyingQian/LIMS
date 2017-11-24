package com.ddxq.boss.base.famousqiye.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月24日 上午10:12:14 
* 类说明 
*/
@Repository("FamiousQiyeDAO")
public class FamousQiyeDAOImpl implements FamousQiyeDAO {

	@Autowired
	BaseDAO baseDAO;
	
	@Override
	public boolean insertFamousQiye(Map<String, Object> mymap) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into famous_qiye (  ");
		List<String> keylist =  new ArrayList<String>();
		List<Object> valuelist = new ArrayList<Object>();
		for(Map.Entry<String, Object> entry:mymap.entrySet()){
			if( entry.getValue()==null || entry.getValue().equals("")){}
			else{
				keylist.add(entry.getKey());
				valuelist.add(entry.getValue());
			}
		}
		String key = org.apache.commons.lang.StringUtils.join(keylist.toArray(),",");
		StringBuffer sb = new StringBuffer();
		sb.append("( ?");
		for(int i=0;i<keylist.size()-1;i++){
			sb.append(",?");
		}
		sb.append(" )");
		sql.append(key+")"+" "+"values" +sb.toString());
		Object []args = valuelist.toArray();
		return baseDAO.executeCommand(sql.toString(), args);
	}
}
