package com.ddxq.employee.guanliyuanshop.controller;

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

import com.ddxq.employee.guanliyuanshop.service.GuanLiYuanShopService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/admin/shop")
public class GuanLiYuanShopController {
	private static final String SHOP= "/admin/sellermanager/shop";
	Logger log = LoggerFactory.getLogger(GuanLiYuanShopController.class);
	@Autowired
	private GuanLiYuanShopService shopservice;
	
	@RequestMapping("/show")
	@ResponseBody
	public JSONObject search(HttpServletRequest request, HttpServletResponse response) {	
		String typeid=request.getParameter("typeid");
		String districtId=request.getSession().getAttribute("districtId").toString();
		JSONObject object=shopservice.getshopinfo(districtId, typeid);
		return object;
	}
	@RequestMapping("/showlist")
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		// typeid 1为 小区全部商品  2 为今日优惠
		String actor=request.getParameter("typeid"); 
		mav.addObject("typeid",actor);
		mav.setViewName(SHOP);	
		return mav;
	}
	@RequestMapping("/insertInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		JSONObject retJobj = new JSONObject();
		String districtId = jobj.getString("districtId");
		String docid = jobj.getString("docid");
		String typeid = jobj.getString("typeid");
		retJobj.put("success",shopservice.insertinfo(districtId, docid));
		retJobj.put("url", "/ddxq/admin/shop/showlist?typeid=" + typeid);
		return retJobj.toString();
	}
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		JSONObject retJobj = new JSONObject();
		String districtId = jobj.getString("districtId");
		String docid = jobj.getString("docid");
		String typeid = jobj.getString("typeid");
		retJobj.put("success",shopservice.removeInfo(districtId, docid));
		retJobj.put("url", "/ddxq/admin/shop/showlist?typeid=" + typeid);
		return retJobj.toString();
	}
}
