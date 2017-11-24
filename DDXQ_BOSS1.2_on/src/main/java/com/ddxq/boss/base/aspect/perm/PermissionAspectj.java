/**
 * FileName:    PrivilegeAspectj.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By ddxq
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月12日 上午8:17:31
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月12日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.aspect.perm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.cache.CacheUtil;
import com.ddxq.common.log.SystemLog;


/**
 * ClassName:PrivilegeAspectj <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月12日 上午8:17:31 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Aspect
@Component
@Order(1)
public class PermissionAspectj {
	@Autowired
	MD5Util userOpenIDToMD5;

	//redis的依赖
	@Autowired
	CacheUtil cacheUtil;
    @Autowired
   	BaseDAO baseDAO;
	@Autowired
	SystemLog systemLog;
	
	@Around("@annotation(Permission)")
	public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/weixin/common/user/zhuce/login");
		Object[] args = pjp.getArgs();
		WeiXinUserPojo weixinUser =(WeiXinUserPojo)args[2];
		HttpServletRequest request= (HttpServletRequest)(pjp.getArgs()[0]);
		HttpServletResponse response= (HttpServletResponse)(pjp.getArgs()[1]);
		if(validate(weixinUser)){//此时拦截事件发生，用户跳转到注册页面，如果是从菜单访问，会携带一个code，
				request.setAttribute("openid", weixinUser.getOpenid());
				//request.getRequestDispatcher("/ddxq/wx/user/register").forward(request, response);
				//return null;
			return	model;
		}
		else
		{
			return pjp.proceed(args);
		}
    }
	
	/**
	 * 验证方法
	 * 采用的是对用户信息进行读取，如果电话号码值为空，就进行拦截，进入到用户注册页面
	 * @throws Throwable 
	 */
	@SuppressWarnings("null")
	public boolean validate(WeiXinUserPojo weixinUser){
		String mobile = weixinUser.getMobile();
		if(null==mobile || "".equals(mobile)){//利用电话号码的长度进行判断
			return true;//拦截
		}
		else if(mobile.length()<9){
			systemLog.debugLog(PermissionAspectj.class, "拦截: "+mobile.length());
			return true;//拦截
		}
		else{
			systemLog.debugLog(PermissionAspectj.class, "放行 :"+mobile.length());
			return false;//放行
		}
	}	    	

}

