package com.ddxq.employee.seller.controller;

import java.io.IOException;
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

import com.ddxq.employee.seller.service.SellerService;
import com.ddxq.weixin.base.fastdfsfile.service.uploadFileService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/seller/xqcs")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	@Autowired
	private uploadFileService uploadfile;
	
	Logger log = LoggerFactory.getLogger(SellerController.class);  
	public static final String COMMODITIES_SHOW ="/seller/xqcs/xqcs";
	
	
	@RequestMapping("/show")
	public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response){
		String sellerID = (String)request.getSession().getAttribute("id");
		ModelAndView mav=new ModelAndView();
		mav.addObject("seller", sellerService.getSellerInfo(sellerID));
		mav.addObject("menuA",sellerService.getModelMenu());
		mav.setViewName(COMMODITIES_SHOW);
		return mav;	
		
	}
	@RequestMapping("/getinfos")
	@ResponseBody
	public JSONObject getinfo(HttpServletRequest request, HttpServletResponse response){
		String districtId = (String)request.getSession().getAttribute("districtId");
		String sellerID = (String)request.getSession().getAttribute("id");
		JSONObject object=sellerService.getGoods(districtId, sellerID);
		System.out.println("object："+object);
		return object;
	}
	
	@RequestMapping(value = "/headimagesave", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String ImageSave(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject retJSON = new JSONObject();
		String[] imagePath = uploadfile.uploadImage1(request);
		if( imagePath != null){
			retJSON.put("success", true); 
			retJSON.put("imagePath", imagePath[0]);
			retJSON.put("imagePathShort", imagePath[1]);
		}else{
			retJSON.put("success", false);
		}
		return retJSON.toString();
	}
	
	@RequestMapping("/getMenuB")
	@ResponseBody
	public String getMenuB(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		JSONObject retJobj = sellerService.getMenuB(obj.getString("idA"));
		return retJobj.toString();
	}

	@RequestMapping("/insertInfo")
	@ResponseBody
	public String insertNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		obj.put("seller_id", (String)request.getSession().getAttribute("id"));
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",sellerService.insertInfo(obj));
		retJobj.put("url", "/ddxq/seller/xqcs/getinfos");
		return retJobj.toString();
	}
	
	@RequestMapping("/searchData")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String districtId=request.getParameter("districtIdFrom").trim();
		String cateid=request.getParameter("cateid");
		JSONObject object;
		System.out.println(cateid);
		if(cateid.equals("-1")){
			object=sellerService.getDataFromDistrictIdAndColumn(districtId);
		}else{
			object=sellerService.getDataFromDistrictIdAndColumn(districtId,cateid);
		}
		System.out.println(object.toString());
		return object.toString();
	}
	@RequestMapping("/editInfo")
	@ResponseBody
	public String edit(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		obj.put("seller", (String)request.getSession().getAttribute("id"));
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",sellerService.updateInfo(obj));
		retJobj.put("url", "/ddxq/seller/xqcs/getinfos");
		return retJobj.toString();
	}
	@RequestMapping("/removeData")
	@ResponseBody
	public String reomove(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject obj){
		JSONObject retJobj = new JSONObject();
		retJobj.put("success",sellerService.removeInfo(obj));
		retJobj.put("url", "/ddxq/seller/xqcs/getinfos");
		return retJobj.toString();
	}
	
	@RequestMapping("/removemenu")
	@ResponseBody
	public JSONObject removemenu(HttpServletRequest request, HttpServletResponse response){
		JSONObject object=sellerService.removeMenu();
		System.out.println("删除成功");
		return object;
	}
}
