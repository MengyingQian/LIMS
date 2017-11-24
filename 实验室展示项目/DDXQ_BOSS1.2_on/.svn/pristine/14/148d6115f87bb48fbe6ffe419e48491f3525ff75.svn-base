package com.ddxq.boss.business.wuyemsg.service;


import java.util.List;
import java.util.Map;

import org.bson.util.StringRangeSet;
import org.springframework.stereotype.Service;

import com.jzsn.utils.https.HttpsManager;
import com.jzsn.utils.https.HttpsSender;

import net.sf.json.JSONObject;

@Service("wuYeMsgService")
public class WuYeMsgServiceImpl implements WuYeMsgService {

	@Override
	public JSONObject sendTmplateMsgForWuYeNotice(String url1, String ACCESS_TOKEN, String openID, String first,
			String title, String date, String content, String remark) {
		System.out.println(ACCESS_TOKEN);
    	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ACCESS_TOKEN;
    	System.out.println("upLoadThumb:"+url);
       
    	String changfengtmpjson=
      		  "{"+
      	          "\"touser\":\""+openID+"\","+
      	           "\"template_id\":\"_mYiJsWlpQCzPiYBZcUkfp8zgiKhmuaiIl5uOs-Pb1M\","+
      	           "\"url\":\""+url1+"\","+
      	           "\"topcolor\":\"#FF0000\","+
      	           "\"data\":{"+
      	                   "\"first\": {"+
      	                       "\"value\":\""+first+"\","+
      	                       "\"color\":\"#666666\""+
      	                   "},"+   
                                  "\"keyword1\": {"+
      	                       "\"value\":\""+title+"\","+
      	                       "\"color\":\"#666666\""+
      	                   "},"+    
      	                    "\"keyword2\": {"+
      	                       "\"value\":\""+date+"\","+
      	                       "\"color\":\"#666666\""+
      	                   "},"+ 
                                   "\"keyword3\": {"+
      	                       "\"value\":\""+content+"\","+
      	                       "\"color\":\"#666666\""+
      	                   "},"+         
  	                   "\"remark\": {"+
  	                   "\"value\":\""+remark+"\","+
  	                   "\"color\":\"#666666\""+
  	                   "}"+
      	           "}"+
      	       "}";
    	//发送器
    	JSONObject returnMsg = null;
    	HttpsSender httpsSender = HttpsManager.getHttpsSender();
    	returnMsg = httpsSender.post_JSON_response_JSON(url,5000,5000, JSONObject.fromObject(changfengtmpjson));
    	return returnMsg;
	}

	@Override
	public JSONObject sendTmplateMsgForWuYeNotice(String url1, String ACCESS_TOKEN, List<Map> list,String mobanId, String first,
			String title, String date, String content, String remark) {
		JSONObject returnMsg = null;
    	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ACCESS_TOKEN;
    	System.out.println("upLoadThumb:"+url);
    	for (int i = 0; i < list.size(); i++) {
    	   String changfengtmpjson=
    	      		  "{"+
    	      	          "\"touser\":\""+list.get(i).get("openId").toString()+"\","+
    	      	           "\"template_id\":\""+mobanId+"\","+
    	      	           "\"url\":\""+url1+"\","+
    	      	           "\"topcolor\":\"#FF0000\","+
    	      	           "\"data\":{"+
    	      	                   "\"first\": {"+
    	      	                       "\"value\":\""+first+"\","+
    	      	                       "\"color\":\"#173177\""+
    	      	                   "},"+   
    	                                  "\"keyword1\": {"+
    	      	                       "\"value\":\""+title+"\","+
    	      	                       "\"color\":\"#173177\""+
    	      	                   "},"+    
    	      	                    "\"keyword2\": {"+
    	      	                       "\"value\":\""+date+"\","+
    	      	                       "\"color\":\"#173177\""+
    	      	                   "},"+ 
    	                                   "\"keyword3\": {"+
    	      	                       "\"value\":\""+content+"\","+
    	      	                       "\"color\":\"#173177\""+
    	      	                   "},"+         
    	  	                   "\"remark\": {"+
    	  	                   "\"value\":\""+remark+"\","+
    	  	                   "\"color\":\"#173177\""+
    	  	                   "}"+
    	      	           "}"+
    	      	       "}";
    	    	//发送器   	   
    	   HttpsSender httpsSender = HttpsManager.getHttpsSender();
       	   returnMsg = httpsSender.post_JSON_response_JSON(url,5000,5000, JSONObject.fromObject(changfengtmpjson));
	}  	
		return returnMsg;
	}

}
