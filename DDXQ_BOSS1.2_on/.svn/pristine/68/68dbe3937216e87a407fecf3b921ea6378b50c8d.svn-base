package com.ddxq.employee.vrcode.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddxq.common.log.SystemLog;
import com.ddxq.employee.vrcode.service.GrzxService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/wx/hdq/grzx")
public class GrzxController {

	@Autowired
	private SystemLog systemLog;
	@Autowired
	private GrzxService grzxService;


	@RequestMapping("/getcode")
	@ResponseBody
	public Map<String, Object> getCode(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj) {
		String mobile = obj.getString("mobile");
		String change = obj.getString("change");
		Map<String, Object> result = grzxService.getCode(mobile, change);
		return result;
	}
}
