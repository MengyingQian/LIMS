package com.ddxq.boss.base.user.dao;

import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 上午9:08:52 
* 类说明 
*/
public interface UsersRedisDao {
	
	/**
	 * 
	 * @Title: putUser
	 * @Description: TODO(将微信User数据插入到redis中)
	 * @ @param weixinUser   
	 * @return: void   
	 * @throws
	 */
	public void putUser(String key,WeiXinUserPojo weixinUser);
	/**
	 * 
	 * @Title: getUser
	 * @Description: TODO(通过key值得到rerdis中的数据)
	 * @ @param key
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public WeiXinUserPojo getUser(String key);
	/**
	 * 
	 * @Title: keyUserExist
	 * @Description: TODO(通过key值判断redis中是否存在)
	 * @ @param key
	 * @ @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean keyUserExist(String key);

	/**
	 * 根据openid获取用户信息
	 * @param openid
	 * @return:含有用户信息的map
	 */
	public WeiXinUserPojo getUserInfo(String openid);
}
