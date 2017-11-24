package com.ddxq.boss.base.aspect.service;

import java.util.Map;

import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;

import net.sf.json.JSONObject;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 上午10:14:17 
* 类说明 
*/
public interface UsersCacheService {
	
	public long getUserID();
	public WeiXinUserPojo getUserfromOpneid(String openid);
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @Title: MapToWeiXinUser
	 * @Description: TODO(mysql中的map数据转换为WeixinUserPojo)
	 * @ @param usermysql
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public  WeiXinUserPojo MapToWeiXinUser(Map usermysql);
	/**
	 * 
	 * @Title: JsonToWeiXinUser
	 * @Description: TODO(UnionID机制说明，用于sql数据误删除)
	 * @ @param retJson3
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public  WeiXinUserPojo JsonToWeiXinUser(JSONObject retJson3);
	/**
	 * 
	 * @Title: Json1ToWeiXinUser
	 * @Description: TODO(微信网页授权，用于code信息的拉取)
	 * @ @param retJson1
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public  WeiXinUserPojo Json1ToWeiXinUser(JSONObject retJson1);
	/**
	 * 
	 * @Title: OpenidGetUserID
	 * @Description: TODO(根据Openid得到user_id)
	 * @ @param Openid
	 * @ @return   
	 * @return: String   不存在就返会null
	 * @throws
	 */
	public  String OpenidGetUserID(String openid);
	/**
	 * 
	 * @Title: IdGetWeiXinUser
	 * @Description: TODO(根据user_id得到user)
	 * @ @param user_id
	 * @ @param openid
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public WeiXinUserPojo IdGetWeiXinUser(String user_id,String openid);
	/**
	 * 
	 * @Title: CodeGetWeiXinUser
	 * @Description: TODO(没有openid，利用code拉取信息,点击菜单)
	 * @ @param code
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public WeiXinUserPojo CodeGetWeiXinUser(String code);
	/**
	 * 
	 * @Title: OpenidGetWeiXinUser
	 * @Description: TODO(user_id为Null时，利用openid得到用户信息)
	 * @ @param openid
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public WeiXinUserPojo OpenidGetWeiXinUser(String openid);
}

