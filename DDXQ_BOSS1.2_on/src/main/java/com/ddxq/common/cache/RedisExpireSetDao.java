/**
 * FileName:    RedisExpireDao.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月13日 下午4:11:19
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月13日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.common.cache;

import java.util.List;

/**
 * ClassName:RedisExpireDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月13日 下午4:11:19 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
public interface RedisExpireSetDao {
	/**
	 * 
	 * @Title: put
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param key
	 * @param value   
	 * @return: void   
	 * @throws
	 */
	public void put(String key, String value);
	/**
	 * 
	 * @Title: keyIsExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param key
	 * @return   
	 * @return: boolean   
	 * @throws
	 */
	public boolean keyIsExist(String key);
	/**
	 * 
	 * @Title: remove
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param key   
	 * @return: void   
	 * @throws
	 */
	public void remove(String key);
	/**
	 * 
	 * @Title: removeBatch
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param keys   
	 * @return: void   
	 * @throws
	 */
	public void removeBatch(List<String> keys);
	/**
	 * 
	 * @Title: get
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param key
	 * @return: Long   
	 * @throws
	 */
	public Long getExpireTime(String key);
}
