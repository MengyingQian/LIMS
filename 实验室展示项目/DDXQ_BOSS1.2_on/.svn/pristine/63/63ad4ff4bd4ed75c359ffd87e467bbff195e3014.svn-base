package com.ddxq.employees.info.dao;

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

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年12月12日 上午10:47:57 
* 类说明 
*/
@Repository("infoDao")
public class InfoDaoImpl implements InfoDao {
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	MD5Util mD5Util;
	@Override
	public boolean changepwd(String id, String pre, String pwd) {
		String prepwd="";
		String pwdd="";
		try {
			prepwd=mD5Util.StringToMD5_32(pre);
			pwdd=mD5Util.StringToMD5_32(pwd);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		String sql="select passWD from ddxq_employees where id=? ";
		Map map=baseDAO.queryForMap(sql, new Object[]{id});
		if(prepwd.equals(map.get("passWD").toString())){
			String sq="update ddxq_employees set passWD =? where id=? ";
			return baseDAO.executeCommand(sq, new Object[]{pwdd,id});
		}else{
			return false;
		}
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
	public JSONObject getUserinfo(String id) {
		String sql="select created,updated,employeeId,passWD,employeeActor,districtId,employeeName,employeeNickName,employeeIdCard,employeeSex,employeeBirth,employeeMobile,employeeEmail,employeeProvince,employeeCity,employeeAddr,employeePostcode,employeeDepart,employeeSalary,employeeLevel,employeePoints,status,notes from ddxq_employees where id=?";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{id});
		formDate(list);
		JSONObject retJobj = new JSONObject();
		retJobj.put("total",list.size());
		retJobj.put("rows", list);
		return retJobj;
	}

	public boolean editInfo(Map map) {
		String sql = "update ddxq_employees set employeeActor=:employeeActor,districtId=:districtId,employeeName=:employeeName,employeeNickName=:employeeNickName,employeeIdCard=:employeeIdCard,employeeSex=:employeeSex,employeeBirth=:employeeBirth,employeeMobile=:employeeMobile,employeeEmail=:employeeEmail,employeeProvince=:employeeProvince,employeeCity=:employeeCity,employeeAddr=:employeeAddr,employeePostcode=:employeePostcode,employeeDepart=:employeeDepart,employeeSalary=:employeeSalary,employeeLevel=:employeeLevel,employeePoints=:employeePoints,status=:status,notes=:notes where employeeId=:employeeId;";
		return baseDAO.executeNamedCommand(sql, map);
	}
}
 