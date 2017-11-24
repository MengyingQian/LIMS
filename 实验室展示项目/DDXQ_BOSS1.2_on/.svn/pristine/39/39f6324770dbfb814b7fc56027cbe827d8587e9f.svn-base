package com.ddxq.boss.business.wuyemsg.controller;

import java.util.List;

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

import com.ddxq.boss.business.wuyemsg.dao.WuYeMsgDao;
import com.ddxq.boss.business.wuyemsg.service.WuYeMsgService;
import com.ddxq.system.showqrcode.controller.ShowQRCodeController;
import com.jzsn.utils.weixin.token.WeiXinTokenManager;

import net.sf.json.JSONObject;
/**
 * 用于物业的各种直接推送通知。
 * @author asus
 *
 */
@Controller
@RequestMapping("/ddxq/boss/business/wuye")
public class WuYeMsgController {
	@Autowired
	WeiXinTokenManager weiXinTokenManager;
	@Autowired
	WuYeMsgService wuYeMsgService;
	@Autowired
	WuYeMsgDao wuYeMsgDao;
	Logger log = LoggerFactory.getLogger(WuYeMsgController.class);
	
	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response){
		
//		关于社区消防大修进度的通知
//		标题：关于社区消防大修进度的通知
//		发布时间：2015年5月10日
//		内容：截止5月10日，ABCD栋的烟感已全部更换好；CDEF栋地下室的温感已经更换好130套，CD栋共有10盏应急灯坏需更换，将于5月20日前更换完毕；谢谢大家的关注！
//		截止5月10日，ABCD栋的烟感已全部更换好；CDEF栋地下室的温感已经更换好130套，CD栋共有10盏应急灯坏需更换，将于5月20日前更换完毕；谢谢大家的关注！
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/bussiness/tuiSongNotice");
		
		return modelAndView;
		
	}
	@RequestMapping("/sendMsg")
	@ResponseBody
	public String send(HttpServletRequest request, HttpServletResponse response,@RequestBody JSONObject jobj){
//		String first=request.getParameter("first");
//		String data1=request.getParameter("data1");
//		String data2=request.getParameter("data2");
//		String data3=request.getParameter("data3");
//		String remark=request.getParameter("remark");
//		String mobanId=request.getParameter("mobanId");
//		String districtId=request.getParameter("districtId");
		String districtId=jobj.getString("districtId");
		List list=wuYeMsgDao.getOpenIdViaDistrictId(Integer.parseInt(districtId));
//		System.out.println("sadasd"+districtId);
		JSONObject token = weiXinTokenManager.getToken();		
		String ACCESS_TOKEN = token.getString("access_token");
//		System.out.println(mobanId);
//		String first="您有一个邮政快递包裹，请前来取件。";
//		String title="2016年11月11日 10:36";
//		String date="8号楼门卫处";
//		String remark="13800138000";
//		String content="请带上您的有效证件，谢谢合作！";
//		
		String first=jobj.getString("first");
		String data1=jobj.getString("data1");
		String data2=jobj.getString("data2");
		String data3=jobj.getString("data3");
		String remark=jobj.getString("remark");
		String mobanId=jobj.getString("mobanId");
		
		JSONObject result=wuYeMsgService.sendTmplateMsgForWuYeNotice("http://info.bjwkyy.net/dist/"+ ((Integer.parseInt(districtId)+100000)+"").substring(1)+"/news.html", ACCESS_TOKEN,list, mobanId,first,data1,data2, data3, remark);
		log.debug("是否有错误？"+result.getString("errcode")+ result.getString("errmsg"));
		return result.toString();

	}
}
