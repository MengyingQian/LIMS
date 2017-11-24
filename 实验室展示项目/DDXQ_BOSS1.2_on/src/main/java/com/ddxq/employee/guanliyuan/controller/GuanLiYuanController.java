package com.ddxq.employee.guanliyuan.controller;

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

import com.ddxq.employee.guanliyuan.dao.GuanLiYuanDao;
import com.ddxq.employee.guanliyuan.service.GuanLiYuanService;
import com.ddxq.employee.vrcode.service.GrzxService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/admin/userman")
public class GuanLiYuanController {
	
	
	private static final String USER_URL = "/admin/usermanager/show";
	private static final String SERVICES_STATUS = "/admin/sellermanager/shopstatus";
	Logger log = LoggerFactory.getLogger(GuanLiYuanController.class);
	
	@Autowired
	private GuanLiYuanService guanliyuanservice;
	@Autowired
	private GrzxService grzxService;
	
	@RequestMapping("/show")
	@ResponseBody
	public JSONObject search(HttpServletRequest request, HttpServletResponse response) {	
		String actor=request.getParameter("actorid");
		String districtId=request.getSession().getAttribute("districtId").toString();
		JSONObject object=guanliyuanservice.getUserViaActor(districtId, actor);
		return object;
	}
	@RequestMapping("/user")
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String actor=request.getParameter("actorid");
		String districtId=request.getSession().getAttribute("districtId").toString();
		mav.addObject("actorid",actor);
		mav.addObject("districtId",districtId);
		mav.setViewName(USER_URL);	
		return mav;
	}
	@RequestMapping("/insertInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		Map map=new HashMap();
		 Iterator it=jobj.keys();		 
		 while(it.hasNext()){
			  String str = it.next().toString();
			  map.put(str, jobj.getString(str));
			 }
		JSONObject retJobj = new JSONObject();
		if(! grzxService.yanzheng(jobj)){
			retJobj.put("success", false);
			retJobj.put("mess", "验证码错误");
			retJobj.put("actorid", map.get("employeeActor"));
			retJobj.put("url", "/ddxq/admin/userman/show?actorid="+map.get("employeeActor"));
			return retJobj.toString();
		}
		Map result = guanliyuanservice.insertInfo(map);
		retJobj.put("success", (boolean) result.get("success"));
		retJobj.put("mess", result.get("mess"));
		retJobj.put("actorid", map.get("employeeActor"));
		retJobj.put("url", "/ddxq/admin/userman/show?actorid="+map.get("employeeActor"));
		return retJobj.toString();
		
	}
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String account=jobj.getString("account").trim();
		String actorid=jobj.getString("actorid").trim();
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",guanliyuanservice.removeInfo(account));
		retJobj.put("actorid",actorid);
		retJobj.put("url", "/ddxq/admin/userman/show?actorid="+actorid);
		return retJobj.toString();
	}
	@RequestMapping("/getcert")
	@ResponseBody
	public String getcert(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",true);
		retJobj.put("certcode",guanliyuanservice.getcert(jobj.getString("id")));
		retJobj.put("url", "/ddxq/admin/userman/show?actorid="+jobj.getString("actorid"));
		return retJobj.toString();
		
	}
	@RequestMapping("/servicesstatus")
	public ModelAndView changeStatus(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		String districtId = request.getSession().getAttribute("districtId").toString();
		String type = request.getParameter("type");
		boolean status = false;
		if(type.equals("1")){
			status = guanliyuanservice.getServicesStatus(districtId, 1);
		} else if (type.equals("2")) {
			status = guanliyuanservice.getServicesStatus(districtId, 2);
		} else if (type.equals("3")) {
			status = guanliyuanservice.getServicesStatus(districtId, 3);
		}else if (type.equals("4")) {
			status = guanliyuanservice.getServicesStatus(districtId, 4);
		}else if (type.equals("5")) {
			status = guanliyuanservice.getServicesStatus(districtId, 5);
		}
		if (status){
			modelAndView.addObject("status", "开启");
		} else {
			modelAndView.addObject("status", "关闭");
		}
		modelAndView.addObject("districtId", districtId);
		modelAndView.addObject("type", type);
		modelAndView.setViewName(SERVICES_STATUS);
		return modelAndView;
	}
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",true);
		boolean res = guanliyuanservice.changServicesStatus(jobj);
		if (res){
			retJobj.put("status", "开启");
		} else {
			retJobj.put("status", "关闭");
		}
		return retJobj.toString();
	}
}
