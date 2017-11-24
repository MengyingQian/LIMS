package com.ddxq.boss.base.service.userMsg;

/**   
* @ClassName:     UserMsgManager.java   
* @Description: TODO(用一句话描述该文件做什么) 
* All rights Reserved, Designed By jzsn  
* Copyright:    Copyright(C) 2015-2015年12月28日 
* Company       闻康云医（北京）科技有限公司 jzsn LTD.  
* @author:    名字 
* @version    V2.0    
* Createdate:         2015年12月28日 下午2:46:04  
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.WeiXinBase;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;
import com.jzsn.utils.weixin.msg.send.passive.PassiveReplyManager;

import net.sf.json.JSONObject;

@Service("userMsgManagerService")
public class UserMsgManagerServiceImpl implements UserMsgManagerService{
    @Autowired 
    SystemLog systemLog;
    
	private PassiveReplyManager passiveReplyManager = new PassiveReplyManager();
	
	public JSONObject process_text_msg(ReceiveXmlEntity recXMLEntity){
		String respContent = recXMLEntity.getContent();
        String toUser = recXMLEntity.getToUserName();
        JSONObject jsonObject = new JSONObject();
    	if (null != toUser){//已建立连接 直接发给医生
    		jsonObject.put("intercept",1);
    		jsonObject.put("content",""+respContent);
    		return jsonObject;
    	}else{
    		//没有建立客服与客户连接的服务
    		 //此消息是用户发送的 
    			jsonObject.put("intercept",0);//不拦截
    			String content = WeiXinBase.getWelcomTip();
    			String result = passiveReplyManager.formatXmlAnswer(
            			recXMLEntity.getFromUserName(), recXMLEntity.getToUserName(), content);
            	jsonObject.put("content", ""+result);
    			return jsonObject;  	
    	}
	}
	
	public String identifyToUser(ReceiveXmlEntity recXMLEntity){
    	//开始  假定这是用户发的消息
//    	String OpenID = recXMLEntity.getFromUserName();//获得客服的ID
//    	jzsnResidentsBean patients=jzsnResidentsApplicationService.getResidentByOpenId(OpenID);
//    	String servantID =DoctorSelector.patientToDoctorMap.get((patients.getId()+"").trim());
//    	
//    	System.out.println("---patients 的  id 是     "+patients.getId());
//    	 
//    	if (null == servantID){
//    		   //表明这是用户在没有选择医生的情况下发来的消息
//    			System.out.println("ordinary msg from:"+OpenID);
//    			return null;  
//    		}
//    	 else{
//    		//表明用户正在请求服务
//    		//消息转发给医生
//    		System.out.println("customer is inquiring :"+servantID);
//    		return servantID;
//    	}
		return "";
    }
}
