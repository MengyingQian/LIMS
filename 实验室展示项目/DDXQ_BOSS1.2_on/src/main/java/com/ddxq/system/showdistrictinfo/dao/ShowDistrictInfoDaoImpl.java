package com.ddxq.system.showdistrictinfo.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
@Repository("showDistrictInfoDao")
public class ShowDistrictInfoDaoImpl implements ShowDistrictInfoDao{
	@Autowired
	BaseDAO baseDAO;
	@Override
	public List<Map<String, Object>> showAll() {
		String sql ="select * from ddxq_districts order by districtId desc limit 0,500;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql);
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
	public boolean addDistrictInfo(Object[] obj) {
		String sql="insert into ddxq_districts(districtId,areacode,name,alias,description,address,postcode,phone,site,busroute,qualitiy,category,level,tags,notes,weathercode) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";	
		
		return baseDAO.executeCommand(sql, obj);
	}
	@Override
	public boolean checkExist(String districtId) {
		String sql ="select count(*) as num from ddxq_districts where districtId=?;";
		Map map=baseDAO.queryForMap(sql, new Object[]{districtId});
		int num=Integer.parseInt(map.get("num").toString());
		if(num==0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean checkExist(String districtId, String id) {
		String sql ="select count(*) as num from ddxq_districts where districtId=? and id!=?;";
		Map map=baseDAO.queryForMap(sql, new Object[]{districtId,id});
		int num=Integer.parseInt(map.get("num").toString());
		System.out.println(num+" "+districtId+"  "+id);
		if(num==0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public List<Map<String, Object>> showByDistrictId(int start, int end) {
		String sql ="select * from ddxq_districts where (districtId >= ?) and (districtId <= ?) order by districtId limit 0,500;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{start,end});
		formDate(list);
		return list;
	}
	@Override
	public boolean removeDistrictInfo(String districtId) {
		String sql ="delete from ddxq_districts where districtId=? ;";
		return baseDAO.executeCommand(sql, new Object[]{districtId});
	}
	@Override
	public boolean editDistrictInfo(Object[] obj) {
		String sql="update ddxq_districts set districtId=? ,areacode=?,name=?,alias=?,description=?,address=?,postcode=?,phone=?,site=?,busroute=?,qualitiy=?,category=?,level=?,tags=?,notes=?,weathercode=? where id=? ;";	
		return baseDAO.executeCommand(sql, obj);
	}
	@Override
	public List<Map<String, Object>> searchDistrict(String name) {
		String sql ="select * from ddxq_districts where alias  like\"%"+name+"%\" order by districtId limit 0,100;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql);
		formDate(list);
		return list;
	}
	@Override
	public boolean insertBorder(String districtId, String border) {
		String sql="update ddxq_districts set borderline=? where id=? ";
		
		return baseDAO.executeCommand(sql, new Object[]{border,districtId});
	}
	@Override
	public boolean insertLocation(String x, String y,String districtId) {
		String sql="update ddxq_districts set longitude=?,latitude=? where id=? ";	
		return baseDAO.executeCommand(sql, new Object[]{x,y,districtId});
	}
	@Override
	public boolean updateTicketAndUrl(String districtId) {
		String quer="select * from ddxq_qrcode where districtId=?;";
		Map map=baseDAO.queryForMap(quer, new Object[]{districtId});
		if(map.size()==0){
			return false;
		}
		String sql="update ddxq_districts set ticket=?,qrcodeURL=? where districtId=?;";
		return baseDAO.executeCommand(sql,new Object[]{map.get("ticket").toString(),map.get("qrCodeURL").toString(),districtId});

	}


}
