package com.ddxq.admin.admininfo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.admin.admininfo.dao.AdminInfoDao;
import com.ddxq.system.poweruser.controller.PowerUserController;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/admin/admininfo")
public class AdminInfoController {
	private static final String USER_URL = "/admin/admininfo/changepwd";
	private static final String USER_URL2 = "/admin/admininfo/myinfo";
	Logger log = LoggerFactory.getLogger(AdminInfoController.class);
	@Autowired
	AdminInfoDao adminInfoDao;
	@RequestMapping("/changepwd")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(USER_URL);	
		return mav;
	}
	@RequestMapping("/submit")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String pre=jobj.getString("prepwd").trim();
		String pwd=jobj.getString("pwd").trim();
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",adminInfoDao.changepwd(request.getSession().getAttribute("account").toString(),pre , pwd));
		return retJobj.toString();
	}
	@RequestMapping("/myinfo")
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(USER_URL2);	
		return mav;
	}
	@RequestMapping("/search")
	@ResponseBody
	public JSONObject search(HttpServletRequest request, HttpServletResponse response) {	
		String account=request.getSession().getAttribute("account").toString();
		JSONObject object=adminInfoDao.getUserViaAccount(account);
		return object;
	}
	@RequestMapping("/editInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		Map map=new HashMap();
		 Iterator it=jobj.keys();		 
		 while(it.hasNext()){
			  String str = it.next().toString();
			  map.put(str, jobj.getString(str));
			 }
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",adminInfoDao.editInfo(map));
		retJobj.put("url", "/ddxq/admin/userman/show?actorid="+map.get("employeeActor"));
		return retJobj.toString();
		
	}
}
