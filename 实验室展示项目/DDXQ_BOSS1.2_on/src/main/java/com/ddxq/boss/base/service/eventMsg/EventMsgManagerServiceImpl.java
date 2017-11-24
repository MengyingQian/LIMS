package com.ddxq.boss.base.service.eventMsg;

import java.util.Date;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.boss.base.user.dao.SubscribeUsersDao;
import com.ddxq.boss.base.user.dao.UsersDao;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.config.HostConfig;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.WeiXinBase;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;
import com.jzsn.utils.weixin.msg.send.passive.PassiveReplyManager;
import com.jzsn.utils.weixin.token.WeiXinTokenManager;
import com.jzsn.utils.weixin.user.UserInfoManager;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName:    EventMsgManagerServiceImpl
 * @Description:TODO(用来处理事件消息的逻辑)
 * @author:   zxz
 * @date:       2016年4月8日 上午8:39:44
 *
 */
@Service("eventMsgManagerService")
public class EventMsgManagerServiceImpl implements EventMsgManagerService{
    @Autowired
    SystemLog systemLog;
    
    @Autowired
    WeiXinTokenManager weiXinTokenManager;
    
    @Autowired
    SubscribeUsersDao subscribeUsersDao;
    
    @Autowired
    HostConfig hostConfig;
    
    PassiveReplyManager xmlAnswer = new PassiveReplyManager();
    UserInfoManager uim = new UserInfoManager();
    
    int read_time_out = 5000;// 读超时时间，单位是毫秒
    int connect_time_out = 500;// 连接超时时间，单位是毫秒

    public String processSubscribeEventMsg(ReceiveXmlEntity recXMLEntity) {
        // 推欢迎消息后，把用户信息插入数据库
        // 获得openid
        // 获得用户基本信息
        // 获得token
        JSONObject resultJson = weiXinTokenManager.getToken();
        String access_token = resultJson.getString("access_token");
        // 获取用户信息
        //这里添加判断语句：是否已经在数据库，在库则不用拉取信息，改变用户状态；不在则需要拉取信息
        if(!subscribeUsersDao.isUserExist(recXMLEntity.getFromUserName()))
        {
	        resultJson = this.uim.getUserInfoViaOpenid(access_token, this.read_time_out, this.connect_time_out,
	                recXMLEntity.getFromUserName(), "zh_CN");
	        if (resultJson.containsKey("errcode"))
	        {
	            systemLog.debugLog(EventMsgManagerServiceImpl.class, "original token is:" + access_token);
	            String errcode = resultJson.getString("errcode");
	            if (errcode.equals("42001") || errcode.equals("40001"))
	            {
	                // 表示token无效,重新获取token
//	                Date now = new Date();
	                resultJson = weiXinTokenManager.regetToken(access_token);
	                if (resultJson.containsKey("errcode") || resultJson.containsKey("error"))
	                {
	                    return resultJson.toString();
	                }
	                else
	                {
	                    access_token = resultJson.getString("access_token");
	                    systemLog.debugLog(EventMsgManagerServiceImpl.class, "new token is:" + access_token);
	                    resultJson = this.uim.getUserInfoViaOpenid(access_token, this.read_time_out, this.connect_time_out,
	                            recXMLEntity.getFromUserName(), "zh_CN");
	                    systemLog.debugLog(EventMsgManagerServiceImpl.class, "微信用户信息 resultJson is:" + resultJson.toString());
	                }
	            }
	        }else{
	            systemLog.debugLog(EventMsgManagerServiceImpl.class, "微信用户信息 resultJson is:" + resultJson.toString());
	        }
	        
	        //最终获取到了用户信息，存入数据库
	        WeiXinUserPojo weiXinUser = new WeiXinUserPojo(resultJson);
	        subscribeUsersDao.saveUserViaUserPojo(weiXinUser);
//	        usersDao.saveUserInfo(resultJson);
        }else
        {
        	//用户信息已经在库，只需将其状态正常化
        	subscribeUsersDao.normalizeUserstatus(recXMLEntity.getFromUserName());
        	//这里可以在考虑一下把用户信息提取出来转换成json
        	systemLog.debugLog(EventMsgManagerServiceImpl.class, "微信用户信息 resultJson is:" + recXMLEntity.getFromUserName());
        }
        // 推欢迎消息
        
        String welcomeMsg = WeiXinBase.getWelcomMsg();
//        String content = this.xmlAnswer.createOneArticle(welcomeMsg, welcomeMsg,
//        		"http://news.sctv.com/rw/lsjm/201103/W020110309374006652685.jpg",
//        		"http://bbs.byr.cn/");
        String domainname = hostConfig.getMobile_domainName();
        String content2 = this.xmlAnswer.createOneArticle("欢迎访问君子淑女！", "致力于复兴和重塑中国人集体人格。", 
        		"http://"+domainname+":8080/group1/M00/00/00/Chj8mVe9P9GAExu8AANQjwG28Ro662.jpg",
        		"http://mp.weixin.qq.com/s?__biz=MzA5NDk1NTU1Mg==&mid=100000066&idx=1&sn=47a388e90cd11b468a2da3ba619c9231&scene=18#wechat_redirect");
        String returnMsg = this.xmlAnswer.formatImageTextAnswer(recXMLEntity.getFromUserName(),
                recXMLEntity.getToUserName(), content2, 1);
        return returnMsg;
    }

