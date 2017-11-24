package com.ddxq.zshiro.mcustom;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年10月13日 上午11:11:09 
* 类说明 
*/
public class CustomToken implements HostAuthenticationToken, RememberMeAuthenticationToken{
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 
		 */
		private String mobile;
	    private String password;
	    private String host;
	    private boolean rememberMe = false;
	    private String captcha;

		public CustomToken() {
	    }

	    public CustomToken(String mobile, String password) {
	        this(mobile, password, false, null);
	    }

	    public CustomToken(String mobile, String password, String host) {
	        this(mobile, password, false, host);
	    }

	    public CustomToken(String mobile, String password, boolean rememberMe) {
	        this(mobile, password, rememberMe, null);
	    }

	    public CustomToken(String mobile, String password, boolean rememberMe, String host) {
	        this.mobile = mobile;
	        this.password = password;
	        this.rememberMe = rememberMe;
	        this.host = host;
	    }
	    
	    public CustomToken(String mobile, String password,
				boolean rememberMe, String host, String captcha) {
	    	this.mobile = mobile;
	        this.password = password;
	        this.rememberMe = rememberMe;
	        this.host = host;
			this.captcha = captcha;
		}

	    public Object getPrincipal() {
	        return getMobile();
	    }

	    public Object getCredentials() {
	        return getPassword();
	    }

	    public String getHost() {
	        return host;
	    }

	    public boolean isRememberMe() {
	        return rememberMe;
	    }

	    public void clear() {
	        this.mobile = null;
	        this.host = null;
	        this.password = null;
	        this.rememberMe = false;
	        this.captcha = null;
	    }

	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getName());
	        sb.append(" - ");
	        sb.append(mobile);
	        sb.append(", rememberMe=").append(rememberMe);
	        if (StringUtils.isNotBlank(host)) {
	            sb.append(" (").append(host).append(")");
	        }
	        return sb.toString();
	    }

	    public String getMobile() {
	        return mobile;
	    }

	    public void setMobile(String mobile) {
	        this.mobile = mobile;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setHost(String host) {
	        this.host = host;
	    }

	    public String getCaptcha() {
			return captcha;
		}

		public void setCaptcha(String captcha) {
			this.captcha = captcha;
		}

	    public void setRememberMe(boolean rememberMe) {
	        this.rememberMe = rememberMe;
	    }

}
 