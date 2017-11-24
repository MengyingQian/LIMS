package com.ddxq.boss.base.service.uploadUImg;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.fastDFS.BaseFastdfsDaoImpl;
import com.ddxq.boss.base.user.dao.UsersRedisDao;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.token.WeiXinTokenManager;
/**
 * 
 * ClassName:UploadUImgImpl
 * Description:TODO
 * @author Liu
 * @date 2016年5月20日 上午10:39:28
 *
 */
@Service("uploadUImgService")
public class UploadUImgImpl {
	@Autowired 
	SystemLog systemLog;
	@Autowired
	BaseFastdfsDaoImpl fastdfsDao;
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	WeiXinTokenManager weiXinTokenManager;
	@Autowired
	UsersRedisDao usersRedisDao;
	
	/**
	 * 
	 * @Title:uploadImage
	 * @Description:更换头像
	 * @param:@param openid
	 * @param:@param file
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public String uploadImage(String openid, String mediaId,String user_id){
		List<String> list = new ArrayList<String>();
		//查询SQL是否保存了photoURL，如果不是默认的图片,则删除FDFS中的原先的photoURL
		Map map=queryPhotoUrl(openid);
		String photoURL=(String) map.get("head_img_url");
		if(photoURL==null || photoURL.trim().equals("") || photoURL.contains("1234.jpg") || photoURL.contains("wKgBL1d9vAaAGbniABCGKMpfDlI431")){
			//不删除原图片，直接生成并覆盖URL
			String url = genPhotoURL(mediaId);
			list.add(url);
			list.add(openid);
			boolean updatePhotoUrlR = updatePhotoUrl(list.toArray());
			systemLog.debugLog(UploadUImgImpl.class, "Mysql插入head_img_url结果:"+updatePhotoUrlR);
			if(usersRedisDao.keyUserExist(user_id)){
				WeiXinUserPojo user = usersRedisDao.getUser(user_id);
				user.setHead_img_url(url);
				usersRedisDao.putUser(user_id, user);
			}
			return url;
		}
		//删除原图片，生成并覆盖URL
		String[] fileIds=fastdfsDao.url2Strs(photoURL);
		int i=fastdfsDao.fastdfsDelete(fileIds);
		if(i!=0){
			systemLog.debugLog(UploadUImgImpl.class, "fastDFS删除head_img_url失败:"+i);
		}
		String url = genPhotoURL(mediaId);
		list.add(url);
		list.add(openid);
		boolean updatePhotoUrlR = updatePhotoUrl(list.toArray());
		systemLog.debugLog(UploadUImgImpl.class, "Mysql插入head_img_url结果:"+updatePhotoUrlR);
		if(usersRedisDao.keyUserExist(user_id)){
			WeiXinUserPojo user = usersRedisDao.getUser(user_id);
			user.setHead_img_url(url);
			usersRedisDao.putUser(user_id, user);
		}
		return url;
	}
	/**
	 * 
	 * @Title:genPhotoURL
	 * @Description:genarate photoURL
	 * @param:@param file
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public String genPhotoURL(String mediaId) {
		// 获得文件输入流
		byte[] buffer = null;
		try {
			String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"; 
			String accessToken = weiXinTokenManager.getToken().getString("access_token");//access_token
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId); 
			systemLog.debugLog(UploadUImgImpl.class, "图片下载url:"+requestUrl);
			  HttpURLConnection conn = null; 
			    URL url = new URL(requestUrl); 
			    conn = (HttpURLConnection) url.openConnection(); 
			    conn.setDoInput(true); 
			    conn.setRequestMethod("GET"); 
			    conn.setConnectTimeout(30000); 
			    conn.setReadTimeout(30000);
			//
			InputStream fis = conn.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1);// change 1000 to 1
			byte[] b = new byte[1];// change 1000 to 1
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		systemLog.debugLog(UploadUImgImpl.class, "buffer is:" + buffer);
		String photoUrl = fastdfsDao.fastdfsUploadUrl(buffer, "jpg", null);
		systemLog.debugLog(UploadUImgImpl.class, "head_img_url is:" + photoUrl);
		return photoUrl;
	}
	/**
	 * 
	 * @Title:queryPhotoUrl
	 * @Description:查询photoURL
	 * @param:@param args
	 * @param:@return
	 * @return:Map
	 * @throws
	 */
	public Map queryPhotoUrl(String args){
		String sql = " select * from users where openid = :openid ";
		Map map = new HashMap();
		map.put("openid", args);
		return baseDAO.queryForMap(sql, map);
	}
	/**
	 * 
	 * @Title:updatePhotoUrl
	 * @Description:更新photoURL
	 * @param:@param args
	 * @param:@return
	 * @return:boolean
	 * @throws
	 */
	public boolean updatePhotoUrl(Object[] args){
		String sql = " update users set head_img_url = ? where openid = ? ";
		return baseDAO.executeCommand(sql, args);
	}
}