    public String processUnsubscribeEventMsg(ReceiveXmlEntity recXMLEntity) {
//        String CustomerID = recXMLEntity.getFromUserName();// 获取要退订的用户ID
//        // 检查是否用户还在被服务中
//        String serverID = CustomerServantRelationManager.userInServiceMap.get(CustomerID);
//        if (serverID != null) {
//            // 假如用户还在被服务，则删除服务，将客服变成空闲，并告诉客服，用户已退订
//            CustomerServantRelationManager.userInServiceMap.remove(CustomerID);
//            CustomerServantRelationManager.servantQueue.add(serverID);
//        }
//        return "患者:" + CustomerID + ",退订了公众号!";
    	subscribeUsersDao.freezeUserStatus(recXMLEntity.getFromUserName());
    	return "";
    }

    public String processClickEventMsg(ReceiveXmlEntity recXMLEntity) {
        String eventKey = recXMLEntity.getEventKey();
        String fromUserName = recXMLEntity.getFromUserName();
        String toName = recXMLEntity.getToUserName();
        if (eventKey.equals("online_service")) {
            return "";
        } else if (eventKey.equals("free_wifi")) {
            String content = "【系统消息】您点击了免费WIFI,谢谢您的光顾!";
            String result = this.xmlAnswer.formatXmlAnswer(
                    recXMLEntity.getFromUserName(), recXMLEntity.getToUserName(), content);
            return result;
        } else if (eventKey.equals("hospital_register")) {
            // 当天挂号
            String to = fromUserName;
            String from = toName;
            String content = this.xmlAnswer
                    .createOneArticle(
                            "尊敬的用户:" + fromUserName + ",已收到您的挂号请求，请点击开始挂号！",
                            "<a href=\"http://www.xywy.com/\">寻医问药网</a>",
                            "http://wx.qlogo.cn/mmopen/YjibkLVZsl3l5LOYvu4mQsEbzaicVAErzRlIic4EEJAgzpjAolQA2hcWxqibAm5icCc2Bo7t8fOw8ZDBX1JQI37icjeicmx3MYpy41O/0",
                            "http://pay.120.io/alipay/getRegList.do?openId=" + fromUserName);
            String returnMsg = this.xmlAnswer.formatImageTextAnswer(to, from, content, 1);
            System.out.println("当天挂号 returnMsg is:" + returnMsg);
            return returnMsg;
        } else if (eventKey.equals("pay_medicine_bill")) {
            String to = fromUserName;
            String from = toName;
            String content = this.xmlAnswer
                    .createOneArticle(
                            "尊敬的用户:" + fromUserName + ",已收到您的支付请求，请点击开始支付！",
                            "<a href=\"http://www.xywy.com/\">寻医问药网</a>",
                            "http://wx.qlogo.cn/mmopen/YjibkLVZsl3l5LOYvu4mQsEbzaicVAErzRlIic4EEJAgzpjAolQA2hcWxqibAm5icCc2Bo7t8fOw8ZDBX1JQI37icjeicmx3MYpy41O/0",
                            "http://pay.120.io/alipay/pay/userPayList.jsp?openId=" + fromUserName);
            String returnMsg = this.xmlAnswer.formatImageTextAnswer(to, from, content, 1);
            return returnMsg;
        } else {
            String content = "【系统消息】无法处理您的点击:" + eventKey;
            String result = this.xmlAnswer.formatXmlAnswer(
                    recXMLEntity.getFromUserName(), recXMLEntity.getToUserName(), content);
            return result;
        }
    }

    public String processSCANEventMsg(ReceiveXmlEntity recXMLEntity) {
        String content = "【系统消息】您已经关注过该公众号了！！";
        String result = this.xmlAnswer.formatXmlAnswer(
                recXMLEntity.getFromUserName(), recXMLEntity.getToUserName(), content);
        return result;
    }

    public String processVIEWEventMsg(ReceiveXmlEntity recXMLEntity) {
        String content = "【系统消息】您点击了VIEW事件!";
        String result = this.xmlAnswer.formatXmlAnswer(
                recXMLEntity.getFromUserName(), recXMLEntity.getToUserName(), content);
        return result;
    }

    public String processLOCATIONEventMsg(ReceiveXmlEntity recXMLEntity) {
        String content = "【系统消息】您点击了LOCATION事件!";
        String result = this.xmlAnswer.formatXmlAnswer(
                recXMLEntity.getFromUserName(), recXMLEntity.getToUserName(), content);
        return result;
    }

    public String processPassiveMsg(ReceiveXmlEntity recXMLEntity) {
        String content = "";
        String result = "";
        String userID = recXMLEntity.getFromUserName();
//        if (CustomerServantRelationManager.servantMap.containsKey(userID)) {
//            // 假如是客服
//        } else {
//            // 假如是普通用户
//            content = "【系统提示】请选择互动->在线咨询!";
//            result = this.xmlAnswer.formatXmlAnswer(
//                    userID, recXMLEntity.getToUserName(), content);
//        }
        return result;
    }

    public String addHeadAndTail(String head, String originalText, String tail) {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        sb.append(originalText);
        sb.append(tail);
        return sb.toString();
    }
}
