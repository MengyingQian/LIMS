package com.ddxq.employee.kuaidi.controller;

import java.io.IOException;
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

import com.ddxq.employee.kuaidi.service.KuaiDiService;
import com.ddxq.weixin.base.fastdfsfile.service.uploadFileService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/employees/daishou")
public class KuaiDiController {
	
	@Autowired
	private KuaiDiService kuaiDiService;
	
	Logger log = LoggerFactory.getLogger(KuaiDiController.class);  
	public static final String MESSAGE ="/daishou/message";
	public static final String MUBAN ="/daishou/muban";
	
	
	@RequestMapping("/message")
	public ModelAndView addMessage(HttpServletRequest request, HttpServletResponse response){
		String kuadiid = (String)request.getSession().getAttribute("id");
		ModelAndView mav=new ModelAndView();
		Map kuaidi = kuaiDiService.getinfo(kuadiid);
		mav.addObject("kuaidi", kuaidi);
		mav.setViewName(MESSAGE);
		return mav;	
	}
	@RequestMapping("/sendmessage")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		JSONObject retJobj = new JSONObject();
		boolean result = kuaiDiService.SendMessage(jobj);
		retJobj.put("success", result);
		return retJobj.toString();
	}
	
	@RequestMapping("/muban")
	public ModelAndView addMuBan(HttpServletRequest request, HttpServletResponse response){
		String sellerID = (String)request.getSession().getAttribute("id");
		ModelAndView mav=new ModelAndView();
		mav.setViewName(MUBAN);
		return mav;	
		
	}

}
