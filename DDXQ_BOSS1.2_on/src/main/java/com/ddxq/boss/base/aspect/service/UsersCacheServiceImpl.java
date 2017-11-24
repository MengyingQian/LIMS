package com.ddxq.boss.base.aspect.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.aspect.userCache.UserCacheAspectj;
import com.ddxq.boss.base.country.dao.CountryDao;
import com.ddxq.boss.base.openid.redis.dao.OpenidIDRedisDao;
import com.ddxq.boss.base.user.dao.UsersDao;
import com.ddxq.boss.base.user.dao.UsersRedisDao;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.config.HostConfig;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.WeiXinBase;
import com.jzsn.utils.weixin.token.WeiXinTokenManager;
import com.jzsn.utils.weixin.user.UserInfoManager;

import net.sf.json.JSONObject;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 上午10:15:10 
* 类说明 
*/
@Repository("UsersService")
public class UsersCacheServiceImpl implements UsersCacheService {
	
	@Autowired
	private CountryDao countryDao;
	@Autowired
	private SystemLog systemLog;
	@Autowired
	private UsersRedisDao userRedisDao;
	@Autowired
	private OpenidIDRedisDao openidIDRedisDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private WeiXinTokenManager weixinTokenManager;
	@Autowired
	private HostConfig hostConfig;
	
	@Autowired
	BaseDAO baseDAO;
	
	private UserInfoManager userinfo = new 	UserInfoManager();
	private final static int read_time_out = 5000;
	private final static int connect_time_out = 5000;
	private final static String APPID=WeiXinBase.getApp_ID();
	private final static String SECRET=WeiXinBase.getApp_Secret();  
	
	@Transactional(propagation=Propagation.REQUIRED)//保证原子操作，不被多线程干扰
	@Override
	public long getUserID() {
		return  usersDao.getUserId();
	}
	@Override
	public WeiXinUserPojo getUserfromOpneid(String openid) {
		WeiXinUserPojo user = null;
		if(userRedisDao.keyUserExist(openid)){//redis
			user = userRedisDao.getUser(openid);
			systemLog.debugLog(getClass(), "redis user is exist，user："+user.getNick_name());
		}else{//mysql
			Map usermysql =usersDao.UseridGetMap(openid);
		    user = MapToWeiXinUser(usermysql);
		    userRedisDao.putUser(openid, user);
			systemLog.debugLog(UserCacheAspectj.class, "mysql数据注入redis：user："+user.toString());
		}
		return user;
	}
	
	
	@Override
	public String OpenidGetUserID(String openid) {
		return openidIDRedisDao.getOpenidID(openid);
	}

	@Override
	public WeiXinUserPojo IdGetWeiXinUser(String user_id, String openid) {
		WeiXinUserPojo user = null;
		if(userRedisDao.keyUserExist(user_id)){//redis
			user = userRedisDao.getUser(user_id);
			systemLog.debugLog(getClass(), "redis user is exist，user："+user.getNick_name());
		}
		else{//mysql
			Map usermysql =usersDao.UseridGetMap(user_id);
		    if(null != usermysql.get("openid") && !"".equals(usermysql.get("openid")) )  {
		    	user = MapToWeiXinUser(usermysql);
		    	userRedisDao.putUser(user_id, user);
				systemLog.debugLog(UserCacheAspectj.class, "mysql数据注入redis：user："+user.toString());
			}else{//拉取(UnionID机制)
				JSONObject retJson = weixinTokenManager.getToken();
				JSONObject userJson =userinfo.getUserInfoViaOpenid(retJson.getString("access_token"), read_time_out, connect_time_out, openid, "zh_CN");
				systemLog.debugLog(UserCacheAspectj.class, "UnionID机制拉取retJson3为："+userJson.toString());
				user = JsonToWeiXinUser(userJson);
				for(int i=0;i<5;i++){
					if(usersDao.saveUserViaUserPojo(user)){
						systemLog.debugLog(UserCacheAspectj.class, "数据注入mysql：user："+user.toString());
						break;	
					}
				}
				usermysql=usersDao.OpenidGetMap(openid);
				long user_idsql=(Long)usermysql.get("user_id");
				user.setUser_id(user_idsql);
				user_id = Long.toString(user_idsql);
				userRedisDao.putUser(user_id, user);
				systemLog.debugLog(UserCacheAspectj.class, "数据注入redis：user："+user.toString());
				openidIDRedisDao.putOpenidID(openid, user_id);
				systemLog.debugLog(UserCacheAspectj.class, "将openid："+openid+"和user_id："+user_id+"注入redis");					
		    } 						
		}
		return user;
	}

