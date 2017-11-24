package com.ddxq.boss.base.qrcode.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;

@Repository("qrCodeDao")
public class QRcodeDaoImpl implements QRcodeDao{
	@Autowired
	BaseDAO baseDAO;
	@Override
	public String getTicket(int sceneId) {
		
		return null;
	}

	@Override
	public String getUrl(String ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertTicketAndUrl(int sceneId, String ticket, String url) {
		String sql="update ddxq_districts set ticket = ?,qrCodeUrl =? where sceneId= ? ";		
		boolean success=baseDAO.executeCommand(sql,new Object[]{ticket,url,sceneId});		
		return success;
	}

}
