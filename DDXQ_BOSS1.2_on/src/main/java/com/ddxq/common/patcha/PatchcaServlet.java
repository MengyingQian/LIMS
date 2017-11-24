/**
 * FileName:    PatchaServlet.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月8日 上午11:59:01
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月8日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.common.patcha;
/**
 * ClassName:PatchaServlet <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月8日 上午11:59:01 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;



public class PatchcaServlet extends HttpServlet {
	private static final long serialVersionUID = -656795564528376490L;
	private static Random random = new Random();
	private static int WIDTH = 110;
	private static int HEIGHT = 50;
	private static int MAX_LENGTH = 4;
	private static int MIN_LENGTH = 4;
	
	private static ConfigurableCaptchaService cs;
	private static ConcurrentHashMap<String, String> patchcaMap = new ConcurrentHashMap<String, String>();

	@Override
	public void init() throws ServletException {
		cs = new ConfigurableCaptchaService();
		
		int width = NumberUtils.toInt(this.getInitParameter("width"), WIDTH); 
		int height = NumberUtils.toInt(this.getInitParameter("height"), HEIGHT);
		cs.setWidth(width);
		cs.setHeight(height);
		
		cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
		
		int maxLength = NumberUtils.toInt(this.getInitParameter("maxLength"), MAX_LENGTH); 
		int minLength = NumberUtils.toInt(this.getInitParameter("minLength"), MIN_LENGTH);
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("234578acefghkmnprstuvwxyz");
		wf.setMaxLength(maxLength);
		wf.setMinLength(minLength);
		
		cs.setWordFactory(wf);
		cs.setFilterFactory(new DiffuseRippleFilterFactory());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 清除缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		
		// 显示类型
		response.setContentType("image/png");
	      
		OutputStream os = response.getOutputStream();
		String patchca= EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
		String sessionId = request.getSession().getId();
		patchcaMap.put(sessionId, patchca);
		os.flush();
		os.close();
	}
	
	/**
	 * 检查验证码是否正确
	 * @param req
	 * @return
	 */
	public static boolean validate(String sessionId, String code) {
		System.out.println("sessionId is:"+sessionId+", code is:"+code);
		String sourceCode = patchcaMap.remove(sessionId);
		System.out.println("sourceCode is:"+sourceCode+", code is:"+code);
		return StringUtils.equalsIgnoreCase(code, sourceCode);
	}
	
	@Override
	public void destroy() {
		cs = null;
	}
}
