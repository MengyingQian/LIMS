package com.ddxq.system.weixinmanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.system.weixinmanager.service.WeiXinManagerService;

import net.sf.json.JSONObject;
/**
 * 粉丝管理
 * @author asus
 *
 */
@Controller
@RequestMapping("/ddxq/system/weixinmanager")
public class WeiXinManagerController {
	private static final String USER_URL = "/system/weixinmanager/showfans";
	@Autowired
	WeiXinManagerService weiXinManagerService;
	Logger log = LoggerFactory.getLogger(WeiXinManagerController.class);
	@RequestMapping("/show")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(USER_URL);	
		return mav;
	}
	@RequestMapping("/showAll")
	@ResponseBody
	public String showQRcodeGrid(HttpServletRequest request, HttpServletResponse response){	
		JSONObject retJobj=weiXinManagerService.showAll();
		return retJobj.toString();
	}
	@RequestMapping("/search")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String id =request.getParameter("districtId");
		System.out.println(id);
		int dist=0;
		JSONObject retJobj=null;
		if(id.equals("")){
			 retJobj=weiXinManagerService.showAll();
		}else{
			dist=Integer.parseInt(id);
			retJobj=weiXinManagerService.showByDistrictId(dist);
		}
		//System.out.println(object);
		return retJobj.toString();
	}
}
