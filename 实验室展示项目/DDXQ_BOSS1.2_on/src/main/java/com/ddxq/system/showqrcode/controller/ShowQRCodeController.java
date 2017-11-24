package com.ddxq.system.showqrcode.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.boss.base.service.qrcode.QRcodeService;
import com.ddxq.common.config.HostConfig;
import com.ddxq.system.showqrcode.service.ShowQRCodeService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ddxq/system/qrcodeManage")
public class ShowQRCodeController {
	private static final String QRCODE_VIEW_URL = "/system/district/showQRCode";

	@Autowired
	ShowQRCodeService showQRCodeService;
	@Autowired
	QRcodeService qRcodeService;
	@Autowired
	HostConfig hostConfig;
	Logger log = LoggerFactory.getLogger(ShowQRCodeController.class);
	@RequestMapping("/showQRcode")
	public ModelAndView showQRcode(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(QRCODE_VIEW_URL);	
		mav.addObject("host", hostConfig.getFastdfs_domainName());
		return mav;
	}
	@RequestMapping("/showQRcodeGrid")
	@ResponseBody
	public String showQRcodeGrid(HttpServletRequest request, HttpServletResponse response){	
		JSONObject retJobj=showQRCodeService.showAll();
		return retJobj.toString();
	}
	@RequestMapping("/genData")
	@ResponseBody
	public String queryQRcodeGrid(HttpServletRequest request, HttpServletResponse response){
		int start,end;
		String from=request.getParameter("districtIdFrom").trim();
		String to=request.getParameter("districtIdTo").trim();
		if(from.equals("")){
			start=1;
		}else{
			start =Integer.parseInt(from);
			if(start<0){
				start=0;
			}
		}
		end = Integer.parseInt(to);
		
		
		for (int i = start; i <= end; i++) {			
			if(!showQRCodeService.checkExist(i)){
				List<Map<String, Object>> list=new ArrayList<>();
				list.add(qRcodeService.genUrl(i, 5000));
				showQRCodeService.insertNewCode(list);
			}			
		}
		
		JSONObject retJobj = showQRCodeService.showAll();
		return retJobj.toString();
	}
	@RequestMapping("/searchData")
	@ResponseBody
	public String searchData(HttpServletRequest request, HttpServletResponse response) {
		int start,end;
		boolean isHaveDate=false;
		String from=request.getParameter("districtIdFrom").trim();
		String to=request.getParameter("districtIdTo").trim();
		String dateFrom=request.getParameter("dateFrom").trim();
		String dateTo=request.getParameter("dateTo").trim();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(dateFrom.equals("")||dateTo.equals("")){	
			isHaveDate=false;
		}else{
			try {
				Date date1 = sdf.parse(dateFrom);
				Date date2 = sdf.parse(dateTo);
				if(date2.compareTo(date1)==-1){
					String tmp=dateFrom;
					dateFrom=dateTo;
					dateTo=tmp;				
				}
				isHaveDate=true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(from.equals("")){
			start=1;
		}else{
			start =Integer.parseInt(from);
		}
		if(to.equals("")){
			end=100000;
		}else{
			end = Integer.parseInt(to);
		}
		if(start>end){
			int tmp =0;
			tmp=start;
			start=end;
			end=tmp;
		}
		if(isHaveDate){
			JSONObject retJobj=showQRCodeService.showByDistrictIdAndDate(start, end, dateFrom, dateTo);
			return retJobj.toString();
		}else{
			JSONObject retJobj=showQRCodeService.showByDistrictId(start, end);
			return retJobj.toString();
		}	
		

	}
	@RequestMapping("/deleteData")
	@ResponseBody
	public String deleteData(HttpServletRequest request, HttpServletResponse response) {
		int start=-1,end=-1;
		boolean isDelete=true;
		boolean isHaveDate=false;
		String from=request.getParameter("districtIdFrom").trim();
		String to=request.getParameter("districtIdTo").trim();
		String dateFrom=request.getParameter("dateFrom").trim();
		String dateTo=request.getParameter("dateTo").trim();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(dateFrom.equals("")||dateTo.equals("")){	
			isHaveDate=false;
		}else{
			try {
				Date date1 = sdf.parse(dateFrom);
				Date date2 = sdf.parse(dateTo);
				if(date2.compareTo(date1)==-1){
					String tmp=dateFrom;
					dateFrom=dateTo;
					dateTo=tmp;				
				}
				isHaveDate=true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(from.equals("") || toString().equals("")){
			isDelete = false;
		}else{
			isDelete = true;
			start = Integer.parseInt(from);
			end = Integer.parseInt(to);
		}
		if(isHaveDate){
			showQRCodeService.deleteByDistrictIdAndDate(start, end, dateFrom, dateTo);
		}else if(isDelete){
			showQRCodeService.deleteByDistrictId(start, end);
		}
		JSONObject retJobj = showQRCodeService.showAll();
		return retJobj.toString();

	}
	@RequestMapping("/pageData")
	@ResponseBody
	public String pageData(HttpServletRequest request, HttpServletResponse response) {
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber").trim());
		int pageSize=Integer.parseInt(request.getParameter("pageSize").trim());
		int total =Integer.parseInt(request.getParameter("total").trim());
		JSONObject retJobj=showQRCodeService.showByPage(pageNumber,  pageSize,total);
		return retJobj.toString();

	}
	@RequestMapping("/noteData")
	@ResponseBody
	public String noteData(HttpServletRequest request, HttpServletResponse response) {
		int from=Integer.parseInt(request.getParameter("districtIdFrom").trim());
		int to=Integer.parseInt(request.getParameter("districtIdTo").trim());
		String note=request.getParameter("note").trim();
		showQRCodeService.insertNote(from, to, note);
		JSONObject retJobj=showQRCodeService.showAll();
		return retJobj.toString();

	}
	
}
