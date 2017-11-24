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

		String sql="insert into ddxq_sysman (account ,pwd,actorid,mobile,name,ims,privmod,privcate,level,status,memo) values(:account ,:password,:actorid,:mobile,:name,:ims,:privmod,:privcate,level,:status,:memo) ;";
		return baseDAO.executeNamedCommand(sql, map);
	}
	@Override
	public List<Map<String, Object>> getUserViaActorid(String actorid) {
		String sql ="select * from ddxq_sysman where actorid=? ;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{actorid});
		formDate(list);
		return list;
	}
	private void formDate(List<Map<String, Object>> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Timestamp ts=(Timestamp) map.get("created");
			Timestamp ts2=(Timestamp) map.get("updated");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	        String date = df.format(ts);
	        String date2 = df.format(ts2); 
	        map.put("created", date);	         
	        map.put("updated", date2);
	        for (Map.Entry<String,Object> entry : map.entrySet() ){
	        	if (null == entry.getValue()){
	        		map.put(entry.getKey(), " ");
	        	}
	        }
		}
	}
	@Override
	public boolean editInfo(Map map) {
		String sql="update ddxq_sysman set pwd=:password,actorid=:actorid,mobile=:mobile,name=:name,ims=:ims,privmod=:privmod,privcate=:privcate,level=:level,status=:status,memo=:memo,updated=updated where account=:account; ";
		return baseDAO.executeNamedCommand(sql, map);
	}
	@Override
	public boolean removeInfo(String account) {
		String sql ="delete from ddxq_sysman where account=?;";		
		return baseDAO.executeCommand(sql, new Object[]{account});
	}
}