	@Override
	public WeiXinUserPojo CodeGetWeiXinUser(String code) {
		WeiXinUserPojo user =null;	
		JSONObject retJson = userinfo.getAccessTokenViaOauth2Code(APPID, SECRET, code, "authorization_code", read_time_out, connect_time_out);
		systemLog.debugLog(UserCacheAspectj.class, "拉取openid和accesstoken为："+retJson.toString());
		String openid = retJson.getString("openid");
		String user_id = OpenidGetUserID(openid);
		if(null == user_id || "".equals(user_id) )
		{
			Map<String,Object> usermysql=usersDao.OpenidGetMap(openid);
			if(null == usermysql.get("nick_name") || "".equals(usermysql.get("nick_name"))){//不存在
				JSONObject retJson1 =userinfo.getUserInfoViaOauth2Code(retJson.getString("access_token"), read_time_out, connect_time_out, openid, "zh_CN");
				systemLog.debugLog(UserCacheAspectj.class, "retJson1的值为："+retJson1.toString());
				user = Json1ToWeiXinUser(retJson1);//注入信息
				for(int i=0;i<5;i++){
					if(usersDao.saveUserViaUserPojo(user)){
						systemLog.debugLog(UserCacheAspectj.class, "数据成功注入到mysql中：user："+user.toString());
						break;	
					}
				}
				usermysql=usersDao.OpenidGetMap(openid);//取user_id
				long user_idsql=(Long)usermysql.get("user_id");
				user.setUser_id(user_idsql);
				user_id = Long.toString(user_idsql) ;
				userRedisDao.putUser(user_id, user);
				systemLog.debugLog(UserCacheAspectj.class, "数据注入redis：user："+user.toString());
				openidIDRedisDao.putOpenidID(openid, user_id);
				systemLog.debugLog(UserCacheAspectj.class, "将openid："+openid+"和user_id："+user_id+"注入redis");	
			}else{//存在
				user = MapToWeiXinUser(usermysql);
				systemLog.debugLog(UserCacheAspectj.class, "在mysql中查询到用户信息，nick_name："+user.getNick_name());
				user_id = Long.toString((Long)usermysql.get("user_id"));
				userRedisDao.putUser(user_id, user);
				systemLog.debugLog(UserCacheAspectj.class, "数据注入到redis中：user："+user.toString());
				openidIDRedisDao.putOpenidID(openid, user_id);
				systemLog.debugLog(UserCacheAspectj.class, "将openid："+openid+"和user_id："+user_id+"永久存入redis中");
			}
			return user;
		}else{//通过id得到信息
			if(userRedisDao.keyUserExist(user_id)){
				user = userRedisDao.getUser(user_id);
				systemLog.debugLog(UserCacheAspectj.class, "检测到redis中存在用户信息，user："+user.getNick_name());
			}else{//mysql
				Map<String,Object> usermysql =usersDao.UseridGetMap(user_id);
			    if(null != usermysql.get("openid") && !"".equals(usermysql.get("openid")) ) {
					systemLog.debugLog(UserCacheAspectj.class, "检测到mysql中存在用户信息");
					user = MapToWeiXinUser(usermysql);
					userRedisDao.putUser(user_id, user);
					systemLog.debugLog(UserCacheAspectj.class, "mysql数据注入到redis中：user："+user.toString());
				}else{//授权方式
					JSONObject retJson1 =userinfo.getUserInfoViaOauth2Code(retJson.getString("access_token"), read_time_out, connect_time_out, openid, "zh_CN");
					systemLog.debugLog(UserCacheAspectj.class, "retJson1的值为："+retJson1.toString());
					user = Json1ToWeiXinUser(retJson1);
					for(int i=0;i<5;i++){
						if(usersDao.saveUserViaUserPojo(user)){
							systemLog.debugLog(UserCacheAspectj.class, "数据成功注入到mysql中：user："+user.toString());
							break;	
						}
					}
					usermysql=usersDao.OpenidGetMap(openid);
					long user_idsql=(Long)usermysql.get("user_id");
					user.setUser_id(user_idsql);
					user_id = Long.toString(user_idsql) ;
					userRedisDao.putUser(user_id, user);
					systemLog.debugLog(UserCacheAspectj.class, "数据注入到redis中：user："+user.toString());
					openidIDRedisDao.putOpenidID(openid, user_id);
					systemLog.debugLog(UserCacheAspectj.class, "将openid："+openid+"和user_id："+user_id+"永久存入redis中");					
				} 
			}
		}
		return user;
	}

