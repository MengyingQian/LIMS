/**
 * FileName:    WeiXinUserPojo.java
 * @Description: 用户信息get和set方法
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   lqw
 * @version   V1.0 
 * Createdate:        2016年4月19日 上午11:31:17
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.user.pojo;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * ClassName:WeiXinUserPojo <br/>
 * Function: 用户信息get和set方法. <br/>
 * Reason:	 用户信息get和set方法. <br/>
 * Date:     2016年4月19日 上午10:40:30 <br/>
 * @author   lqw
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
public class WeiXinUserPojo {
	/**
	 * 用户ID
	 */
	private long user_id;
	/**
	 * 根据openid生成的MD5
	 */
	private String md5;
	/**
	 * 微信的登录ID，支持微信号登录
	 */
	private String wx_id;
	/**
	 * QQ号，支持QQ号登录
	 */
	private String qq_number;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 用户注册登陆名
	 */
	private String login_name;
	/**
	 * 用户登陆密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nick_name;
	/**
	 * 用户真实姓名
	 */
	private String user_name;
	/**
	 * 证件类型
	 */
	private String id_card_type;
	/**
	 * 证件号码
	 */
	private String id_card;
	/**
	 * 用户性别
	 */
	private String sex;
	/**
	 * 出生日期
	 */
	private Date birth;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 电话号码
	 */
	private String tele;
	/**
	 * 国籍编号id
	 */
	private String country;
	/**
	 * 国籍名字
	 */
	private String country_name;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 县区编号
	 */
	private String county_id;
	/**
	 * 县区名称
	 */
	private String county_name;
	/**
	 * 职业
	 */
	private String industry;
	/**
	 * 职业ID
	 */
	private int industry_id;
	/**
	 * 年收入
	 */
	private int income;
	/**
	 * 联系地址
	 */
	private String addr;
	/**
	 * 邮政编码
	 */
	private String postcode;
	/**
	 * 用户类别
	 */
	private String user_type;
	/**
	 * 用户状态
	 */
	private String user_status;
	/**
	 * 头像URL
	 */
	private String head_img_url;
	/**
	 * 二维码URL
	 */
	private String qr_code_url;
	/**
	 * 用户等级ID
	 */
	private int level_id;
	/**
	 * 用户积分
	 */
	private int points;
	/**
	 * 微信号的openID，可以是空
	 */
	private String openid;
	/**
	 * 微信的unionId，可以是空
	 */
	private String union_id;
	/**
	 * 是否关注
	 */
	private String is_subscribed;
	/**
	 * 状态标示
	 */
	private String status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Timestamp create_time;
	/**
	 * 修改时间
	 */
	private Timestamp update_time;
	
	public WeiXinUserPojo(){};
	
	public WeiXinUserPojo(JSONObject weiXinUserJson){
		this.setIs_subscribed(weiXinUserJson.getString("subscribe"));
		this.setOpenid(weiXinUserJson.getString("openid"));
		this.setNick_name(weiXinUserJson.getString("nickname"));
		this.setSex(weiXinUserJson.getString("sex"));
//		this.setLanguage(weiXinUserJson.getString("language"));
		this.setCity(weiXinUserJson.getString("city"));
		this.setProvince(weiXinUserJson.getString("province"));
		this.setCountry(weiXinUserJson.getString("country"));
		this.setHead_img_url(weiXinUserJson.getString("headimgurl"));
//		this.setCreate_time(weiXinUserJson.get("subscribe_time"));
//		this.setUnion_id(weiXinUserJson.getString("unionid"));
		this.setRemark(weiXinUserJson.getString("remark"));
//		this.setgroupid(weiXinUserJson.getString("groupid"));
//		this.setTagid_List(weiXinUserJson.getString("tagid_list"));
		this.setBirth(new Date());
		this.setForeignKeyAsDefault();
	}
	
	public void setForeignKeyAsDefault(){
		this.setIndustry_id(8); 		//其他行业
		this.setId_card_type("0");		//身份证
		this.setSex("0"); 				//性别未知
		this.setCounty_id("0007");		//海淀区
		this.setIncome(0);				//无收入
		this.setNation("0");			//汉族
		this.setCountry("0");			//中国
	}
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getWx_id() {
		return wx_id;
	}

	public void setWx_id(String wx_id) {
		this.wx_id = wx_id;
	}

	public String getQq_number() {
		return qq_number;
	}

	public void setQq_number(String qq_number) {
		this.qq_number = qq_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getId_card_type() {
		return id_card_type;
	}

	public void setId_card_type(String id_card_type) {
		this.id_card_type = id_card_type;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty_id() {
		return county_id;
	}

	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}

	public String getCounty_name() {
		return county_name;
	}

	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public int getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(int industry_id) {
		this.industry_id = industry_id;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getHead_img_url() {
		return head_img_url;
	}

	public void setHead_img_url(String head_img_url) {
		this.head_img_url = head_img_url;
	}

	public String getQr_code_url() {
		return qr_code_url;
	}

	public void setQr_code_url(String qr_code_url) {
		this.qr_code_url = qr_code_url;
	}

	public int getLevel_id() {
		return level_id;
	}

	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnion_id() {
		return union_id;
	}

	public void setUnion_id(String union_id) {
		this.union_id = union_id;
	}

	public String getIs_subscribed() {
		return is_subscribed;
	}

	public void setIs_subscribed(String is_subscribed) {
		this.is_subscribed = is_subscribed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	

	
}
