package com.ddxq.system.poweruser.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
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

import com.ddxq.system.poweruser.dao.PowerUserDao;
import com.ddxq.system.poweruser.service.PowerUserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/system/poweruser")
public class PowerUserController {
	private static final String USER_URL = "/system/poweruser/show";
	Logger log = LoggerFactory.getLogger(PowerUserController.class);
	@Autowired
	PowerUserService userService;
	@RequestMapping("/show")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(USER_URL);	
		return mav;
	}
	@RequestMapping("/insertInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String account=jobj.getString("account");
		String password=jobj.getString("password");
		String actorid=jobj.getString("actorid");
		String mobile=jobj.getString("mobile");
		String name=jobj.getString("name");
		String ims=jobj.getString("ims");
		String privmod=jobj.getString("privmod");
		String privcate=jobj.getString("privcate");
		String level=jobj.getString("level");
		String status=jobj.getString("status");
		String memo=jobj.getString("memo");
		String created=new Date().getTime()+"";
		String updated=new Date().getTime()+"";
		
		Map map=new HashMap();
		map.put("account", account);
		map.put("password", password);
		map.put("actorid", actorid);
		map.put("mobile", mobile);
		map.put("name", name);
		map.put("ims", ims);
		map.put("privmod", privmod);
		map.put("privcate",privcate);
		map.put("level", level);
		map.put("status", status);
		map.put("memo", memo);
		
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",userService.insertInfo(map));
		retJobj.put("actorid",actorid);
		retJobj.put("url", "/ddxq/system/poweruser/search?actorid="+actorid);
		return retJobj.toString();
		
	}
	@RequestMapping("/search")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String actorid=request.getParameter("actorid").trim();		
		JSONObject object=userService.getUserViaActorid(actorid);
		System.out.println(object.toString());
		return object.toString();
	}
	@RequestMapping("/edit")
	@ResponseBody
	public String editNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String account=jobj.getString("account");
		String password=jobj.getString("password");
		String actorid=jobj.getString("actorid");
		String mobile=jobj.getString("mobile");
		String name=jobj.getString("name");
		String ims=jobj.getString("ims");
		String privmod=jobj.getString("privmod");
		String privcate=jobj.getString("privcate");
		String level=jobj.getString("level");
		if(level.equals("")){
			level="0";
		}
		String status=jobj.getString("status");
		String memo=jobj.getString("memo");
		Timestamp timestamp=new Timestamp(new Date().getTime());
		Map map=new HashMap();
		map.put("account", account);
		map.put("password", password);
		map.put("actorid", actorid);
		map.put("mobile", mobile);
		map.put("name", name);
		map.put("ims", ims);
		map.put("privmod", privmod);
		map.put("privcate",privcate);
		map.put("level", level);
		map.put("status", status);
		map.put("memo", memo);
		map.put("uodated", timestamp);
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",userService.editInfo(map));
		retJobj.put("actorid",actorid);
		retJobj.put("url", "/ddxq/system/poweruser/search?actorid="+actorid);
		return retJobj.toString();
		
	}
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String account=jobj.getString("account").trim();
		String actorid=jobj.getString("actorid").trim();
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",userService.removeInfo(account));
		retJobj.put("actorid",actorid);
		retJobj.put("url", "/ddxq/system/poweruser/search?actorid="+actorid);
		return retJobj.toString();
	}
}
