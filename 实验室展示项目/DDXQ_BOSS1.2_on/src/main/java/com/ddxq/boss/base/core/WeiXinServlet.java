package com.ddxq.boss.base.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ddxq.boss.base.service.receivedMsg.RecievedMsgDispatcherService;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.msg.recv.MsgReceiver;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlEntity;
import com.jzsn.utils.weixin.msg.recv.ReceiveXmlProcess;

/**
 * Servlet implementation class WeiXinServlet
 */
public class WeiXinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired 
	SystemLog systemLog;
	@Autowired
	RecievedMsgDispatcherService recievedMsgDispatcherService;
    MsgReceiver msgReceiver = new MsgReceiver();
	ReceiveXmlProcess recXMlProcessor = new ReceiveXmlProcess();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		    } catch (Exception e) {
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//在Java中，用HttpServletRequest在POST模式下别想用getParameter()拿到URL参数，必须用getQueryString()然后自己想办法解析字符串：
	//String qs = request.getQueryString();
	//Map<String, String> map = parse(qs);
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
				if (null == recievedMsgDispatcherService){
					System.out.println("recievedMsgDispatcherService is null!");
				}
				
				if (null == systemLog){
					System.out.println("systemLog is null!");
				}
					
				systemLog.debugLog(WeiXinServlet.class,"xmlStr is:"+xmlStr);
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
