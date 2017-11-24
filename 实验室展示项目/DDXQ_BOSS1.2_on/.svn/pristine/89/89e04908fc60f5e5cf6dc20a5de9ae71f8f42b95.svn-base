package com.ddxq.zshiro.mfilter; 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddxq.common.log.SystemLog;
import com.ddxq.zshiro.mcustom.CustomToken;
/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年10月13日 上午11:03:45 
* 类说明 
*/
public class MyAuthenticationFilter extends AuthenticatingFilter{
	
	@Autowired 
	private  SystemLog systemLog;
	
    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";
    public static final String DEFAULT_LOGINNAME_PARAM = "mobile";
    public static final String DEFAULT_PASSWORD_PARAM = "password";
    public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
    private String mobileParam = DEFAULT_LOGINNAME_PARAM;
    private String passwordParam = DEFAULT_PASSWORD_PARAM;
    private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;
    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    	
    
    public MyAuthenticationFilter() {
        setLoginUrl(DEFAULT_LOGIN_URL);
    }
    @Override
    public void setLoginUrl(String loginUrl) {
        String previous = getLoginUrl();
        if (previous != null) {
            this.appliedPaths.remove(previous);
        }
        super.setLoginUrl(loginUrl);
        this.appliedPaths.put(getLoginUrl(), null);
    }
    /**
     * 在访问被拒绝
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
            	systemLog.debugLog(getClass(), "Login submission detected.  Attempting to execute login.");
                return executeLogin(request, response);
            } else {
            	systemLog.debugLog(getClass(), "Login page view.");
                return true;
            }
        } else {
        	systemLog.debugLog(getClass(), "Attempting to access a path which requires authentication.  Forwarding to the "
                    + "Authentication url [" + getLoginUrl() + "]");
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
    /**
     * 创建自定义的令牌
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
    	String loginName = getLoginName(request);
        String password = getPassword(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CustomToken(loginName, password, rememberMe, host);
    }

    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
    	return (request instanceof HttpServletRequest)
                && WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
    }

    protected boolean isRememberMe(ServletRequest request) {
    	return WebUtils.isTrue(request, getRememberMeParam());
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {    	
        //由于原来的onLoginSuccess中使用的是WebUtils.redirectToSavedRequest，会判断favicon.ico，跳转url变成/favicon.ico
    	//导致无法跳转到成功页面，因此修改为直接跳转成功页面，无论是否有favicon.ico（网站图标，用来显示的）
		WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
        return true;
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
    	setFailureAttribute(request, e);
    	systemLog.debugLog(getClass(), "=====================>错误信息是:"+e.getMessage());
        return true;
    }

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
    	String className = ae.getClass().getName();
        request.setAttribute(getFailureKeyAttribute(), className);
    }

    protected String getLoginName(ServletRequest request) {
        return WebUtils.getCleanParam(request, getMobileParam());
    }

    protected String getPassword(ServletRequest request) {
        return WebUtils.getCleanParam(request, getPasswordParam());
    }

    public String getMobileParam() {
        return mobileParam;
    }

    public void setMobileParam(String mobile) {
        this.mobileParam = mobile;
    }

    public String getPasswordParam() {
        return passwordParam;
    }

    public void setPasswordParam(String passwordParam) {
        this.passwordParam = passwordParam;
    }

    public String getRememberMeParam() {
        return rememberMeParam;
    }

    public void setRememberMeParam(String rememberMeParam) {
        this.rememberMeParam = rememberMeParam;
    }

    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }
}
  

