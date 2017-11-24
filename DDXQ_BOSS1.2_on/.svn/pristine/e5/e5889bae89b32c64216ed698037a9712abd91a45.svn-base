package com.ddxq.system.login.controller;

import java.util.ArrayList;
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

import com.ddxq.boss.security.captcha.PatchcaServlet;
import com.ddxq.common.config.HostConfig;
import com.ddxq.system.login.dao.SystemLoginDao;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/system")
public class SystemLoginController {
	private static final String LOGIN_URL = "/systemLogin";
	private static final String MAIN_URL = "/mainWork";

	Logger log = LoggerFactory.getLogger(SystemLoginController.class);  
	@Autowired
	SystemLoginDao systemLoginDao;
	@Autowired
	private HostConfig hostConfig;

	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		ArrayList<String>list=new ArrayList<>();
		mav.setViewName(LOGIN_URL);	
		return mav;
	}
	@RequestMapping("/mainWork")
	@ResponseBody
	public String mainWork(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String name=jobj.getString("account");
		String password=jobj.getString("password");
		String captcha=jobj.getString("captcha");
		JSONObject jsonObject=new JSONObject();
		if(!PatchcaServlet.validate(request.getSession().getId(), captcha)){
			jsonObject.put("success", false);
			jsonObject.put("error", "1");
			return jsonObject.toString();
		}
		int check=systemLoginDao.check(name, password);
	    if(check==2){//密码错误
	    	jsonObject.put("success", false);
			jsonObject.put("error", "2");
			return jsonObject.toString();
	    }else if(check==3){//账号错误
	    	jsonObject.put("success", false);
			jsonObject.put("error", "3");
			return jsonObject.toString();
	    }
		Map map=systemLoginDao.getMsg(name);
		request.getSession().setMaxInactiveInterval(60*60);
		request.getSession().setAttribute("name", systemLoginDao.getTypeName(map.get("actorid").toString()));
		request.getSession().setAttribute("actorid", map.get("actorid").toString());
		request.getSession().setAttribute("account", map.get("account").toString());
		request.getSession().setAttribute("districtId", map.get("districtId").toString());	
		request.getSession().setAttribute("id", map.get("id").toString());	
		request.getSession().setAttribute("type", map.get("type").toString());	
		jsonObject.put("success", true);
		return jsonObject.toString();
	}
	@RequestMapping("/enter")
	public ModelAndView enter(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		long type=Long.valueOf((String) request.getSession().getAttribute("type")) ;
		if(type==1){
			mav.setViewName(MAIN_URL);	
		}else{
			mav.setViewName("");	
		}
		return mav;
	}
}
