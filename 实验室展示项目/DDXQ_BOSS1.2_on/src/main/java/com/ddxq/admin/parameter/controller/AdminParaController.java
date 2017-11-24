package com.ddxq.admin.parameter.controller;

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

import com.ddxq.system.parameter.controller.ParameterController;
import com.ddxq.system.parameter.service.ParameterService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/admin/parameter")
public class AdminParaController {
	private static Logger log = LoggerFactory.getLogger( AdminParaController.class);
	@Autowired
	ParameterService parameterService;
	@RequestMapping("/show")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/parameter/parameter");	
		return mav;
	}
	@RequestMapping("/add")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String rediskey=jobj.getString("rediskey").trim();
		String value=jobj.getString("value").trim();
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",parameterService.addParameter(rediskey, value));
		return retJobj.toString();
	}
}
