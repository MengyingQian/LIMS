package com.ddxq.boss.security.captcha;

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
		String sessionId = request.getSession().getId();
		OutputStream os = response.getOutputStream();
		String patchca= EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
		System.out.println(sessionId +":q" + patchca);
		patchcaMap.put(sessionId, patchca);
		
		os.flush();
		os.close();
		// 清除缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		
		// 显示类型
		response.setContentType("image/png");
	}
	
	/**
	 * 检查验证码是否正确
	 * @param req
	 * @return
	 */
	public static boolean validate(String sessionId, String code) {
		System.out.println("sessionid:"+sessionId+",code="+code);
		String sourceCode = patchcaMap.get(sessionId);
	    return sourceCode.equals(code);
		//return StringUtils.equalsIgnoreCase(code, sourceCode);
	}
	
	@Override
	public void destroy() {
		cs = null;
	}
}
