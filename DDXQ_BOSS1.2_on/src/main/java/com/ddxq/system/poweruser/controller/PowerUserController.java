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
		String title=jobj.getString("title");
		String author=jobj.getString("author");
		String publish_year=jobj.getString("publish_year");
		String publish_month_date=jobj.getString("publish_month_date");
		String department=jobj.getString("department");
		String url=jobj.getString("url");
		/*String privmod=jobj.getString("privmod");
		String privcate=jobj.getString("privcate");
		String level=jobj.getString("level");*/
		String status=jobj.getString("status");
//		String memo=jobj.getString("memo");
		String create_time=new Date().getTime()+"";
		String update_time=new Date().getTime()+"";
		
		Map map=new HashMap();
		map.put("title", title);
		map.put("author", author);
		map.put("publish_year", publish_year);
		map.put("publish_month_date",publish_month_date);
		map.put("department",department);
		map.put("url", url);
//		map.put("privmod", privmod);
//		map.put("privcate",privcate);
//		map.put("level", level);
		map.put("status", status);
//		map.put("memo", memo);
		
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",userService.insertInfo(map));
		retJobj.put("publish_year",publish_year);
		retJobj.put("url", "/ddxq/system/poweruser/search?actorid="+publish_year);
		return retJobj.toString();
		
	}
	@RequestMapping("/chengyuan")
	public ModelAndView chengyuan(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/chengyuan");	
		return mav;
	}
	@RequestMapping("/chengguo")
	public ModelAndView chengguo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/chengguo");	
		return mav;
	}
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index");	
		return mav;
	}
	@RequestMapping("/yjfx")
	public ModelAndView yjfx(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/yjfx");	
		return mav;
	}
	@RequestMapping("/xwzx")
	public ModelAndView xwzx(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/xwzx");	
		return mav;
	}
	@RequestMapping("/search")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String actorid=request.getParameter("actorid").trim();		
		JSONObject object=userService.getUserViaActorid(actorid);
		System.out.println(object.toString());
		//System.out.println(actorid);
		return object.toString();
	}
	@RequestMapping("/searchyear")
	@ResponseBody
	public String searchyear(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		int year=jobj.getInt("year");		
		JSONObject object=userService.getUserViaActorid(year+"");
		System.out.println(object.toString());
		object.put("success",true);
		return object.toString();
	}
	@RequestMapping("/searchstatus")
	@ResponseBody
	public String searchstatus(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String status=jobj.getString("status");			
		JSONObject object=userService.getUserStatus(status);
		System.out.println(object.toString());
		object.put("success",true);
		return object.toString();
	}
	@RequestMapping("/searchposition")
	@ResponseBody
	public String searchposition(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String position=jobj.getString("position");		
		JSONObject object=userService.getUserPosition(position);
		System.out.println(object.toString());
		object.put("success",true);
		return object.toString();
	}
	@RequestMapping("/edit")
	@ResponseBody
	public String editNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String paper_id=jobj.getString("paper_id");
		String title=jobj.getString("title");
		String author=jobj.getString("author");
		String publish_year=jobj.getString("publish_year");
		String publish_month_date=jobj.getString("publish_month_date");
		String department=jobj.getString("department");
		String url=jobj.getString("url");
//		String privmod=jobj.getString("privmod");
//		String privcate=jobj.getString("privcate");
//		String level=jobj.getString("level");
		/*if(level.equals("")){
			level="0";
		}*/
		String status=jobj.getString("status");
//		String memo=jobj.getString("memo");
		Timestamp timestamp=new Timestamp(new Date().getTime());
		Map map=new HashMap();
		map.put("paper_id", paper_id);
		map.put("title", title);
		map.put("author", author);
		map.put("publish_year", publish_year);
		map.put("publish_month_date", publish_month_date);
		map.put("department", department);
		map.put("url", url);
		/*map.put("privmod", privmod);
		map.put("privcate",privcate);
		map.put("level", level);*/
		map.put("status", status);
//		map.put("memo", memo);
//		map.put("uodated", timestamp);
		map.put("update_time", timestamp);
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",userService.editInfo(map));
		retJobj.put("publish_year",publish_year);
		retJobj.put("url", "/ddxq/system/poweruser/search?actorid="+publish_year);
		return retJobj.toString();
		
	}
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String paper_id=jobj.getString("paper_id").trim();
		String publish_year=jobj.getString("publish_year").trim();
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",userService.removeInfo(paper_id));
		retJobj.put("publish_year",publish_year);
		retJobj.put("url", "/ddxq/system/poweruser/search?actorid="+publish_year);
		return retJobj.toString();
	}
}
