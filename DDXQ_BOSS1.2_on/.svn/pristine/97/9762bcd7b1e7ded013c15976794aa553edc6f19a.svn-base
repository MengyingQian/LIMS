/**
 * FileName:    MenuServiceImpl.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月8日 下午3:22:00
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月8日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.service.menu;

import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.config.HostConfig;
import com.jzsn.utils.https.HttpClientUtils;

/**
 * ClassName:MenuServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月8日 下午3:22:00 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Autowired
	HostConfig hostConfig;
	public String setMenuJsonString(){
		String menu =  "{"+
			     "\"button\":["+
	               "{"+
	                   "\"name\":\"君淑榜\","+
	                   "\"sub_button\":["
                   +"{"+
                   "\"type\":\"view\","+
                    "\"name\":\"君淑个人榜\","+
                    "\"url\":\"http://"+hostConfig.getMobile_domainName()+"/qxey/wx/yyxx/index.html\""+
                   "},"
                  +"{"+
                  "\"type\":\"view\","+
                  "\"name\":\"君淑企业榜\","+
                  "\"url\":\"http://"+hostConfig.getMobile_domainName()+"/qxey/wx/yyxx/yylx.html\""+
                 "},"
                 +"{"+
                  "\"type\":\"view\","+
                  "\"name\":\"我要打分\","+
                  "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19112ceeb49418a3&redirect_uri=http://"+hostConfig.getMobile_domainName()+"/mod/weixin/igman/index.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\""+
                 "},"
                 +"{"+
                 "\"type\":\"view\","+
                 "\"name\":\"i.上榜\","+
                 "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19112ceeb49418a3&redirect_uri=http://"+hostConfig.getMobile_domainName()+"/mod/weixin/igman/index.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\""+
                "},"
                +"{"+
                 "\"type\":\"view\","+
                 "\"name\":\"君淑自测\","+
                 "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19112ceeb49418a3&redirect_uri=http://"+hostConfig.getMobile_domainName()+"/mod/weixin/appointRegister/departmentList.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\""+
                "}"  
				  +"]},"
				+"{"+
                 "\"name\":\"君淑辩\","+
                 "\"sub_button\":["
               +"{"+
                  "\"type\":\"view\","+
                  "\"name\":\"最嗨辩论\","+
                  "\"url\":\"http://"+hostConfig.getMobile_domainName()+"/qxey/wx/jzzy/jzlc.html\""+
                 "}," 
               +"{"+
                 "\"type\":\"view\","+
                 "\"name\":\"获奖名单\","+
                 "\"url\":\"http://"+hostConfig.getMobile_domainName()+"/qxey/wx/jzzy/jzlc.html\""+
                "},"
               +"{"+
                "\"type\":\"view\","+
                "\"name\":\"开山立辩\","+
                "\"url\":\"http://"+hostConfig.getMobile_domainName()+"/qxey/wx/jzzy/jzlc.html\""+
               "},"
              +"{"+
               "\"type\":\"view\","+
               "\"name\":\"君淑财富\","+
               "\"url\":\"http://"+hostConfig.getMobile_domainName()+"/qxey/wx/jzzy/jzlc.html\""+
              "},"
              +"{"+
                  "\"type\":\"view\","+
                  "\"name\":\"君淑书院\","+
                  "\"url\":\"http://"+hostConfig.getMobile_domainName()+"/qxey/wx/jzzy/dzdt.html\""+
                 "}" 
              + "]}," 
              +"{"+
	               "\"name\":\"君淑会\","+
	               "\"sub_button\":["
				  +"{"+
				  "\"type\":\"view\","+
				  "\"name\":\"君淑润品\","+
				  "\"url\":\"http://m.zuikuh5.com/a/297966_895929.html/\""+
				  "},"
				 +"{"+
                   "\"type\":\"view\","+
                   "\"name\":\"君淑基金\","+
                  "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19112ceeb49418a3&redirect_uri=http://"+hostConfig.getMobile_domainName()+"/mod/weixin/feedback/evaDoctor.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\""+
                  "},"
                 +"{"+
                  "\"type\":\"view\","+
                  "\"name\":\"君淑调解园\","+
                 "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19112ceeb49418a3&redirect_uri=http://"+hostConfig.getMobile_domainName()+"/mod/weixin/feedback/evaDoctor.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\""+
                 "},"
                 +"{"+
                  "\"type\":\"view\","+
                  "\"name\":\"君淑社群\","+
                 "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19112ceeb49418a3&redirect_uri=http://"+hostConfig.getMobile_domainName()+"/mod/weixin/feedback/evaDoctor.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\""+
                 "},"
                +"{"+
                "\"type\":\"view\","+
                "\"name\":\"更多...\","+
                "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19112ceeb49418a3&redirect_uri=http://"+hostConfig.getMobile_domainName()+"/mod/weixin/nav/wdxx.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\""+
               "}"  
	          + "]"+
	          "}" 
	          + "]"+
	          "}"; 
		return menu;
	}
	    
	public String delMenu(String access_token){
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+access_token;
		String jsonStr=null;
		try {
			jsonStr = HttpClientUtils.get(url, "utf-8", 5000, 5000);
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	public String createMenu(String access_token, String menuJsonStr){
		 String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		 String result = null;
		try {
			result = HttpClientUtils.post(url, menuJsonStr, "application/json", "utf-8", 5000, 5000);
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result;
	}
}
