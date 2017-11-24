/**
 * @ClassName:     EventMsgManagerServiceImpl.java
 * @Description:   TODO(这是微信事件消息处理服务的接口类，定义了各种事件的处理接口) 
 * All Rights Reserved, Designed By JZSN
 * Copyright (c) 北京里里外外文化发展有限公司  2016-
 * @author         zxz
 * @version        V1.0  
 * @Date           2016年4月8日 上午8:12:10 
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月8日      zxz          1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
package com.ddxq.boss.base.service.eventMsg;

import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;
/**
 * 
 * @ClassName:    EventMsgManagerServiceImpl
 * @Description:TODO(这个接口类提供微信事件的处理接口)
 * @author:   zxz
 * @date:       2016年4月8日 上午8:14:42
 *
 */
public interface EventMsgManagerService {
	/**
	 * 
	 * @Title: processSubscribeEventMsg
	 * @Description: TODO(处理用户关注事件)
	 * @param recXMLEntity  
	 * @return: String   
	 * @throws
	 */
	public String processSubscribeEventMsg(ReceiveXmlEntity recXMLEntity);
	
	/**
	 * 
	 * @Title: processUnsubscribeEventMsg
	 * @Description: TODO(处理用户取消关注事件)
	 * @param recXMLEntity  
	 * @return: String   
	 * @throws
	 */
	public String processUnsubscribeEventMsg(ReceiveXmlEntity recXMLEntity);
	
	/**
	 * 
	 * @Title: processClickEventMsg
	 * @Description: TODO(处理用户点击事件消息)
	 * @param recXMLEntity
	 * @return: String   
	 * @throws
	 */
	public String processClickEventMsg(ReceiveXmlEntity recXMLEntity);
	
	/**
	 * 
	 * @Title: processSCANEventMsg
	 * @Description: TODO(处理用户扫描二维码事件)
	 * @param recXMLEntity
	 * @return   
	 * @return: String   
	 * @throws
	 */
	public String processSCANEventMsg(ReceiveXmlEntity recXMLEntity);
	
	/**
	 * 
	 * @Title: processVIEWEventMsg
	 * @Description: TODO(处理用户的View事件)
	 * @param recXMLEntity
	 * @return   
	 * @return: String   
	 * @throws
	 */
	public String processVIEWEventMsg(ReceiveXmlEntity recXMLEntity);
	
	/**
	 * 
	 * @Title: processLOCATIONEventMsg
	 * @Description: TODO(处理用户的定位事件)
	 * @param recXMLEntity
	 * @return   
	 * @return: String   
	 * @throws
	 */
	public String processLOCATIONEventMsg(ReceiveXmlEntity recXMLEntity);
	
	/**
	 * 
	 * @Title: processPassiveMsg
	 * @Description: TODO(处理系统的被动接收的消息)
	 * @param recXMLEntity
	 * @return   
	 * @return: String   
	 * @throws
	 */
	public String processPassiveMsg(ReceiveXmlEntity recXMLEntity);
	
	/**
	 * 
	 * @Title: addHeadAndTail
	 * @Description: TODO(给消息添加头和尾)
	 * @param head
	 * @param originalText
	 * @param tail
	 * @return   
	 * @return: String   
	 * @throws
	 */
	public String addHeadAndTail(String head, String originalText, String tail);
}
