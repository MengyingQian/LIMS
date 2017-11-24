package com.ddxq.admin.xxl.xwzx.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.ddxq.admin.xxl.xwzx.service.XwzxService;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.common.cache.CacheUtil;
import com.ddxq.common.config.HostConfig;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/admin/xxl/xwzx")
/**
 * 用来物业通知等公告之类的
 * @author wzp
 *
 */
public class XwzxController {
	@Autowired
	private HostConfig hostConfig;
	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	XwzxService xwzxService;
	@Autowired
	BaseHBaseDAO baseHBaseDAO;
	Logger log = LoggerFactory.getLogger(XwzxController.class);

	@RequestMapping("/showNotice")
	public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		String districtId=request.getSession().getAttribute("districtId").toString();
		mav.addObject("districtId", districtId);
		mav.setViewName("/admin/noticemanage/xwzx");
		return mav;		
	}
	@RequestMapping("/showSingleNotice")
	public ModelAndView showSingleNotice(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String districtId = request.getSession().getAttribute("districtId").toString();
		String actorid = request.getSession().getAttribute("actorid").toString();
		mav.addObject("districtId", districtId);
		String messtype = xwzxService.getMessageType(actorid);
		System.out.println("messtype: " + messtype);
		mav.addObject("messtype", messtype);
		mav.setViewName("/admin/noticemanage/xwzxsingle");
		return mav;		
	}
	//内容编辑器
	@RequestMapping("/showEditor")
	public ModelAndView addEditor(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		mav.addObject("domainName", hostConfig.getMobile_domainName());
		mav.addObject("upurl", "/ddxq/ueditor/upimage");
		mav.setViewName("/bussiness/xxl/uEditor");
		return mav;		
	}
	@RequestMapping("/insertNotice")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){	
			JSONObject retJobj = xwzxService.insertNotice(jobj);
			return retJobj.toString();
	}
	@RequestMapping("/editNotice")
	@ResponseBody
	public String editNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jobj){		
			String districtId=jobj.getString("districtId");		
			String info=jobj.getString("info");
			String docid=jobj.getString("docid");					
			String title=jobj.getString("title");
			String content=jobj.getString("content");
			String link=jobj.getString("link");
			String author=jobj.getString("author");
			String publisher=jobj.getString("publisher");
			String created=jobj.getString("created2");
			String subtitle=jobj.getString("subtitle");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Map row = new HashMap();
			List<Map<String,String>> list = new ArrayList();
			row.put("content", content);
			list.add(row);
			try {
				baseHBaseDAO.addData(docid, "ddxq-news", list);
			} catch (IOException e) {			
				e.printStackTrace();
			}
			
			Map map=new HashMap<>();
			map.put("subtitle",subtitle);
			map.put("title", title);
			map.put("docid", docid);
			map.put("link", link);
			map.put("author", author);
			map.put("publisher",publisher);
			map.put("created", created);
			map.put("updated", new Date().getTime()+"");
			cacheUtil.hashPut(docid, map);			
			
			JSONObject retJobj = new JSONObject();
			retJobj.put("success",true);
			retJobj.put("districtId",districtId);
			retJobj.put("info",info);
			retJobj.put("url", "/ddxq/admin/xxl/xwzx/searchData?districtIdFrom="+districtId+"&column="+info);
			return retJobj.toString();
	}
	@RequestMapping("/searchData")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String districtId=request.getParameter("districtIdFrom").trim();
		String column=request.getParameter("column").trim();		
		JSONObject object=xwzxService.getDataFromDistrictIdAndColumn(districtId, column);		
		return object.toString();
	}
	@RequestMapping("/removeData")
	@ResponseBody
	public String remove(HttpServletRequest request, HttpServletResponse response,@RequestBody JSONObject jobj) {
		String districtId=jobj.getString("districtId");
		String column=jobj.getString("info");
		String docid=jobj.getString("docid");
		String redisKey="districtid:"+districtId+":info:"+column;
		JSONObject retJobj = new JSONObject();
		xwzxService.remove(redisKey, docid,districtId,column);
		retJobj.put("success",true);
		retJobj.put("url", "/ddxq/admin/xxl/xwzx/searchData?districtIdFrom="+districtId+"&column="+column);
		return retJobj.toString();
	}
	@RequestMapping("/testNotice")
	public ModelAndView testNotice(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		String districtId=request.getParameter("districtId");
		String name=xwzxService.getDistrictName(Integer.parseInt(districtId));
		String info=request.getParameter("info");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String link=request.getParameter("link");
		String publisher=request.getParameter("publisher");
		String author=request.getParameter("author");
		String column="";
		String[] strings=new String[]{};
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date= sdf.format(new Date());	
		column=xwzxService.parseToName(info);
		Map map=new HashMap<>();
		map.put("column", column);
		map.put("name", name);
		map.put("time", date);
		map.put("title", title);
		map.put("publisher",publisher);
		map.put("content", content);
		map.put("link", link);
		map.put("author", author);
		mav.addObject("map",map);
		mav.setViewName("/admin/noticemanage/newsScan");
		return mav;		
	}
}
