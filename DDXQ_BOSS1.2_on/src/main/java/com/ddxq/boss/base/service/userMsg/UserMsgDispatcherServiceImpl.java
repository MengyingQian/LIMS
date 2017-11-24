package com.ddxq.boss.base.service.userMsg;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;

import net.sf.json.JSONObject;

/**   
* @ClassName:     MspProcessor.java   
* @Description: TODO(用一句话描述该文件做什么) 
* All rights Reserved, Designed By jzsn  
* Copyright:    Copyright(C) 2015-2015年12月28日 
* Company       闻康云医（北京）科技有限公司 jzsn LTD.  
* @author:    名字 
* @version    V2.0    
* Createdate:         2015年12月28日 下午12:21:14  
*/
public class UserMsgDispatcherServiceImpl implements UserMsgDispatcherService{
	private static Logger log = LoggerFactory.getLogger(UserMsgDispatcherServiceImpl.class);
	UserMsgManagerServiceImpl userMsgManager = new UserMsgManagerServiceImpl();
	public JSONObject processCommonUserMsg(ReceiveXmlEntity recXMLEntity){
    	//文本消息
    	String msgType = recXMLEntity.getMsgType();
    	String respContent = "";
//    	String toUser = userMsgManager.identifyToUser(jzsnResidentsApplicationService, recXMLEntity);
    	
    	String fromUser=recXMLEntity.getFromUserName();
    	JSONObject jsonObject = new JSONObject();
        String createTime=recXMLEntity.getCreateTime();
//        jzsnResidentsBean residents = jzsnResidentsApplicationService.getResidentByOpenId(recXMLEntity.getFromUserName());
	   
        if (msgType.equals("text")) {
        	//文本消息
        	jsonObject.put("intercept",0);
    		jsonObject.put("content","文本消息");
        }else if (msgType.equals("image")) {
        	//图片消息 
        	jsonObject.put("intercept",0);
    		jsonObject.put("content","图片消息 ");
        }else if (msgType.equals("video")) {
            //视频消息
        	jsonObject.put("intercept",0);
    		jsonObject.put("content","视频消息");
        }else if (msgType.equals("shortvideo")) {
            //小视频消息
        	jsonObject.put("intercept",0);
    		jsonObject.put("content","小视频消息");
        }else if (msgType.equals("location")) {
        	//地理位置
        	jsonObject.put("intercept",0);
    		jsonObject.put("content","地理位置");
        }else if (msgType.equals("link")) {
        	//链接消息
        	jsonObject.put("intercept",0);
    		jsonObject.put("content","链接消息");
        }
		return jsonObject;
    }
}
