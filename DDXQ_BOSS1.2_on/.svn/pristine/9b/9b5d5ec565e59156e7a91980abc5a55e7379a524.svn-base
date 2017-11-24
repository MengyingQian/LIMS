package com.ddxq.boss.base.util.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * ClassName:GetIPinfo
 * Description:TODO
 * @author Liu
 * @date 2016年8月30日 下午3:21:07
 *
 */
public class GetIPinfo {

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	public static String request(String httpArg) {
		String url = "http://apis.juhe.cn/ip/ip2addr?ip=IPIP&dtype=json&key=bfdb46f7a3b64042ebccb439d39886fe";
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url.replace("IPIP", httpArg));
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ( (inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static Map<String, String> requestInfo(String str) {
		Map<String, String> map = new HashMap<String, String>();
		if(str.contains("重庆市")){
			map.put("province", "重庆市");
			map.put("city", "重庆市");
		}else if(str.contains("北京市")){
			map.put("province", "北京市");
			map.put("city", "北京市");
		}else if(str.contains("上海市")){
			map.put("province", "上海市");
			map.put("city", "上海市");
		}else if(str.contains("天津市")){
			map.put("province", "天津市");
			map.put("city", "天津市");
		}else if(str.contains("香港")){
			map.put("province", "香港");
			map.put("city", "香港");
		}else if(str.contains("澳门")){
			map.put("province", "澳门");
			map.put("city", "澳门");
		}else if(str.contains("广西")){
			map.put("province", "广西");
			map.put("city", str.replace("广西", ""));
		}else if(str.contains("内蒙古")){
			map.put("province", "内蒙古");
			map.put("city", str.replace("内蒙古", ""));
		}else if(str.contains("宁夏")){
			map.put("province", "宁夏");
			map.put("city", str.replace("宁夏", ""));
		}else if(str.contains("西藏")){
			map.put("province", "西藏");
			map.put("city", str.replace("西藏", ""));
		}else if(str.contains("新疆")){
			map.put("province", "新疆");
			map.put("city", str.replace("新疆", ""));
		}else{
			int pos = str.indexOf("省") + 1;
			map.put("province", str.substring(0, pos));
			map.put("city", str.substring(pos));
		}
		return map;
	}

}