	@Override
	public WeiXinUserPojo OpenidGetWeiXinUser(String openid) {
		WeiXinUserPojo user = null;
		String user_id = null;
		Map <String,Object> usermysql=usersDao.OpenidGetMap(openid);
		if(null != usermysql.get("openid") && !"".equals(usermysql.get("openid")) ) {//mysql存在
			user = MapToWeiXinUser(usermysql);		
			systemLog.debugLog(UserCacheAspectj.class, "在mysql中查询到用户信息，nick_name："+user.getNick_name());
			user_id = Long.toString((Long)usermysql.get("user_id")) ;
			userRedisDao.putUser(user_id, user);
			systemLog.debugLog(UserCacheAspectj.class, "数据注入到redis中：user："+user.toString());
			openidIDRedisDao.putOpenidID(openid, user_id);
			systemLog.debugLog(UserCacheAspectj.class, "将openid："+openid+"和user_id："+user_id+"永久存入redis中");					
		}else{//(unios机制)
			JSONObject retJson = weixinTokenManager.getToken();
			JSONObject userJson =userinfo.getUserInfoViaOpenid(retJson.getString("access_token"), read_time_out, connect_time_out, openid, "zh_CN");
			systemLog.debugLog(UserCacheAspectj.class, "直接openid拉取retJson3的值为："+userJson.toString());
			user = JsonToWeiXinUser(userJson);
			for(int i=0;i<5;i++){
				if(usersDao.saveUserViaUserPojo(user)){
					systemLog.debugLog(UserCacheAspectj.class, "数据成功注入到mysql中：user："+user.toString());
					break;	
				}
			}
			usermysql=usersDao.OpenidGetMap(openid);
			long user_idsql=(Long)usermysql.get("user_id");
			user.setUser_id(user_idsql);
			user_id = Long.toString(user_idsql) ;
			userRedisDao.putUser(user_id, user);
			systemLog.debugLog(UserCacheAspectj.class, "数据注入到redis中：user："+user.toString());
			openidIDRedisDao.putOpenidID(openid, user_id);
			systemLog.debugLog(UserCacheAspectj.class, "将openid："+openid+"和user_id："+user_id+"永久存入redis中");
		}
		return user;
	}
	
	
	
