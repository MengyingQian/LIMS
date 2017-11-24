package com.ddxq.mytest.testgrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/boss/mytest/")
public class TestController {
	@RequestMapping("/grid")
	public ModelAndView queryQRcodeGrid(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/Test");
		return modelAndView;
	}
	@RequestMapping("/getData")
	@ResponseBody
	public String showQRcodeGrid(HttpServletRequest request, HttpServletResponse response){	
		//JSONObject retJobj=showDistrictInfoService.showAll();
		JSONObject retJobj=new JSONObject();
		retJobj.put("total",14);
		List<Map<String, Integer>> list=new ArrayList<>();
		Map<String, Integer> map1=new HashMap<>();
		Map<String, Integer> map2=new HashMap<>();
		map1.put("id", 1);
		map1.put("name",1);
		map2.put("id", 1);
		map2.put("name",1);
		list.add(map1);
		list.add(map2);
		retJobj.put("rows", list);
		return retJobj.toString();
	}
}
