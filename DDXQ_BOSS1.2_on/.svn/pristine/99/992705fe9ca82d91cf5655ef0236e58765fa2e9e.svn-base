package com.ddxq.boss.base.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;

import net.sf.json.JSONObject;

public interface SubscribeUsersDao {
	public final static int CommandExcuted = 1;
	public final static int IllegalParameters = 2;
	public final static int IdDoesNotExists = 3;
	/**
	 * 
	 * @Title: getUserId
	 * @Description: TODO(获取当前数据中的最大的userid)
	 * @ @return   
	 * @return: long   
	 * @throws
	 */
	public long getUserId();
	
	
	/**
	 * 
	 * @Title: jsonObject_To_ObjectArray
	 * @Description: TODO 工具方法，将json对象的值元素转换为Object数组和查询值"key1,key2,key3"以及插入值"values(?,?,?)"字符串，结果封装在JSON对象中，便于其他函数调用
	 * @param: @param jsonObject
	 * @param: @return   
	 * @return: Object[]   
	 * @throws
	 */
	public HashMap jsonObject_To_HashMap(JSONObject jsonObject);
	
	/**
	 * 
	 * @Title: WeiXinUserPojo_To_ObjectArray
	 * @Description: TODO 将微信用户对象转换成Object[]以便于其他数据库函数调用
	 * @param: @param weiXinUserPojo
	 * @param: @return   返回
	 * @return: Object[]   
	 * @throws
	 */
	public boolean saveUserInfo(JSONObject jsonObject);
	/**
	 * 
	 * @Title: deleteUserViaOpenId
	 * @Description: TODO 通过用户OpenId删除用户记录
	 * @param: @param OpenId
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean deleteUserViaOpenId(String OpenId);
	/**(
	 * 
	 * @Title: updateUserViaOpenId
	 * @Description: TODO 通过用户OpenId修改存储用户信息
	 * @param: @param OpenId
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean updateUserViaOpenId(String OpenId,JSONObject jsonObject);
	/**
	 * 
	 * @Title: queryUserViaOpenId
	 * @Description: TODO 通过用户OpenId从数据库获取用户信息
	 * @param: @param OpenId
	 * @param: @return jsonObject内包含需要更新的键值对
	 * @return: Map     返回Map类型
	 * @throws
	 */
	public Map queryUserViaOpenId(String OpenId);
	/**
	 * 
	 * @Title: isUserExist
	 * @Description: TODO 判断用户信息是否在库，在库则变更用户状态为正常
	 * @param: @param OpenId
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean isUserExist(String OpenId);
	/**
	 * 
	 * @Title: normalizeUserstate
	 * @Description: TODO 将用户状态置为正常
	 * @param: @param OpenId
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean normalizeUserstatus(String OpenId);
	/**
	 * 
	 * @Title: freezeUserStatus
	 * @Description: TODO 冻结用户状态
	 * @param: @param OpenId
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean freezeUserStatus(String OpenId);
	/**
	 * 
	 * @Title: getAllUsersOpenId
	 * @Description: TODO 获取所有在库的用户OpenId
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public List getAllUsersOpenId();
	
	/**
	 * 
	 * @Title: restoreUsersInfoToMysql
	 * @Description: TODO 批量存储用户信息
	 * @param: @param jsonUsersInfo 从微信批量获取的用户信息列表
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean saveUsersInfoToMysql(JSONObject jsonUsersInfo);
	/**
	 * 
	 * @Title: saveUserViaUserPojo
	 * @Description: TODO 通过传递过来的pojo对象存储
	 * @param: @param weiXinUser
	 * @param: @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean saveUserViaUserPojo(WeiXinUserPojo weiXinUser);
	/**
	 * 
	 * @Title: updateUserPojo
	 * @Description: TODO(根据传送过来的map，更新数据库,需要的数据为id以及需要更新的其他参数
	 * @ @param weiXinUser
	 * @ @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean updateUserPojo(long id,Map<String,Object> usermap);
	/**
	 * 
	 * @Title: useridGetUserPojo
	 * @Description: TODO(根据用户id获得userpojo)
	 * @ @param userid
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public WeiXinUserPojo useridGetUserPojo(long userid);
	/**
	 * 
	 * @Title: UseridGetMap
	 * @Description: TODO(通过user_id得到map)
	 * @ @param user_id
	 * @ @return   
	 * @return: Map<String,Object>   
	 * @throws
	 */
	public Map<String,Object> UseridGetMap (String user_id);
	/**
	 * 
	 * @Title: OpenidGetMap
	 * @Description: TODO(通过openid的到用户map)
	 * @ @param openid
	 * @ @return   
	 * @return: Map<String,Object>   
	 * @throws
	 */
	public Map<String,Object> OpenidGetMap (String openid);
}
