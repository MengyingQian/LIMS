package com.ddxq.system.poweruser.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
@Repository("userDao")
public class PowerUserDaoImpl implements PowerUserDao{
	@Autowired
	BaseDAO baseDAO;
	@Override
	public boolean insertInfo(Map map) {

		String sql="insert into papers (title ,author,publish_year,publish_month_date,department,url,status) values(:title ,:author,:publish_year,:publish_month_date,:department,:url,:status) ;";
		return baseDAO.executeNamedCommand(sql, map);
	}
	@Override
	public List<Map<String, Object>> getUserViaActorid(String actorid) {
		//String sql ="select * from ddxq_sysman where actorid=? ;";
		String sql ="select * from papers where publish_year=? ;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{actorid});
		formDate(list);
		return list;
	}
	@Override
	public List<Map<String, Object>> getUserStatus(String actorid) {
		//String sql ="select * from ddxq_sysman where actorid=? ;";
		String sql ="select * from papers where status=? ;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{actorid});
		formDate(list);
		return list;
	}
	@Override
	public List<Map<String, Object>> getUserPosition(String actorid) {
		//String sql ="select * from ddxq_sysman where actorid=? ;";
		String sql ="select * from members where position=? ;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{actorid});
		formDate(list);
		return list;
	}
	private void formDate(List<Map<String, Object>> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Timestamp ts=(Timestamp) map.get("create_time");
			Timestamp ts2=(Timestamp) map.get("update_time");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	        String date = df.format(ts);
	        String date2 = df.format(ts2); 
	        map.put("create_time", date);	         
	        map.put("update_time", date2);
	        for (Map.Entry<String,Object> entry : map.entrySet() ){
	        	if (null == entry.getValue()){
	        		map.put(entry.getKey(), " ");
	        	}
	        }
		}
	}
	@Override
	public boolean editInfo(Map map) {
		String sql="update papers set title=:title,author=:author,publish_year=:publish_year,publish_month_date=:publish_month_date,department=:department,url=:url,status=:status,update_time=update_time where paper_id=:paper_id; ";
		return baseDAO.executeNamedCommand(sql, map);
	}
	@Override
	public boolean removeInfo(String account) {
		String sql ="delete from papers where paper_id=?;";		
		return baseDAO.executeCommand(sql, new Object[]{account});
	}
}
