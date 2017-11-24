package com.ddxq.system.showqrcode.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.service.qrcode.QRcodeService;

@Repository("showQRCodeDao")
public class ShowQRCodeDaoImpl implements ShowQRCodeDao{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	QRcodeService qRcodeService;
	@Override
	public List<Map<String, Object>> showAll() {
		String sql ="select * from ddxq_qrcode order by districtId;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql);
		formDate(list);
		return list;
	}

	@Override
	public List<Map<String, Object>> showByDistrictId(int start, int end) {
		String sql ="select * from ddxq_qrcode where (districtId >= ?) and (districtId <= ?) order by districtId;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{start,end});
		formDate(list);
		return list;
	}

	@Override
	public List<Map<String, Object>> showByDistrictIdAndDate(int start, int end, String dateFrom, String dateTo) {
		String sql ="select * from ddxq_qrcode where (districtId >= ?) and (districtId <= ?) and (created >= ?) and (created <= ?) order by districtId;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{start,end,dateFrom,dateTo});
		formDate(list);
		return list;
	}

	@Override
	public boolean insertNewCode(List<Map<String, Object>> list) {
		//INSERT INTO tab_comp VALUES(item1, price1, qty1),
		//(item2, price2, qty2),
		//(item3, price3, qty3);
		StringBuilder sql=new StringBuilder();
		
		sql.append("insert into ddxq_qrcode (districtId,ticket,qrCodeURL) values ");
		for (int i = 0; i < list.size(); i++) {
			sql.append("(\""+list.get(i).get("districtId")+"\",\"");
			sql.append(list.get(i).get("ticket")+"\",\"");
			sql.append(list.get(i).get("qrCodeURL")+"\")");
			if(i!=list.size()-1){
				sql.append(",");
			}
		}
		sql.append(";");
		try {			
			baseDAO.executeCommand(sql.toString(), null);
		} catch (Exception e) {			
		}		
		return true;
	}

	@Override
	public boolean checkExist(int districtId) {
		String sql ="select count(*) as num from ddxq_qrcode where districtId=? ;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql,new Object[]{districtId});
		long num=(long)list.get(0).get("num");		
		return num!=0;
	}

	@Override
	public boolean deleteByDistrictId(int start, int end) {
		String sql ="delete from ddxq_qrcode where (districtId >=?) and (districtId <=?) ;";
		baseDAO.executeCommand(sql, new Object[]{start,end});
		return true;
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
	public List<Map<String, Object>> showByPage(int pageNumber, int pageSize) {
		int start=(pageNumber-1)*(pageSize);
		int end=pageSize;
		String sql ="SELECT * FROM ddxq_qrcode LIMIT ?,?;";
		List<Map<String, Object>> list=baseDAO.queryForList(sql, new Object[]{start,end});
		formDate(list);
		return list;
	}

	@Override
	public boolean updateNote(int start, int end, String note) {
		String sql ="update ddxq_qrcode set notes =? where districtId>= ? and districtId <=? ; ";
		baseDAO.executeCommand(sql, new Object[]{note,start,end});
		return true;
	}

	@Override
	public boolean deleteByDistrictIdAndDate(int start, int end, String dateFrom, String dateTo) {
		String sql ="delete from ddxq_qrcode where (districtId >=?) and (districtId <=?)  and (created >= ?) and (created <= ?) ;";
		baseDAO.executeCommand(sql, new Object[]{start,end,dateFrom,dateTo});
		return true;
	}

	
}
