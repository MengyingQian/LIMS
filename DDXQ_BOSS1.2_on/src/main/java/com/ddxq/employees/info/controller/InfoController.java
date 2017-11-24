package com.ddxq.employees.info.controller;

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

import com.ddxq.admin.admininfo.controller.AdminInfoController;
import com.ddxq.employees.info.service.InfoService;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年12月12日 上午10:46:44 
* 类说明 
*/
@Controller
@RequestMapping("/ddxq/employees/info")
public class InfoController {
	
	private static final String USER_URL2 = "/eminfos/myinfo";
	private static final String USER_URL = "/eminfos/changepwd";
	Logger log = LoggerFactory.getLogger(AdminInfoController.class);
	@Autowired
	private InfoService infoservice;
	
	@RequestMapping("/myinfo")
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String actorid = (String) request.getSession().getAttribute("actorid");
		String id = (String) request.getSession().getAttribute("id");
		mav.addObject("actorid", actorid);
		mav.addObject("id", id);
		mav.setViewName(USER_URL2);	
		return mav;
	}
	@RequestMapping("/getinfo")
	@ResponseBody
	public JSONObject search(HttpServletRequest request, HttpServletResponse response) {	
		String id = (String) request.getSession().getAttribute("id");
		JSONObject object=infoservice.getinfo(id);
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
		retJobj.put("success",infoservice.editInfo(map));
		retJobj.put("url", "/ddxq/employees/info/getinfo");
		return retJobj.toString();
		
	}
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
		retJobj.put("success",infoservice.changepwd(request.getSession().getAttribute("id").toString(),pre , pwd));
		return retJobj.toString();
	}
}
 