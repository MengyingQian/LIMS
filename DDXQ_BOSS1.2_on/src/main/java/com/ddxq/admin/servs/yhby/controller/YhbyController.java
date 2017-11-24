package com.ddxq.admin.servs.yhby.controller;

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

import com.ddxq.admin.servs.yhby.service.YhbyService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/admin/servs/yhby")
public class YhbyController {
	@Autowired
	YhbyService yhbyService;
	Logger log = LoggerFactory.getLogger(YhbyController.class);  
	@RequestMapping("/show")
	public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		String districtId=request.getSession().getAttribute("districtId").toString();
		mav.addObject("districtId", districtId);
		mav.setViewName("/admin/servs/yhby");
		return mav;		
	}
	@RequestMapping("/insertInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String districtId=jobj.getString("districtId");
		String score=jobj.getString("score");
		String servs=jobj.getString("servs");
		String phone=jobj.getString("phone");
		String address=jobj.getString("address");
		String link=jobj.getString("link");
		String caption=jobj.getString("caption");
		String seller=jobj.getString("seller");
		String created=new Date().getTime()+"";
		String updated=new Date().getTime()+"";
		String publisher=jobj.getString("publisher");
		String description = jobj.getString("description");
		String redisKey="districtid:"+districtId+":servs:"+servs;
	
		
		Map map=new HashMap();
		map.put("publisher", publisher);
		map.put("seller", seller);
		map.put("score", score);
		map.put("caption", caption);
		map.put("address", address);
		map.put("created", created);
		map.put("updated",updated);
		map.put("phone", phone);
		map.put("link", link);
		map.put("description", description);
		yhbyService.insertInfo(redisKey, map);
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",true);
		retJobj.put("districtId",districtId);
		retJobj.put("servs",servs);
		retJobj.put("url", "/ddxq/admin/servs/yhby/searchData?districtIdFrom="+districtId+"&servs="+servs);
		return retJobj.toString();
		
	}
	@RequestMapping("/editInfo")
	@ResponseBody
	public String editNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		String districtId=jobj.getString("districtId");
		String servs=jobj.getString("servs");
		String phone=jobj.getString("phone");
		String address=jobj.getString("address");
		String link=jobj.getString("link");
		String caption=jobj.getString("caption");
		String seller=jobj.getString("seller");
		String created=new Date().getTime()+"";
		String updated=new Date().getTime()+"";
		String publisher=jobj.getString("publisher");
		String docid=jobj.getString("docid");
		String description = jobj.getString("description");
		
		Map map=new HashMap();
		map.put("publisher", publisher);
		map.put("seller", seller);
		map.put("caption", caption);
		map.put("address", address);
		map.put("created", created);
		map.put("updated",updated);
		map.put("phone", phone);
		map.put("link", link);
		map.put("description", description);
		yhbyService.editInfo(docid, map);
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",true);
		retJobj.put("url", "/ddxq/admin/servs/yhby/searchData?districtIdFrom="+districtId+"&servs="+servs);
		return retJobj.toString();
	}

	@RequestMapping("/searchData")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String districtId=request.getParameter("districtIdFrom").trim();
		String servs=request.getParameter("servs").trim();	
		
		JSONObject object=yhbyService.getDataFromDistrictIdAndColumn(districtId, servs);
		System.out.println(object.toString());
		return object.toString();
	}
	@RequestMapping("/removeData")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		yhbyService.removeInfo(jobj);
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",true);
		retJobj.put("url", "/ddxq/admin/servs/yhby/searchData?districtIdFrom="+jobj.getJSONObject("0").getInt("districtId")+"&servs="+jobj.getJSONObject("0").getString("servs"));
		return retJobj.toString();
	}
}
