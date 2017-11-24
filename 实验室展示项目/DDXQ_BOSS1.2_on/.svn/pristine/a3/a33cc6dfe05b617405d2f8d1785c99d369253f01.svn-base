/**
 * FileName:    WeiXinUserController.java
 * @Description: TODO(用一句话描述该文件做什么)
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
 * 2016年4月19日  zhu xu zhen          0.1             0.1
 * 2016年4月19日 lqw   				0.1				0.1
 * 2016年4月21日jkc                  0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.aspect.service.UsersCacheService;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.boss.base.user.service.WeiXinUserService;
import com.ddxq.boss.base.util.jssdk.JSSDK;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.WeiXinBase;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * ClassName:WeiXinUserController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月19日 上午11:31:17 <br/>
 * @author   zxz,lqw，jkc
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Controller
@RequestMapping("/ddxq/wx/user")
public class WeiXinUserController {

    private static final String USER_REGISTER_URI = "/weixin/common/user/zhuce/register";
    private static final String EDIT_INFO_URI="/weixin/jshui/gengduo/gerenzhongxin/gerenxinxi/editInfo";
    private static final String USER_LOGIN_URI="/weixin/common/user/zhuce/login";
    private static final String USER_CHANGE_PASSWORD="/weixin/common/user/zhuce/changepass";
    @Autowired 
    SystemLog systemLog;
    @Autowired
    WeiXinUserService weixinUserService;
    @Autowired
    JSSDK jSSDK;

	@Autowired 
	UsersCacheService usersService;
	
	@Autowired
	private  BaseDAO baseDao;

	@RequestMapping("/register")
	public ModelAndView userRegister(HttpServletRequest request, HttpServletResponse response, WeiXinUserPojo weixinUser){
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName(USER_REGISTER_URI);//跳转到用户注册界面
		} catch (Exception e) {
			systemLog.errLog(WeiXinUserController.class, weixinUser, "访问用户注册页面出现异常", e);
		}
/*		
		System.out.println("开始生成JSON格式的字符串************");
		String sqlpro = " select * from province  ";
		List<Map<String,Object>> pro =baseDao.queryForList(sqlpro);
		JSONArray proviences = new JSONArray();
		for( Map dange:pro  ){
			JSONArray citys = new JSONArray();
			JSONObject provience = new JSONObject();
			
			provience.put("id", dange.get("province_id"));
			provience.put("name", dange.get("province_name"));
			String sqlcity = " select city_id,city_name from city where province_id=:province_id "; 
			Map get = new HashMap();
			get.put("province_id", dange.get("province_id"));
			List<Map> sqlcitys = new ArrayList();
			sqlcitys = baseDao.queryForList(sqlcity, get);
			for( Map sqldange:sqlcitys ){
				JSONObject city = new JSONObject();
				city.put("id", sqldange.get("city_id"));
				city.put("name", sqldange.get("city_name"));
				citys.add(city);
			}
			provience.put("city", citys);
			proviences.add(provience);
		}
		
		System.out.println(proviences.toString());
		
		
		JSONArray area = new JSONArray();*/
		
//		String sqlcity =  " select county_id,county_name,city_id from county ";
//		List<Map> citysql = baseDao.queryForList(sqlcity);
//		for( Map citysdange:citysql  ){
//			
//			JSONObject conty  = new  JSONObject();
//			conty.put("id",citysdange.get("county_id")  );
//			conty.put("name",citysdange.get("county_name")  );
//			conty.put("pid",citysdange.get("city_id")  );
//			
//			area.add(conty);
//		}
//		System.out.println(area.toString());
//		System.out.println("end************");
		
		
		
		
		return mav;
	}
	
	@RequestMapping("/register_save")
	public ModelAndView userRegisterSave(HttpServletRequest request, HttpServletResponse response,WeiXinUserPojo weixinUser ){
	
		ModelAndView mav = new ModelAndView();
		String flag=request.getParameter("flag");
		System.out.println("flag: "+flag);
		if( "register".equals(flag) ){//信息更新
			weixinUserService.updateZhuCe(request, weixinUser);
			String mobile = request.getParameter("mobile");
			String password = request.getParameter("password");
			weixinUser.setMobile(mobile);
			weixinUser.setPassword(password);
			mav.addObject("cstm", weixinUser);
		}else if("login".equals(flag) ){
			mav.addObject("cstm", weixinUser);
		}

		String urlpath = request.getRequestURL().toString();
		Map<String,String> data = jSSDK.jssdkReturn(urlpath);
		mav.addObject("appid", WeiXinBase.getApp_ID());
		mav.addObject("timestamp", data.get("timestamp"));
		mav.addObject("nonceStr", data.get("nonceStr"));
		mav.addObject("signature", data.get("signature"));
		mav.addObject("fromlogin","fromlogin");
		mav.setViewName(EDIT_INFO_URI);
		return mav;
	}
	
//	@RequestMapping("/getcode")
//	@ResponseBody
//	public  Map<String,Object> getCode(HttpServletRequest request,HttpServletResponse response,WeiXinUserPojo weixinUser,@RequestBody JSONObject obj ){
//		String mobile = obj.getString("mobile");
//		String change = obj.getString("change");
//		Map<String,Object> result = weixinUserService.getCode(mobile,change);
//		return result;
//	}
	
	@RequestMapping("/yanzheng")
	@ResponseBody
	public  Map<String,Object> yanzheng(HttpServletRequest request,HttpServletResponse response,WeiXinUserPojo weixinUser,@RequestBody JSONObject obj ){
		return weixinUserService.yanzheng(obj);
	}
	
	/*login 界面*/
	
	@RequestMapping("/login")
	public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response,WeiXinUserPojo weixinUser ){
		ModelAndView model = new ModelAndView();
		model.setViewName(USER_LOGIN_URI);
		return model;
	}
	
	@RequestMapping("/loginyanzheng")
	@ResponseBody
	public Map<String,Object> loginyanzheng(HttpServletRequest request,HttpServletResponse response,WeiXinUserPojo weixinUser,@RequestBody JSONObject obj ){
		Map<String,Object> map = weixinUserService.loginyanzheng(obj);
		return map;
	}
	@RequestMapping("/changepassword")
	public ModelAndView changPassword(HttpServletRequest request,HttpServletResponse response,WeiXinUserPojo weixinUser){
		ModelAndView model = new ModelAndView();
		model.setViewName(USER_CHANGE_PASSWORD);
		return model;
	}
	@RequestMapping("/getopenid")
	@ResponseBody
	public Map<String,Object> getopneid(HttpServletRequest request,HttpServletResponse response,WeiXinUserPojo weixinUser,@RequestBody JSONObject obj ){

		Map<String,Object> map = new HashMap<String,Object>();
		long openid=usersService.getUserID();//openid等于userid
		map.put("success", true);
		map.put("openid", String.valueOf(openid));
		return map;
	}
	
	@RequestMapping("/getuserinfo")
	@ResponseBody
	public Map<String,Object>  getUserinfo(HttpServletRequest request,HttpServletResponse response,WeiXinUserPojo weixinUser,@RequestBody JSONObject obj ){
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == obj){
			map.put("success", false);
			return map;
		}else if( null==obj.getString("openid") ){
			map.put("success", false);
			return map;
		}
		map=weixinUserService.genrenzhongxin( obj.getString("openid") );
		return map;
	}
	
}
