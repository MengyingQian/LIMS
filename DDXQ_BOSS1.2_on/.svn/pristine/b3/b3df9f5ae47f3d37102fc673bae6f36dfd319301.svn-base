package com.ddxq.boss.base.openid.redis.dao;

import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 上午10:54:38 
* 类说明 
*/
public interface OpenidIDRedisDao {
	/**
	 * 
	 * @Title: putUser
	 * @Description: TODO(将user_id存入redis)
	 * @ @param weixinUser   
	 * @return: void   
	 * @throws
	 */
	public void putOpenidID(String openid,String user_id);
	/**
	 * 
	 * @Title: getUser
	 * @Description: TODO(通过openid值得到user_id中的数据)
	 * @ @param key
	 * @ @return   
	 * @return: WeiXinUserPojo   
	 * @throws
	 */
	public String getOpenidID(String openid);
	/**
	 * 
	 * @Title: keyUserExist
	 * @Description: TODO(通过key值判断redis中是否存在)
	 * @ @param key
	 * @ @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean keyOpenidIDExist(String openid);

}
