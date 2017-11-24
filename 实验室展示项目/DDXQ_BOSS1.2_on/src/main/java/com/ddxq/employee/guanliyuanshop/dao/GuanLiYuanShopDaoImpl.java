package com.ddxq.employee.guanliyuanshop.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.util.md5.MD5Util;

import net.sf.json.JSONObject;
@Repository("guanLiYuanShopDao")
public class GuanLiYuanShopDaoImpl implements GuanLiYuanShopDao{
	@Autowired
	BaseDAO baseDAO;
/*	@Autowired
	MD5Util mD5Util;
	@Override
	public JSONObject getUserViaActor(String districtId, String actorid) {
		String sql="select id,openId,created,updated,employeeId,passWD,employeeActor,districtId,employeeName,employeeNation,employeeNickName,employeeIdCard,employeeSex,employeeBirth,employeeMobile,employeeEmail,employeeProvince,employeeCity,employeeAddr,employeePostcode,employeeDepart,employeeSalary,employeeLevel,employeePoints,status,notes from ddxq_employees where districtId=? and employeeActor=?;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{districtId,actorid});
		formDate(list);
		JSONObject retJobj = new JSONObject();
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public boolean insertInfo(Map map) {
		String password=map.get("passWD").toString();
		String passwd="";
		try {
			passwd=mD5Util.StringToMD5_32(password);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		map.put("passWD", passwd);
		if(check(map.get("employeeId").toString())){
			return false;
		}
		String sql="insert into ddxq_employees (employeeNation,employeeId,passWD,employeeActor,districtId,employeeName,employeeNickName,employeeIdCard,employeeSex,employeeBirth,employeeMobile,employeeEmail,employeeProvince,employeeCity,employeeAddr,employeePostcode,employeeDepart,employeeSalary,employeeLevel,employeePoints,status,notes) values(:employeeNation,:employeeId,:passWD,:employeeActor,:districtId,:employeeName,:employeeNickName,:employeeIdCard,:employeeSex,:employeeBirth,:employeeMobile,:employeeEmail,:employeeProvince,:employeeCity,:employeeAddr,:employeePostcode,:employeeDepart,:employeeSalary,:employeeLevel,:employeePoints,:status,:notes)";
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
	        if (null == map.get("openId")){
	        	map.put("openId"," ");
	        }
		}
	}
	@Override
	public boolean editInfo(Map map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeInfo(String account) {
		String sql ="delete from ddxq_employees where employeeId=?;";		
		return baseDAO.executeCommand(sql, new Object[]{account});
	}*/

}
