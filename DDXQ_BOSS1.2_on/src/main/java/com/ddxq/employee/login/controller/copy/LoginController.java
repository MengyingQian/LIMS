package com.ddxq.employee.login.controller.copy;

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
import com.ddxq.employee.login.service.copy.LoginService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/boss/loginManage")
public class LoginController {
	private static final String LOGIN_URL = "/login";
	private static final String INDEX_URL = "/index";
	private static final String MAINWORK_URL_EMPLOYEE = "/mainWorkemployee";
	private static final String WELCOME_URL = "/welcome";
	private static final String MAININDEX_URL = "/system/poweruser/show";
	Logger log = LoggerFactory.getLogger(LoginController.class);  
	@Autowired
	private LoginService loginService;
	@Autowired
	private HostConfig hostConfig;

	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		ArrayList<String>list=new ArrayList<>();
		mav.setViewName(LOGIN_URL);	
		return mav;
	}
	@RequestMapping("/loginout")
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(LOGIN_URL);	
		request.getSession().removeAttribute("actorid");
		request.getSession().removeAttribute("account");
		request.getSession().removeAttribute("districtId");
		request.getSession().removeAttribute("name");
		request.getSession().removeAttribute("id");
		request.getSession().removeAttribute("type");
		return mav;
	}
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(INDEX_URL);	
		return mav;
	}
	
	/*@RequestMapping("/mainWork")
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
		int check=loginService.check(name, password);
	    if(check==2){//密码错误
	    	jsonObject.put("success", false);
			jsonObject.put("error", "2");
			return jsonObject.toString();
	    }else if(check==3){//账号错误
	    	jsonObject.put("success", false);
			jsonObject.put("error", "3");
			return jsonObject.toString();
	    }else if(check==4){//管理员停用
	    	jsonObject.put("success", false);
			jsonObject.put("error", "4");
			return jsonObject.toString();
	    }
	    else if(check==5){//普通用户停用
	    	jsonObject.put("success", false);
			jsonObject.put("error", "5");
			return jsonObject.toString();
	    }
		Map map=loginService.getMsg(name, password);
		request.getSession().setMaxInactiveInterval(60*60);
		request.getSession().setAttribute("name", loginService.getTypeName(map.get("actorid").toString()));
		request.getSession().setAttribute("actorid", map.get("actorid").toString());
		request.getSession().setAttribute("account", map.get("account").toString());
		request.getSession().setAttribute("districtId", map.get("districtId").toString());	
		request.getSession().setAttribute("id", map.get("id").toString());	
		request.getSession().setAttribute("type", map.get("type").toString());	
		request.getSession().setAttribute("xiaoquname", map.get("name").toString());	
		jsonObject.put("success", true);
		return jsonObject.toString();
	}*/
	@RequestMapping("/enter")
	public ModelAndView enter(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		long type=Long.valueOf((String) request.getSession().getAttribute("type")) ;
		if(type==2){
			mav.setViewName(MAINWORK_URL_EMPLOYEE);	
		}else{
			mav.setViewName("");	
		}
		return mav;
	}
	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(WELCOME_URL);	
		return mav;
	}
	@RequestMapping("/mainindex")
	public ModelAndView mainindex(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
	/*	long type=Long.valueOf((String) request.getSession().getAttribute("type")) ;
		if(type==2){*/
			mav.setViewName(MAININDEX_URL);	
	/*	}else{
			mav.setViewName("");	
		}*/
		return mav;
	}
	@RequestMapping("/yanzheng")
	@ResponseBody
	public String yanzheng(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String name=jobj.getString("account");
		String password=jobj.getString("password");
		String captcha=jobj.getString("captcha");
		JSONObject jsonObject=new JSONObject();
		
		String na="123";
		String pa="123";
    /*	System.out.println(name.equals(na));
    	System.out.println("2:" + name);
    	System.out.println(password == pa);*/
		if(name.equals(na)  && password.equals(pa)){
			
			jsonObject.put("success", true);
			return jsonObject.toString();
		}
		
		else if(name.equals(na) && (!password.equals(pa))){//密码错误
	    	jsonObject.put("success", false);

			jsonObject.put("error", "2");
			return jsonObject.toString();
	    }else if(!name.equals(na)){//账号错误
	    /*	jsonObject.put("success", false);*/
//	    	System.out.println(name);
			/*jsonObject.put("success", true);*/
	    	jsonObject.put("error", "3");
			return jsonObject.toString();
	    }
		return jsonObject.toString();
	}
	@RequestMapping("/huanying")
	public ModelAndView huanying(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/formup");	
		return mav;
	}
	
	@RequestMapping("/jieshou")
	@ResponseBody
	public String jieshou(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String name=jobj.getString("account");
		String password=jobj.getString("password");
		System.out.println(name);
		System.out.println(password);
		
		
		JSONObject jsonObject=new JSONObject();
		
		Map map=loginService.getMsg(name, password);
	/*	request.getSession().setMaxInactiveInterval(60*60);
		request.getSession().setAttribute("name", loginService.getTypeName(map.get("actorid").toString()));
		request.getSession().setAttribute("actorid", map.get("actorid").toString());
		request.getSession().setAttribute("account", map.get("account").toString());
		request.getSession().setAttribute("districtId", map.get("districtId").toString());	
		request.getSession().setAttribute("id", map.get("id").toString());	
		request.getSession().setAttribute("type", map.get("type").toString());	
		request.getSession().setAttribute("xiaoquname", map.get("name").toString());	*/
		jsonObject.put("success", true);
		jsonObject.put("sex", map.get("sex").toString());
		//jsonObject.put("map", map);
		System.out.println(map);
		return jsonObject.toString();
	
	   
	}
	
}
