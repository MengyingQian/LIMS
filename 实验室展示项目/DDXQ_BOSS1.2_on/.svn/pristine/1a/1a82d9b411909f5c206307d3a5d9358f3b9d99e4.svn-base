package com.ddxq.admin.weixinmanage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.system.weixinmanager.controller.WeiXinManagerController;
import com.ddxq.system.weixinmanager.service.WeiXinManagerService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/ddxq/admin/weixinmanage")
public class WeiXinManageController {
	private static final String USER_URL = "/admin/weixinmanager/showfans";
	@Autowired
	WeiXinManagerService weiXinManagerService;
	Logger log = LoggerFactory.getLogger(WeiXinManagerController.class);
	@RequestMapping("/show")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String id =request.getSession().getAttribute("districtId").toString();
		System.out.println(id);
		mav.addObject("id", id);
		mav.setViewName(USER_URL);	
		return mav;
	}
	@RequestMapping("/search")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String id =request.getSession().getAttribute("districtId").toString();
		System.out.println(id);
		int dist=0;
		JSONObject retJobj=null;		
		dist=Integer.parseInt(id);
		retJobj=weiXinManagerService.showByDistrictId(dist);

		//System.out.println(object);
		return retJobj.toString();
	}
}
