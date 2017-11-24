/**
 * FileName:    ActionAspectj.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月13日 上午7:46:23
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月13日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.common.aspectj.action;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * ClassName:ActionAspectj <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月13日 上午7:46:23 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Aspect
@Component
public class ActionAspectj {
	private static Logger logger = LoggerFactory.getLogger(ActionAspectj.class);
	@Pointcut("@annotation(action)")
	public void annotationAction(Action action) {}
	
	@After("annotationAction(action)")
	public void interceptor(JoinPoint jp, Action action) throws Throwable{
		HttpServletRequest request = (HttpServletRequest)(jp.getArgs()[0]);
		logger.info(""+request.getRemoteAddr()+","+request.getRemotePort()+","+jp.getSignature().toString()+","+request.getRequestURI()+","+request.getRequestURL()+",zxz,"+request.getLocalAddr()+","+action.action()+",success");
	}
}
