package com.ddxq.system.showdistrictinfo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.util.StringRangeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.system.showdistrictinfo.service.ShowDistrictInfoService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/system/districtManage")
public class ShowDistrictInfoController {
	private static final String DISTRICT_VIEW_URL = "/system/district/showDistrictInfo";
	private static final String DRAW_VIEW_URL = "/system/district/borderline";
	@Autowired
	ShowDistrictInfoService showDistrictInfoService;
	
	Logger log = LoggerFactory.getLogger(ShowDistrictInfoController.class);
	@RequestMapping("/showDistrictInfo")
	public ModelAndView showDistrictInfo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(DISTRICT_VIEW_URL);	
		return mav;
	}
	@RequestMapping("/showDistrictGrid")
	@ResponseBody
	public String showQRcodeGrid(HttpServletRequest request, HttpServletResponse response){	
		JSONObject retJobj=showDistrictInfoService.showAll();
		return retJobj.toString();
	}
	@RequestMapping("/addDistrictInfo")
	@ResponseBody
	public String addDistrictInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){    
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",showDistrictInfoService.addDistrictInfo(jobj));
		retJobj.put("url", "/ddxq/system/districtManage/showDistrictGrid");
		return retJobj.toString();
	}
	@RequestMapping("/editDistrictInfo")
	@ResponseBody
	public String editDistrictInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){    
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",showDistrictInfoService.editDistrictInfo(jobj));
		retJobj.put("url", "/ddxq/system/districtManage/showDistrictGrid");
		return retJobj.toString();
	}
	@RequestMapping("/searchDistrictInfo")
	@ResponseBody
	public String sarchDistrictInfo(HttpServletRequest request, HttpServletResponse response){
		String from=request.getParameter("districtIdFrom").trim();
		String to=request.getParameter("districtIdTo").trim();
		int start,end;
		if (from.equals("")) {
			start = 0;
		} else {
			start = Integer.parseInt(from);
		}
		if (to.equals("")) {
			end = start+100;
		} else {
			end = Integer.parseInt(to);
		}
		if (start > end) {
			int tmp = 0;
			tmp = start;
			start = end;
			end = tmp;
		}
		JSONObject retJobj = showDistrictInfoService.showByDistrictId(start, end);
		return retJobj.toString();
	}
	@RequestMapping("/removeDistrictInfo")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response,@RequestBody JSONObject jobj) {
		String districtId=jobj.getString("districtId");
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",showDistrictInfoService.removeDistrictInfo(districtId));
		retJobj.put("url", "/ddxq/system/districtManage/showDistrictGrid");
		return retJobj.toString();
	}
	@RequestMapping("/searchDistrict")
	@ResponseBody
	public String searchdist(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name");
		String name2="";
		 try {
			byte[] bytes=name.getBytes("ISO-8859-1");
			name2=new String(bytes,"utf-8");
			System.out.println(name2);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		JSONObject retJobj = showDistrictInfoService.searchDistrict(name2);
		return retJobj.toString();
	}
	@RequestMapping("/showDrawBorderline")
	public ModelAndView showborder(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.addObject("districtId", request.getParameter("districtId"));
		mav.addObject("rowId", request.getParameter("rowId"));
		mav.setViewName(DRAW_VIEW_URL);	
		return mav;
	}
	@RequestMapping("/addBorderline")
	@ResponseBody
	public String insertborder(HttpServletRequest request, HttpServletResponse response,@RequestBody JSONObject jobj) {
		String id=jobj.getString("id");
		String x=jobj.getString("x");
		String y=jobj.getString("y");
		String points=jobj.getString("point");
		String []po=points.split(",");
		String endString="";
		for (int i = 0; i < po.length; i++) {
			if(i%2==0){
				endString+=po[i]+",";
			}else{
				if(i==po.length-1){
					endString+=po[i];
				}else{
					endString+=po[i]+":";
				}
				
			}
			
		}
		JSONObject retJobj = new JSONObject();
		showDistrictInfoService.insertLocation(x, y, id);
		retJobj.put("success", showDistrictInfoService.insertBorder(id, endString));
		return retJobj.toString();
	}

}
