package com.ddxq.boss.base.service.receivedMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.boss.base.service.eventMsg.EventMsgDispatcherService;
import com.ddxq.boss.base.service.userMsg.UserMsgDispatcherServiceImpl;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;

import net.sf.json.JSONObject;

/**   
* @ClassName:     WeiXinRecievedMsgDispatcher.java   
* @Description: TODO(用一句话描述该文件做什么) 
* All rights Reserved, Designed By jzsn  
* Copyright:    Copyright(C) 2015-2015年12月28日 
* Company       闻康云医（北京）科技有限公司 jzsn LTD.  
* @author:    名字 
* @version    V2.0    
* Createdate:         2015年12月28日 下午12:08:40  
*/
@Service("recievedMsgDispatcherService")
public class RecievedMsgDispatcherServiceImpl implements RecievedMsgDispatcherService{
	@Autowired
	EventMsgDispatcherService eventMsgDispatcherService;
	
	
	private UserMsgDispatcherServiceImpl userMsgDispatcher = new UserMsgDispatcherServiceImpl();

	int intercept_or_not = 1; //开始认为要拦截
	String content = "success"; //开始直接回复成功，拦截消息使用
	public String process(ReceiveXmlEntity recXMLEntity){
		/** 解析xml数据 */
		String msgType = recXMLEntity.getMsgType();
		String respContent = "";
		   
		if (msgType.equals("event")){
			JSONObject jsonObject = eventMsgDispatcherService.processCommonEventMsg(recXMLEntity);
			intercept_or_not = jsonObject.getInt("intercept");
		    content = jsonObject.getString("content");
		}else{
			//这是消息推送
			//消息拦截处理
		    JSONObject jsonObject = eventMsgDispatcherService.processCommonEventMsg(recXMLEntity);
		    intercept_or_not = jsonObject.getInt("intercept");
		    content = jsonObject.getString("content");
		}


		if (0 == intercept_or_not){
			//表示不拦截消息
			respContent = content;
		}else{
			//表示拦截消息
			respContent = "success";
		}
		return respContent;
	}
}
