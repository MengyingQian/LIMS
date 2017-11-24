package com.ddxq.boss.base.user.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.cache.CacheUtilT;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 上午9:10:02 
* 类说明 
*/
@Repository("UsersRedisDao")
public class UsersRedisDaoImpl implements UsersRedisDao {
	@Autowired 
	CacheUtilT cacheUtilT;
	
	private final static String redisKey="Users_info_";
	private final static int expireTime=7200;//超时时间限制

	@Override
	public WeiXinUserPojo getUser(String key) {
		return cacheUtilT.get(redisKey+"_"+key, key, WeiXinUserPojo.class);
	}

	@Override
	public boolean keyUserExist(String key) {
		return cacheUtilT.keyIsExist(redisKey+"_"+key, key);
	}
	@Override
	public void putUser(String key,WeiXinUserPojo weixinUser) {
	
		if( null==weixinUser.getMd5() || "".equals(weixinUser.getMd5()) ){
			weixinUser.setMd5(" ");
		}
		if( null==weixinUser.getWx_id() || "".equals(weixinUser.getWx_id()) ){
			weixinUser.setWx_id(" ");
		}
		if( null==weixinUser.getQq_number() || "".equals(weixinUser.getQq_number()) ){
			weixinUser.setQq_number(" ");
		}
		if( null==weixinUser.getEmail() || "".equals(weixinUser.getEmail()) ){
			weixinUser.setEmail(" ");;
		}//5*1
		
		if( null==weixinUser.getLogin_name() || "".equals(weixinUser.getLogin_name()) ){
			weixinUser.setLogin_name(" ");
		}
		
		if( null==weixinUser.getMd5() || "".equals(weixinUser.getMd5()) ){
			weixinUser.setMd5(" ");
		}
		if( null==weixinUser.getPassword() || "".equals(weixinUser.getPassword()) ){
			weixinUser.setPassword(" ");
		}
		if( null==weixinUser.getNick_name() || "".equals(weixinUser.getNick_name()) ){
			weixinUser.setNick_name(" ");
		}
		if( null==weixinUser.getId_card_type() || "".equals(weixinUser.getId_card_type()) ){
			weixinUser.setId_card_type(" ");
		}//5*2
				
		if( null==weixinUser.getId_card() || "".equals(weixinUser.getId_card()) ){
			weixinUser.setId_card(" ");
		}
		if( null==weixinUser.getSex() || "".equals(weixinUser.getSex()) ){
			weixinUser.setSex(" ");
		}
		if( null==weixinUser.getBirth() || "".equals(weixinUser.getBirth()) ){
			weixinUser.setBirth(new Date());//设置当前时间
		}
		if( null==weixinUser.getNation() || "".equals(weixinUser.getNation()) ){
			weixinUser.setNation(" ");
		}
		if( null==weixinUser.getMobile() || "".equals(weixinUser.getMobile()) ){
			weixinUser.setMobile(" ");
		}//5*3
		
		if( null==weixinUser.getTele() || "".equals(weixinUser.getTele()) ){
			weixinUser.setTele(" ");
		}
		if( null==weixinUser.getCountry() || "".equals(weixinUser.getCountry()) ){
			weixinUser.setCountry(" ");
		}
		if( null==weixinUser.getCountry_name() || "".equals(weixinUser.getCounty_name()) ){
			weixinUser.setCounty_name(" ");
		}
		if( null==weixinUser.getProvince() || "".equals(weixinUser.getProvince()) ){
			weixinUser.setProvince(" ");
		}
		if( null==weixinUser.getCity() || "".equals(weixinUser.getCity()) ){
			weixinUser.setCity(" ");
		}//5*4

		if( null==weixinUser.getCounty_id() || "".equals(weixinUser.getCounty_id()) ){
			weixinUser.setCounty_id(" ");
		}		
		if( null==weixinUser.getCountry_name() || "".equals(weixinUser.getCountry_name()) ){
			weixinUser.setCountry_name(" ");
		}		
		if( null==weixinUser.getIndustry() || "".equals(weixinUser.getIndustry()) ){
			weixinUser.setIndustry(" ");
		}//5*5
		
		if( null==weixinUser.getAddr() || "".equals(weixinUser.getAddr()) ){
			weixinUser.setAddr(" ");
		}		
		if( null==weixinUser.getPostcode() || "".equals(weixinUser.getPostcode()) ){
			weixinUser.setPostcode(" ");
		}
		if( null==weixinUser.getUser_type() || "".equals(weixinUser.getUser_type()) ){
			weixinUser.setUser_type(" ");
		}
		if( null==weixinUser.getUser_status() || "".equals(weixinUser.getUser_status()) ){
			weixinUser.setUser_status(" ");
		}
		if( null==weixinUser.getHead_img_url() || "".equals(weixinUser.getHead_img_url()) ){
			weixinUser.setHead_img_url(" ");
		}//5*6

		if( null==weixinUser.getQr_code_url() || "".equals(weixinUser.getQr_code_url()) ){
			weixinUser.setQr_code_url(" ");
		}
		if( null==weixinUser.getOpenid() || "".equals(weixinUser.getOpenid()) ){
			weixinUser.setOpenid(" ");
		}
		if( null==weixinUser.getUnion_id() || "".equals(weixinUser.getUnion_id()) ){
			weixinUser.setUnion_id(" ");
		}//5*7
	
		if( null==weixinUser.getIs_subscribed() || "".equals(weixinUser.getIs_subscribed()) ){
			weixinUser.setIs_subscribed(" ");
		}
		if( null==weixinUser.getStatus() || "".equals(weixinUser.getStatus()) ){
			weixinUser.setStatus(" ");
		}
		if( null==weixinUser.getRemark() || "".equals(weixinUser.getRemark()) ){
			weixinUser.setRemark(" ");
		}//5*8
		
		cacheUtilT.put(redisKey+"_"+key, key, weixinUser, expireTime);
	}
	
	public WeiXinUserPojo getUserInfo(String openid){
	    WeiXinUserPojo user = cacheUtilT.get(redisKey+"_"+openid, openid, WeiXinUserPojo.class);
		return user;
	}
}