	@Override
	public WeiXinUserPojo MapToWeiXinUser(Map usermysql) {
		WeiXinUserPojo user = new WeiXinUserPojo();
		user.setUser_id((long)usermysql.get("user_id"));
		user.setMd5((String)usermysql.get("md5"));
		user.setWx_id((String)usermysql.get("wx_id"));
		user.setQq_number((String)usermysql.get("qq_number"));
		user.setEmail((String)usermysql.get("email"));
		user.setLogin_name((String)usermysql.get("login_name"));
		user.setPassword((String)usermysql.get("password"));
		user.setNick_name((String) usermysql.get("nick_name"));
		user.setUser_name((String) usermysql.get("user_name"));
		user.setId_card_type((String) usermysql.get("id_card_type"));
		user.setId_card((String) usermysql.get("id_card"));
		user.setSex((String) usermysql.get("sex"));
		user.setBirth((Date) usermysql.get("birth"));
		user.setNation((String) usermysql.get("nation"));
		user.setMobile((String) usermysql.get("mobile"));
		user.setTele((String) usermysql.get("tele"));
		user.setCountry((String) usermysql.get("country"));
		user.setProvince((String) usermysql.get("province"));
		user.setCity((String) usermysql.get("city"));
		user.setCounty_id((String)usermysql.get("county_id"));
		user.setCounty_name((String)usermysql.get("county_name"));
		user.setIndustry((String)usermysql.get("industry"));
		user.setIndustry_id((int)(usermysql.get("industry_id")!=null?usermysql.get("industry_id"):0));
		user.setIncome((int)(usermysql.get("income")!=null?usermysql.get("income"):0));
		user.setAddr((String)usermysql.get("addr"));
		user.setPostcode((String)usermysql.get("postcode"));
		user.setUser_type((String) usermysql.get("user_type"));
		user.setUser_status((String) usermysql.get("user_status"));
		user.setHead_img_url((String) usermysql.get("head_img_url"));
		user.setQr_code_url((String) usermysql.get("qr_code_url"));
		user.setLevel_id((int)(usermysql.get("level_id")!=null?usermysql.get("level_id"):0));
		user.setPoints((int)(usermysql.get("points")!=null?usermysql.get("points"):0));
		user.setOpenid((String) usermysql.get("openid"));
		user.setUnion_id((String) usermysql.get("union_id"));
		user.setIs_subscribed((String) usermysql.get("is_subscribed"));
		user.setStatus((String) usermysql.get("status"));
		user.setRemark((String) usermysql.get("remark"));
		user.setCreate_time((Timestamp) usermysql.get("create_time"));
		user.setUpdate_time((Timestamp) usermysql.get("create_time"));
		return user;
	}

	@Override
	public WeiXinUserPojo JsonToWeiXinUser(JSONObject retJson3) {
		WeiXinUserPojo user = new WeiXinUserPojo();
		user.setIs_subscribed(retJson3.getString("subscribe"));//用户是否关注
		user.setHead_img_url(retJson3.getString("headimgurl"));//头像信息，关注时间并没有得到
		user.setOpenid(retJson3.getString("openid"));
		user.setNick_name(retJson3.getString("nickname"));
		user.setSex(retJson3.getString("sex"));
		user.setCity(retJson3.getString("city"));
		user.setProvince(retJson3.getString("province"));
		user.setCounty_name(retJson3.getString("country"));
		user.setCountry(countryDao.NameGetID(retJson3.getString("country")));
		user.setAddr(" ");
		return user;
	}

	@Override
	public WeiXinUserPojo Json1ToWeiXinUser(JSONObject retJson1) {
		WeiXinUserPojo user = new WeiXinUserPojo();
		user.setHead_img_url(retJson1.getString("headimgurl"));
		user.setQr_code_url(" ");
		user.setOpenid(retJson1.getString("openid"));
		user.setNick_name(retJson1.getString("nickname"));
		user.setSex(retJson1.getString("sex"));
		user.setCity(retJson1.getString("city"));
		user.setProvince(retJson1.getString("province"));
		user.setCounty_name(retJson1.getString("country"));//国家名字
		user.setCountry(countryDao.NameGetID(retJson1.getString("country")));//国家id
		return user;
	}




}
