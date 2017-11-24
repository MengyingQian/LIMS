package com.ddxq.employee.guanliyuan.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.util.md5.MD5Util;

import net.sf.json.JSONObject;
@Repository("adminGuanLiYuanDao")
public class GuanLiYuanDaoImpl implements GuanLiYuanDao{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	MD5Util mD5Util;
	@Override
	public JSONObject getUserViaActor(String districtId, String actorid) {
		String sql="select id,openId,created,updated,employeeId,passWD,employeeActor,districtId,employeeName,employeeNation,employeeNickName,employeeIdCard,employeeSex,employeeBirth,employeeMobile,employeeEmail,employeeProvince,employeeCity,employeeAddr,employeePostcode,employeeDepart,employeeSalary,employeeLevel,employeePoints,status,notes from ddxq_employees where districtId=? and employeeActor=? and status='1';";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{districtId,actorid});
		formDate(list);
		JSONObject retJobj = new JSONObject();
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}
	@Override
	public Map<String,Object> insertInfo(Map map) {
		Map<String,Object> result = new HashMap<String,Object>();
		String password=map.get("passWD").toString();
		String passwd="";
		try {
			passwd=mD5Util.StringToMD5_32(password);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		map.put("passWD", passwd);
		if(check(map.get("employeeId").toString())){
			result.put("success", false);
			result.put("mess", "网络异常");
			return result;
		}
		String  employeeActor = (String)map.get("employeeActor");
		if( null == employeeActor){
			result.put("success", false);
			result.put("mess", "网络异常");	
			return result;
		}
		String sql1 = "select count(*) from ddxq_employees where employeeActor=:employeeActor and districtId=:districtId ";
		Map result1 = baseDAO.queryForMap(sql1, map);
		if ((long) result1.get("count(*)") > 0) {
			if(employeeActor.equals("110")){
				result.put("success", false);
				result.put("mess", "每个小区仅允许一个小区片警");
				return result;
			}
			if(employeeActor.equals("200")){
				result.put("success", false);
				result.put("mess", "每个小区仅允许一个小区物业");
				return result;
			}
			if(employeeActor.equals("300")){
				result.put("success", false);
				result.put("mess", "每个小区仅允许一个小区居委会");
				return result;
			}
			if(employeeActor.equals("500")){
				result.put("success", false);
				result.put("mess", "每个小区仅允许一个小区业委会");
				return result;
			}

			if(employeeActor.equals("700")){
				if (((long) result1.get("count(*)") > 9)){
					result.put("success", false);
					result.put("mess", "每个小区最多10名骑手");
					return result;
				}
			}
			if(employeeActor.equals("800")){
				if (((long) result1.get("count(*)") > 99)){
					result.put("success", false);
					result.put("mess", "每个小区最多99名商家");
					return result;
				}
			}
			if(employeeActor.equals("170")){
				if (((long) result1.get("count(*)") > 10)){
					result.put("success", false);
					result.put("mess", "每个小区最多10名快递代收员");
					return result;
				}
			}
		}
		String sql="insert into ddxq_employees (employeeNation,employeeId,passWD,employeeActor,districtId,employeeName,employeeNickName,employeeIdCard,employeeSex,employeeBirth,employeeMobile,employeeEmail,employeeProvince,employeeCity,employeeAddr,employeePostcode,employeeDepart,employeeSalary,employeeLevel,employeePoints,status,notes) values(:employeeNation,:employeeId,:passWD,:employeeActor,:districtId,:employeeName,:employeeNickName,:employeeIdCard,:employeeSex,:employeeBirth,:employeeMobile,:employeeEmail,:employeeProvince,:employeeCity,:employeeAddr,:employeePostcode,:employeeDepart,:employeeSalary,:employeeLevel,:employeePoints,:status,:notes)";
		result.put("success",baseDAO.executeNamedCommand(sql, map));
		result.put("mess", "新增成功");
		return result;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeInfo(String account) {
		String sql ="update ddxq_employees set status='0'  where employeeId=?;";		
		return baseDAO.executeCommand(sql, new Object[]{account});
	}

}
