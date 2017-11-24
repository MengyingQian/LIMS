package com.ddxq.boss.base.service.eventMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;

import net.sf.json.JSONObject;

/**   
* @ClassName:     EventMsgProcessor.java   
* @Description: TODO(用一句话描述该文件做什么) 
* All rights Reserved, Designed By WKMHS  
* Copyright:    Copyright(C) 2016年3月1日- 
* Company       北京里里外外文化发展有限公司  
* @author:    名字 
* @version    V0.1   
* Createdate:   2016年4月8日 上午7:58
*/
@Service("eventMsgDispatcherService")
public class EventMsgDispatcherServiceImpl implements EventMsgDispatcherService{
	@Autowired
	SystemLog systemLog;
	
	@Autowired
	EventMsgManagerService eventMsgManagerService;
	
	public JSONObject processCommonEventMsg(ReceiveXmlEntity recXMLEntity){
		//这是事件推送
        String eventType = recXMLEntity.getEvent();
        JSONObject jsonObj = new JSONObject();
        String respContent = "";
        //订阅
        if (eventType.equals("subscribe")) {
        	respContent = eventMsgManagerService.processSubscribeEventMsg(recXMLEntity);
        	jsonObj.put("intercept", 0);
        	jsonObj.put("content", respContent);
        }else if (eventType.equals("unsubscribe")) {
        	//取消订阅
       	 //假如用户退订了公众号，但是他还在被服务，则应取消对用户的服务，并通知客服用户已离开
	       	 respContent = eventMsgManagerService.processUnsubscribeEventMsg(recXMLEntity);
	       	 jsonObj.put("intercept", 1); //拦截消息
	    	 jsonObj.put("content", respContent);
        }else if (eventType.equals("CLICK")) { 
            //点击菜单
           //处理click事件
       	     respContent = eventMsgManagerService.processClickEventMsg(recXMLEntity);
       	     jsonObj.put("intercept", 1); //拦截消息
   	         jsonObj.put("content", respContent);
        }//关注公众号消息
        else if (eventType.equals("SCAN")){
        	respContent = eventMsgManagerService.processSCANEventMsg(recXMLEntity);
        	jsonObj.put("intercept", 1); //拦截消息
  	        jsonObj.put("content", respContent);
        }//点击菜单跳转链接时的事件推送
        else if (eventType.equals("VIEW")){
        	respContent = eventMsgManagerService.processVIEWEventMsg(recXMLEntity);
        	jsonObj.put("intercept", 1); //拦截消息
  	        jsonObj.put("content", respContent);
        }//上报地理位置事件
        else if (eventType.equals("LOCATION")){
        	respContent = eventMsgManagerService.processLOCATIONEventMsg(recXMLEntity);
        	jsonObj.put("intercept", 1); //拦截消息
  	        jsonObj.put("content", respContent);
        }
        return jsonObj;
	}
}
