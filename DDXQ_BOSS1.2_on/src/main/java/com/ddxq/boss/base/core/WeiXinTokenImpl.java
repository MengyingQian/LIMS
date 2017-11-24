/**
 * FileName:    WeiXinTokenImpl.java
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) �������������Ļ���չ���޹�˾ 2016
 * Company      �������������Ļ���չ���޹�˾
 * @author:   ����
 * @version   V1.0 
 * Createdate:        2016��4��19�� ����10:13:35
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016��4��19��  zhu xu zhen          0.1             0.1
 * Why & What is modified: <�޸�ԭ������>
 */

package com.ddxq.boss.base.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddxq.boss.base.constant.WeiXinConstant;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.cache.CacheUtil;
import com.ddxq.common.constant.OperationConstant;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.https.HttpsManager;
import com.jzsn.utils.https.HttpsSender;
import com.jzsn.utils.weixin.WeiXinBase;
import com.jzsn.utils.weixin.token.WeiXinTokenManager;

import net.sf.json.JSONObject;

/**
 * ClassName:WeiXinTokenImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月19日10:13:35 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Component("weiXinTokenManager")
public class WeiXinTokenImpl implements WeiXinTokenManager{
    @Autowired
    CacheUtil cacheUtil;
    
    @Autowired
    SystemLog systemLog;
    
	@Override
	public JSONObject getToken() {
		/** 
		 * @Title:        getToken 
		 * @Description:  TODO(获取公众号当前与微信通信的access_token) 
		 * @param:          
		 * @return:          
		 * @throws 
		 * @author        zxz
		 * @Date          2016年4月19日 10:13:56 
		 */
		JSONObject token_json = new JSONObject();
		String access_token = cacheUtil.get("access_token");
		if (null != access_token){
			token_json.put("err", WeiXinConstant.OK);
			token_json.put("access_token", access_token);
		}else{
			token_json = updateAccessToken(3000,3000,"openid","");
		}
		systemLog.debugLog(WeiXinTokenImpl.class, "get access_token successfully");
		return token_json;
	}


	@Override
	public synchronized JSONObject regetToken(String old_access_token) {

		JSONObject token_json = new JSONObject();
		String access_token = cacheUtil.get("access_token");
		if (null != access_token && !"".equals(access_token)){
			if(access_token.equals(old_access_token)){
				token_json = updateAccessToken(3000,3000,"openid",old_access_token);
			}else
			{
				token_json.put("err", WeiXinConstant.OK);
				token_json.put("access_token", access_token);
			}
		}else{
			token_json = updateAccessToken(3000,3000,"openid","");
		}
		return token_json;
	}



//	@Override
	private synchronized JSONObject updateAccessToken(int read_time_out, int connect_time_out, String openId,String old_access_token) {
		/** 
		 * @Title:        updateAccessToken 
		 * @Description:  TODO(更新并存储access_token) 
		 * @param:          
		 * @return:          
		 * @throws 
		 * @author        zxz
		 * @Date          2016年4月19日 10:35:24 
		 * 
		 */
	    int count = 0;
		JSONObject jsonback = new JSONObject();
		String access_token = cacheUtil.get("access_token");
//		WeiXinUserPojo weixinUser = cacheUtil.get("user", openId, WeiXinUserPojo.class);
		if (null != access_token && !"".equals(access_token)){
			if(access_token.equals(old_access_token)){
				access_token = forceUpdateAccessToken(read_time_out,connect_time_out,openId).getString("access_token");
				jsonback.put("access_token",access_token);
				jsonback.put("err", WeiXinConstant.ERR_TOKEN_NOT_EXIST);
			}else
			{
				jsonback.put("err", WeiXinConstant.ERR_TOKEN_ALREADY_EXIST);
				jsonback.put("access_token", access_token);
			}
		}else{
			access_token = forceUpdateAccessToken(read_time_out,connect_time_out,openId).getString("access_token");
			jsonback.put("access_token",access_token);
			jsonback.put("err", WeiXinConstant.ERR_TOKEN_INVALID);
		}
		//将获取到的access_token存储到redis
		return jsonback;
	}

	
	private synchronized JSONObject forceUpdateAccessToken(int read_time_out, int connect_time_out, String openId){
		
//		WeiXinUserPojo weixinUser = cacheUtil.get("user", openId, WeiXinUserPojo.class);
		
		HttpsSender httpsSender = HttpsManager.getHttpsSender();
		//获取HttpSender用于发送请求
		String url = access_token_url.replace("APPID", WeiXinBase.getApp_ID()).replace("APPSECRET", WeiXinBase.getApp_Secret());
		//替换access_token_url中的APPID和APPSERCRET
		JSONObject jsonback =  httpsSender.get_repsonse_JSON(url,read_time_out,connect_time_out);
		//返回json对象
		if(jsonback==null)
		{
		    int count = 0;
			while(count<4)
			{
				try
				{
					Thread.sleep(5000);
					jsonback =  httpsSender.get_repsonse_JSON(url,read_time_out,read_time_out);
					if(jsonback!=null)
					{
						break;
					}
					count++;
				} catch (InterruptedException e) {
//					systemLog.errorLog(WeiXinTokenImpl.class,weixinUser.getUserName(), weixinUser.getNickName(), "weixin", OperationConstant.OPERATION_OUTUSE, "updateToken按时大大大神的InterruptedException!",e);
				}
			}
		}
		cacheUtil.put("access_token",jsonback.getString("access_token"),jsonback.getInt("expires_in"));
		return jsonback;
	}
	
	@Deprecated
	private JSONObject updateToken(Long read_time_out, int connect_time_out, int openId) {
		/** 
		 * @Title:        updateToken 
		 * @Description:  TODO(之前的access_token失效，重新获取并更新) 
		 * @param:          
		 * @return:          
		 * @throws 
		 * @author        zxz
		 * @Date          2016年4月19日  10:34:12 
		 */
		return null;
	}
}
