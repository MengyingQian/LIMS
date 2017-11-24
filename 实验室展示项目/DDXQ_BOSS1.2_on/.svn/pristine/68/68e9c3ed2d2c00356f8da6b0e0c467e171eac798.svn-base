/**
 * FileName:    WeiXinBaseController.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By ddxq
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月22日 下午1:18:01
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月22日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.controller.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ddxq.boss.base.core.SignUtil;
import com.ddxq.boss.base.core.WeiXinServlet;
import com.ddxq.boss.base.service.receivedMsg.RecievedMsgDispatcherService;
import com.ddxq.common.constant.OperationConstant;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.msg.recv.MsgReceiver;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlProcess;

/**
 * ClassName:WeiXinBaseController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月22日 下午1:18:01 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Controller
@RequestMapping("/ddxq/wx/base/WeiXinServlet")
public class WeiXinBaseServlet {
	@Autowired
	SystemLog systemLog;
	@Autowired
	RecievedMsgDispatcherService recievedMsgDispatcherService;
	
    MsgReceiver msgReceiver = new MsgReceiver();
	ReceiveXmlProcess recXMlProcessor = new ReceiveXmlProcess();
	
	@RequestMapping(method = RequestMethod.GET)
	public void weixinGet(HttpServletRequest request,HttpServletResponse response){
		try {
		    //这是微信消息
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			PrintWriter out = response.getWriter();
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			} 
			out.close();
			out = null;
	    } catch (Exception e) {	}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void weixinPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String charst = "UTF-8";
		// TODO Auto-generated method stub
		request.setCharacterEncoding(charst);
		response.setCharacterEncoding(charst);
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			/** 读取接收到的xml消息 */
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			String xmlStr = sb.toString(); // 次即为接收到微信端发送过来的xml数据
			String result = "";
			/** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
			String echostr = request.getParameter("echostr");
			if (echostr != null && echostr.length() > 1) {
				result = echostr;
				systemLog.debugLog(WeiXinServlet.class,"result is:"+result);
			} else {
				// 正常的微信处理流程
				ApplicationContext applicationContext =WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
				systemLog.infoLog(WeiXinBaseServlet.class, "admin", "admin", System.getProperty("catalina.home"), System.getProperty("catalina.base"), OperationConstant.OPERATION_OUTUSE, "xmlStr is:"+xmlStr);				
				ReceiveXmlEntity recXMLEntity = recXMlProcessor.getMsgEntity(xmlStr);
				result = recievedMsgDispatcherService.process(recXMLEntity);
			}

			if (null != result){
				//假如允许反馈
				try {
					OutputStream os = response.getOutputStream();
					os.write(result.getBytes(charst));
					os.flush();
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				systemLog.debugLog(WeiXinServlet.class,"forbid to response!");
			}
		}else{
			systemLog.debugLog(WeiXinServlet.class,"validation failed!");
		}
	}
}
