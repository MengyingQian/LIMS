package com.ddxq.boss.base.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;

import net.sf.json.JSONObject;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 下午2:03:23 
* 类说明 
*/
public interface WeiXinUserService {
	/**
	 * 
	 * @Title: updateZhuCe
	 * @Description: TODO(保存并更新用户信息)
	 * @ @param request
	 * @ @param weixinUser
	 * @ @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean updateZhuCe(HttpServletRequest request,WeiXinUserPojo weixinUser);
	/**
	 * 
	 * @Title: savePhone
	 * @Description: TODO(保存用户的手机号码)
	 * @ @param openid
	 * @ @param phone
	 * @ @return   
	 * @return: Map<String,Object>   
	 * @throws
	 */
//	public Map<String,Object> getCode(String mobile,String change);
	public Map<String,Object> yanzheng(JSONObject obj);
	public Map<String,Object> loginyanzheng(JSONObject obj);
	public Map<String,Object> genrenzhongxin(String openid);
	

}
