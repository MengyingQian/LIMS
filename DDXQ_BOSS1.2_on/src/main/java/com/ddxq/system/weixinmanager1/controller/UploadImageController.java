package com.ddxq.system.weixinmanager1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.weixin.base.fastdfsfile.service.uploadFileService;

import net.sf.json.JSONObject;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年11月24日 上午9:55:31 
* 类说明 
*/
@Controller
@RequestMapping("/ddxq/ueditor")
public class UploadImageController {
	
	private static final String ueditor = "/ueditor/ueditor";
	
	@Autowired
	private uploadFileService upload;	 
	
	@RequestMapping("/show")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response ){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ueditor);	
		return mav;
	}
	
	
	@RequestMapping("/upimage")
	@ResponseBody
	public String upimage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject obj = new JSONObject();
		String[] result = upload.uploadImage(request);
		obj.put("state","SUCCESS");
		obj.put("url", result[0]);
		obj.put("size", result[1]);
		obj.put("title",result[2]);
		obj.put("original", result[2]);
		obj.put("type", result[4]);
		for(String s:result){
			System.out.println("s: "+s);
		}
		return obj.toString();
	}	

}
 