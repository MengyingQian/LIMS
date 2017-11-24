/**
 * FileName:    QRcodeServiceImpl.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   lqw
 * @version   V1.0 
 * Createdate:        2016年4月21日 上午11:23:38
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.service.qrcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.fastDFS.BaseFastdfsDaoImpl;
import com.ddxq.boss.base.qrcode.dao.QRcodeDao;
import com.ddxq.boss.base.user.dao.UsersRedisDao;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.cache.CacheUtil;
import com.ddxq.common.config.WxConfig;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.account.QRCodeManager;
import com.jzsn.utils.weixin.token.WeiXinTokenManager;

import net.sf.json.JSONObject;

/**
 * ClassName:QRcodeServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月21日 上午11:23:38 <br/>
 * @author   lqw
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */

@Service("qRcodeService")
public class QRcodeServiceImpl implements QRcodeService{
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	QRcodeDao qrCodeDao;
	@Autowired
	BaseFastdfsDaoImpl fastdfsDao;
	@Autowired
	WeiXinTokenManager weiXinTokenManager;
	@Autowired
	QRCodeManager qrCodeM;
	@Autowired 
	SystemLog systemLog;
	@Autowired
	WxConfig wxConfig;
	Logger log = LoggerFactory.getLogger(QRcodeServiceImpl.class);
	
	@Override
	public String genQRcodeUrl(String openid,String user_id){
//		int expire_seconds = Integer.parseInt(wxConfig.getQr_expireTime());
//		List<String> list = new ArrayList<String>();
//		String openid123=openid+"123";
//		if(cacheUtil.keyIsExist(openid123)){
//			String qrCodeUrl=cacheUtil.get(openid123);
//			if(qrCodeUrl!=null && !qrCodeUrl.trim().equals("")){
//				return qrCodeUrl;
//			}else{
//				//查询SQL是否保存了Url，如果有则删除FDFS中的过期QRcode
//				Map map=queryQRcodeUrl(openid);
//				String qrUrl=(String) map.get("qr_code_url");
//				if(qrUrl!=null && !qrUrl.trim().equals("")){
//					String[] fileIds=fastdfsDao.url2Strs(qrUrl);
//					int i=fastdfsDao.fastdfsDelete(fileIds);
//					if(i!=0){
//						systemLog.debugLog(QRcodeServiceImpl.class, "fastDFS删除失败"+i);
//					}
//					String url = updateUrl(openid,expire_seconds);
//					list.add(url);
//					list.add(openid);
//					boolean updateQRcodeUrlR = updateQRcodeUrl(list.toArray());
//					systemLog.debugLog(QRcodeServiceImpl.class, "Mysql插入url结果"+updateQRcodeUrlR);
//					cacheUtil.put(openid123, url, expire_seconds);
//					return url;
//				}else{
//					String url = updateUrl(openid,expire_seconds);
//					list.add(url);
//					list.add(openid);
//					boolean updateQRcodeUrlR = updateQRcodeUrl(list.toArray());
//					systemLog.debugLog(QRcodeServiceImpl.class, "Mysql插入url结果"+updateQRcodeUrlR);
//					cacheUtil.put(openid123, url, expire_seconds);
//					if(usersRedisDao.keyUserExist(user_id)){
//						WeiXinUserPojo user = usersRedisDao.getUser(user_id);
//						user.setQr_code_url(url);
//						usersRedisDao.putUser(user_id, user);
//					}
//					return url;
//				}
//			}	
//		}
//		//查询SQL是否保存了Url，如果有则删除FDFS中的过期QRcode
//		Map map=queryQRcodeUrl(openid);
//		String qrUrl=(String) map.get("qr_code_url");
//		if(qrUrl!=null && qrUrl!="" && !qrUrl.contains(" ")){
//			String[] fileIds=fastdfsDao.url2Strs(qrUrl);
//			int i=fastdfsDao.fastdfsDelete(fileIds);
//			if(i != 0){
//				systemLog.debugLog(QRcodeServiceImpl.class, "fastDFS删除失败"+i);
//			}
//			String url = updateUrl(openid,expire_seconds);
//			list.add(url);
//			list.add(openid);
//			boolean updateQRcodeUrlR = updateQRcodeUrl(list.toArray());
//			systemLog.debugLog(QRcodeServiceImpl.class, "Mysql插入url结果"+updateQRcodeUrlR);
//			cacheUtil.put(openid123, url, expire_seconds);
//			if(usersRedisDao.keyUserExist(user_id)){
//				WeiXinUserPojo user = usersRedisDao.getUser(user_id);
//				user.setQr_code_url(url);
//				usersRedisDao.putUser(user_id, user);
//			}
//			return url;
//		}else{
//			String url = updateUrl(openid,expire_seconds);
//			list.add(url);
//			list.add(openid);
//			boolean updateQRcodeUrlR = updateQRcodeUrl(list.toArray());
//			systemLog.debugLog(QRcodeServiceImpl.class, "Mysql插入url结果"+updateQRcodeUrlR);
//			cacheUtil.put(openid123, url, expire_seconds);
//			if(usersRedisDao.keyUserExist(user_id)){
//				WeiXinUserPojo user = usersRedisDao.getUser(user_id);
//				user.setQr_code_url(url);
//				usersRedisDao.putUser(user_id, user);
//			}
//			return url;
//		}
		return null;
	}
	
