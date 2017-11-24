package com.ddxq.system.distman.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.util.md5.MD5Util;
@Repository("distManagerDao")
public class DistManagerDaoImpl implements DistManagerDao{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	MD5Util mD5Util;
	@Override
	public boolean insertInfo(Map map) {
		if(check(map.get("employeeId").toString())){
			return false;
		}
		String sql="insert into ddxq_employees (employeeId,passWD,employeeActor,districtId,employeeName,employeeNickName,employeeIdCard,employeeSex,employeeBirth,employeeMobile,employeeEmail,employeeProvince,employeeCity,employeeAddr,employeePostcode,employeeDepart,employeeSalary,employeeLevel,employeePoints,status,notes) values(:employeeId,:passWD,:employeeActor,:districtId,:employeeName,:employeeNickName,:employeeIdCard,:employeeSex,:employeeBirth,:employeeMobile,:employeeEmail,:employeeProvince,:employeeCity,:employeeAddr,:employeePostcode,:employeeDepart,:employeeSalary,:employeeLevel,:employeePoints,:status,:notes)";
		return baseDAO.executeNamedCommand(sql, map);
	}
	public boolean check(String employeeId) {
		
		String sql="select employeeId from ddxq_employees where employeeId=?; ";
		Map map=baseDAO.queryForMap(sql, new Object[]{employeeId});
		if(map==null){
			return true;
		}
		return false;
	}
	private void formDate(List<Map<String, Object>> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Timestamp ts=(Timestamp) map.get("created");
			Timestamp ts2=(Timestamp) map.get("updated");
			java.sql.Date date=(java.sql.Date) map.get("employeeBirth");
			java.util.Date d=new java.util.Date (date.getTime());
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	        String date3 = sdf.format(ts);
	        map.put("employeeBirth",sdf.format(d));
	        String date2 = sdf.format(ts2); 
	        map.put("created", date3);	         
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
		try {
			map.put("passWD", mD5Util.StringToMD5_32(map.get("passWD").toString()));
		} catch (Throwable e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		String sql = "update ddxq_employees set passWd=:passWD,employeeActor=:employeeActor,districtId=:districtId,employeeName=:employeeName,employeeNickName=:employeeNickName,employeeIdCard=:employeeIdCard,employeeSex=:employeeSex,employeeBirth=:employeeBirth,employeeMobile=:employeeMobile,employeeEmail=:employeeEmail,employeeProvince=:employeeProvince,employeeCity=:employeeCity,employeeAddr=:employeeAddr,employeePostcode=:employeePostcode,employeeDepart=:employeeDepart,employeeSalary=:employeeSalary,employeeLevel=:employeeLevel,employeePoints=:employeePoints,status=:status,notes=:notes where employeeId=:employeeId;";
		return baseDAO.executeNamedCommand(sql, map);
	}

	@Override
	public boolean removeInfo(String account) {
		String sql ="delete from ddxq_employees where employeeId=?;";		
		return baseDAO.executeCommand(sql, new Object[]{account});
	}

	@Override
	public List<Map<String, Object>> getUserViaActorid(String actorid) {
		//
		String sql="select created,updated,employeeId,passWD,employeeActor,districtId,employeeName,employeeNickName,employeeIdCard,employeeSex,employeeBirth,employeeMobile,employeeEmail,employeeProvince,employeeCity,employeeAddr,employeePostcode,employeeDepart,employeeSalary,employeeLevel,employeePoints,status,notes from ddxq_employees where employeeActor=?;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{actorid});
		formDate(list);
		return list;
	}

}
