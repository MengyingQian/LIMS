package com.ddxq.system.employees.controller;

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

import com.ddxq.employee.vrcode.service.GrzxService;
import com.ddxq.system.employees.service.EmployeesService;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/ddxq/system/employees")
public class EmployeesController {
	private static final String USER_URL = "/system/distmanuser/employees";
	Logger log = LoggerFactory.getLogger(EmployeesController.class);
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private GrzxService grzxService;
	@RequestMapping("/show")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
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
			retJobj.put("url", "/ddxq/system/distman/search?employeeActor=" + map.get("employeeActor") + "$districtId=" + jobj.getString("districtId"));
			return retJobj.toString();
		}
		retJobj.put("success",employeesService.insertInfo(map));
		retJobj.put("employeeActor",map.get("employeeActor"));
		retJobj.put("url", "/ddxq/system/distman/search?employeeActor=" + map.get("employeeActor") + "$districtId=" + jobj.getString("districtId"));
		return retJobj.toString();
		
	}
	@RequestMapping("/search")
	@ResponseBody
	public JSONObject search(HttpServletRequest request, HttpServletResponse response) {
		String employeeActor=request.getParameter("employeeActor").trim();	
		String districtId =  request.getParameter("districtId").trim();
		System.out.println("employeeActor: " + employeeActor);
		System.out.println("districtId: " + districtId);
		JSONObject object=employeesService.getUserViaActorid(districtId, employeeActor);
		return object;
	}
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj) {
		String account=jobj.getString("account").trim();
		String actorid=jobj.getString("actorid").trim();
		String districtId = jobj.getString("districtId").trim();
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",employeesService.removeInfo(account));
		retJobj.put("actorid",actorid);
		retJobj.put("url", "/ddxq/system/distman/search?employeeActor=" + actorid + "$districtId=" + districtId);
		return retJobj.toString();
	}
	@RequestMapping("/editInfo")
	@ResponseBody
	public String editNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){
		Map map=new HashMap();
		String actorid=jobj.getString("employeeActor").trim();
		 Iterator it=jobj.keys();		 
		 while(it.hasNext()){
			  String str = it.next().toString();
			  map.put(str, jobj.getString(str));
			 }
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",employeesService.editInfo(map));
		retJobj.put("url", "/ddxq/system/distman/search?employeeActor=" + actorid + "$districtId=" + jobj.getString("employeeId"));
		return retJobj.toString();
		
	}
}