	/**
	 * 
	 * @Title:genUrl
	 * @Description:updateUrl by openid and expire_seconds
	 * @param:@param openid
	 * @param:@param expire_seconds
	 * @param:@return 
	 * @return:String
	 * @throws
	 */
	@Override
	public Map<String, Object> genUrl(int sceneId, int expire_seconds){
		Map<String, Object> map=new HashMap<>();
		JSONObject token = weiXinTokenManager.getToken();
		String access_token = token.getString("access_token");
		String jsonMsg="{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+sceneId+"}}}";
		JSONObject jsonObject = JSONObject.fromObject(jsonMsg);
		JSONObject ticketJSON = qrCodeM.createPermanentTicket(access_token, 5000, 5000, jsonObject);
		systemLog.debugLog(QRcodeServiceImpl.class, ticketJSON.toString());		
		String ticket=ticketJSON.getString("ticket");
		byte[] stream = qrCodeM.createQRCodeStream(access_token, 5000, 5000, ticket);
		log.debug("Stream       "+(stream.length)+"");
		String qrCodeUrl = fastdfsDao.fastdfsUploadUrl(stream,"jpg", null);
		log.debug("qrcode      "+qrCodeUrl);
		//qrCodeDao.insertTicketAndUrl(sceneId, ticket, qrCodeUrl);
		map.put("districtId", sceneId);
		map.put("ticket", ticket);
		map.put("qrCodeURL", qrCodeUrl);
		return map;
	}

	/**
	 * 
	 * @Title:getIdByOpenid
	 * @Description:getIdByOpenid,the id is set to scene_id
	 * @param:@param args
	 * @param:@return
	 * @return:Map
	 * @throws
	 */
	private Map getIdByOpenid(String args){
		String sql = " select user_id from users where openid = :openid ";
		Map map = new HashMap();
		map.put("openid", args);
		return baseDAO.queryForMap(sql, map);
	}
	/**
	 * 
	 * @Title:queryQRcodeUrl
	 * @Description:查询qrCodeURL
	 * @param:@param args
	 * @param:@return
	 * @return:Map从Map中获取qrCodeURL即可
	 * @throws
	 */
	public Map queryQRcodeUrl(String args){
		String sql = " select qr_code_url from users where openid = :openid ";
		Map map = new HashMap();
		map.put("openid", args);
		return baseDAO.queryForMap(sql, map);
	}
	/**
	 * 
	 * @Title:updateQRcodeUrl
	 * @Description:更新qrCodeURL
	 * @param:@param args包含qrCodeURL和openid
	 * @param:@return
	 * @return:boolean
	 * @throws
	 */
	public boolean updateQRcodeUrl(Object[] args){
		String sql = " update users set qr_code_url = ? where openid = ? ";
		return baseDAO.executeCommand(sql, args);
	}
	/**
	 * 
	 * @Title:insertQRcodeUrl
	 * @Description:插入一条openid和qrCodeURL的记录，一般不用
	 * @param:@param args
	 * @param:@return
	 * @return:boolean
	 * @throws
	 */
	public boolean insertQRcodeUrl(Object[] args){
		String sql = " insert into users ( openid,qr_code_url ) values(?,?) ";
		return baseDAO.executeCommand(sql, args);
	}
}
