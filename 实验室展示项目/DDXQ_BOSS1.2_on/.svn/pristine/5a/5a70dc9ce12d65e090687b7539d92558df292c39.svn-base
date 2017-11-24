package com.ddxq.admin.xxl.xqjs.controller;

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
import com.ddxq.admin.xxl.xqjs.dao.XqjsDao;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/admin/xxl/xqjs")
public class XqjsController {
	private static final String USER_URL = "/admin/noticemanage/xqjs";
	Logger log = LoggerFactory.getLogger(XqjsController.class);

	@Autowired
	XqjsDao xqjsDao;
	@RequestMapping("/show")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView();
		String districtId=request.getSession().getAttribute("districtId").toString();
		modelAndView.setViewName(USER_URL);
		modelAndView.addObject("districtId", districtId);
		return modelAndView;
	}
	@RequestMapping("/add")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){    
		JSONObject retJobj = new JSONObject();
		xqjsDao.add(jobj.getString("districtId"), jobj.getString("type"), jobj.getString("content"));
		retJobj.put("success",true);
		return retJobj.toString();
	}
}
