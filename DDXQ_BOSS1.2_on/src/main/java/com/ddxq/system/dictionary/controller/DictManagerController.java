package com.ddxq.system.dictionary.controller;


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

import com.ddxq.system.dictionary.dao.DictManagerDao;
import com.ddxq.system.dictionary.service.DictManagerService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/system/dictionary")
public class DictManagerController {
	
	@Autowired
	private DictManagerService dictmanager;
	
	private static final String USER_URL = "/system/dictionary/menu";
	Logger log = LoggerFactory.getLogger(DictManagerController.class);

	@RequestMapping("/showmenu")
	public ModelAndView menu(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(USER_URL);	
		return mav;
	}
	
	@RequestMapping("/getmenu")
	@ResponseBody
	public JSONObject getMenu(HttpServletRequest request, HttpServletResponse response){
		JSONObject retJobj = dictmanager.getMenu();
		return retJobj;
	}
	
	@RequestMapping("/insertInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",dictmanager.insertInfo(jobj));
		retJobj.put("url", "/ddxq/system/dictionary/getmenu");
		return retJobj.toString();
	}
	
	@RequestMapping("/editInfo")
	@ResponseBody
	public String edit(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		obj.put("seller", (String)request.getSession().getAttribute("id"));
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",dictmanager.updateInfo(obj));
		retJobj.put("url", "/ddxq/system/dictionary/getmenu");
		return retJobj.toString();
	}
	
	@RequestMapping("/removeInfo")
	@ResponseBody
	public String reomove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",dictmanager.removeInfo(obj));
		retJobj.put("url", "/ddxq/system/dictionary/getmenu");
		return retJobj.toString();
	}
}
