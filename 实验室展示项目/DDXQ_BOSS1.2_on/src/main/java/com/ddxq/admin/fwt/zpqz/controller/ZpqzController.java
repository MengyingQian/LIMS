package com.ddxq.admin.fwt.zpqz.controller;

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

import com.ddxq.admin.fwt.fwzs.controller.FwzsController;
import com.ddxq.admin.fwt.fwzs.service.FwzsService;
import com.ddxq.admin.fwt.zpqz.service.ZpqzService;
import com.ddxq.weixin.base.fastdfsfile.service.uploadFileService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/ddxq/admin/zpqz")
public class ZpqzController {
	@Autowired
	private ZpqzService zpqzService;
	Logger log = LoggerFactory.getLogger(ZpqzController.class);  
	public static final String COMMODITIES_SHOW ="/admin/servs/zpqz";
	@RequestMapping("/show")
	public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView();		
		mav.setViewName(COMMODITIES_SHOW);
		return mav;	
		
	}
	@RequestMapping("/getInfo")
	@ResponseBody
	public JSONObject getinfo(HttpServletRequest request, HttpServletResponse response){
		String districtId = (String)request.getSession().getAttribute("districtId");
		JSONObject object=zpqzService.getInfo(districtId);
		System.out.println("object："+object);
		return object;
	}
	
	@RequestMapping("/insertInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		obj.put("districtId", (String)request.getSession().getAttribute("districtId"));
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",zpqzService.insertInfo(obj));
		retJobj.put("url", "/ddxq/admin/zpqz/getInfo");
		return retJobj.toString();
	}
	@RequestMapping("/editInfo")
	@ResponseBody
	public String edit(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		JSONObject retJobj = new JSONObject();
		String districtId = (String)request.getSession().getAttribute("districtId");
		String url="";
		String show=obj.getString("show");
		if(!show.equals("all")){
			url ="/ddxq/admin/fwzs/searchData?type="+show;
		}else{
			url ="/ddxq/admin/fwzs/getInfo";
			
		}
		retJobj.put("success",zpqzService.updateInfo(districtId, obj));
		retJobj.put("url", "/ddxq/admin/zpqz/getInfo");
		return retJobj.toString();
	}
	@RequestMapping("/removeData")
	@ResponseBody
	public String reomove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		JSONObject retJobj = new JSONObject();
		String districtId = (String)request.getSession().getAttribute("districtId");
		String show=obj.getString("show");
		String url="";
		if(!show.equals("all")){
			url ="/ddxq/admin/zpqz/searchData?type="+show;
		}else{
			url ="/ddxq/admin/zpqz/getInfo";
			
		}
		retJobj.put("success",zpqzService.removeInfo(districtId,obj.getString("type"),obj.getString("docid"),obj));
		retJobj.put("url", url);
		return retJobj.toString();
	}
	@RequestMapping("/searchData")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String districtId = (String)request.getSession().getAttribute("districtId");
		String type=request.getParameter("type");
		JSONObject object=zpqzService.getInfo(districtId, type);
		return object.toString();
	}
}
