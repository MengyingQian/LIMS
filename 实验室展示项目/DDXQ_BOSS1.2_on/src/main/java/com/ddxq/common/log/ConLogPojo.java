package com.ddxq.common.log;

import java.io.Serializable;
import java.net.InetAddress;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月3日 上午10:28:04 
* 类说明 
*/
public class ConLogPojo implements Serializable{
	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */
	

	private String time;//时间 "time"
	private String act_level;//操作级别（DEBUG，INFO，ERROR） "act_level"
	private String server_IP;//服务器IP地址  "server_IP"
	private String server_port;//服务器端口 "server_port"
	private String user_IP;//用户来源IP  "user_IP"
	private String action_type;//操作类型  "action_type"
	private String action_info;//操作信息  "action_info" 此处存取的是传过来的className
	private String action_desc;//操作描述  "action_desc"//干了什么事比如用户登录，
	private String action_result;//操作结果（成功、失败、延迟） "action_result"
	private String jsp_name;//JSP页面名字  	"jsp_name"
	private String request_url;//访问请求的URL	"request_url"
	private String stay_time;//页面停留时间   "stay_time"
	private String nickname;//用户名   "nickname"		
	private String userid;//用户id "userid"
	private String money;//钱数  "money"
	
	
	public ConLogPojo(String time, String act_level, String server_IP, String server_port, String user_IP,
			String action_type, String action_info, String action_desc, String action_result, String jsp_name,
			String request_url, String stay_time, String nickname, String userid, String money) {
		super();
		this.time = time;
		this.act_level = act_level;
		this.server_IP = server_IP;
		this.server_port = server_port;
		this.user_IP = user_IP;
		this.action_type = action_type;
		this.action_info = action_info;
		this.action_desc = action_desc;
		this.action_result = action_result;
		this.jsp_name = jsp_name;
		this.request_url = request_url;
		this.stay_time = stay_time;
		this.nickname = nickname;
		this.userid = userid;
		this.money = money;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAct_level() {
		return act_level;
	}
	public void setAct_level(String act_level) {
		this.act_level = act_level;
	}
	public String getServer_IP() {
		return server_IP;
	}
	public void setServer_IP(String server_IP) {
		this.server_IP = server_IP;
	}
	public String getServer_port() {
		return server_port;
	}
	public void setServer_port(String server_port) {
		this.server_port = server_port;
	}
	public String getUser_IP() {
		return user_IP;
	}
	public void setUser_IP(String user_IP) {
		this.user_IP = user_IP;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getAction_info() {
		return action_info;
	}
	public void setAction_info(String action_info) {
		this.action_info = action_info;
	}
	public String getAction_desc() {
		return action_desc;
	}
	public void setAction_desc(String action_desc) {
		this.action_desc = action_desc;
	}
	public String getAction_result() {
		return action_result;
	}
	public void setAction_result(String action_result) {
		this.action_result = action_result;
	}
	public String getJsp_name() {
		return jsp_name;
	}
	public void setJsp_name(String jsp_name) {
		this.jsp_name = jsp_name;
	}
	public String getRequest_url() {
		return request_url;
	}
	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}
	public String getStay_time() {
		return stay_time;
	}
	public void setStay_time(String stay_time) {
		this.stay_time = stay_time;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}

	
	
	

	

	
	

	
}
