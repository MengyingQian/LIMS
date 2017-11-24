/**
 * FileName:    QRcodeService.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月21日 上午11:23:27
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月21日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.service.qrcode;

import java.util.Map;

/**
 * ClassName:QRcodeService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月21日 上午11:23:27 <br/>
 * @author   lqw
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
public interface QRcodeService {
	/**
	 * 
	 * @Title:genQRcodeUrl
	 * @Description:TODO
	 * @param:@param openid
	 * @param:@param user_id
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	String genQRcodeUrl(String openid,String user_id);
	/**
	 * 
	 * @Title:queryQRcodeUrl
	 * @Description:查询qrCodeURL
	 * @param:@param args
	 * @param:@return
	 * @return:Map
	 * @throws
	 */
	Map queryQRcodeUrl(String args);
	/**
	 * 
	 * @Title:updateQRcodeUrl
	 * @Description:更新qrCodeURL
	 * @param:@param args
	 * @param:@return
	 * @return:boolean
	 * @throws
	 */
	boolean updateQRcodeUrl(Object[] args);
	/**
	 * 
	 * @Title:insertQRcodeUrl
	 * @Description:插入openid和qrCodeURL
	 * @param:@param args
	 * @param:@return
	 * @return:boolean
	 * @throws
	 */
	boolean insertQRcodeUrl(Object[] args);
	Map<String, Object> genUrl(int sceneId, int expire_seconds);
}
