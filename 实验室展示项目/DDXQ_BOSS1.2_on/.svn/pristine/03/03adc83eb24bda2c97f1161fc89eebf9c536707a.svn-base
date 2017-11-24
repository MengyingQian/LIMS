/**
 * FileName:    UserCacheAspectj.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By ddxq
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月21日 上午7:13:14
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月21日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */
package com.ddxq.boss.base.aspect.userCache;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ddxq.boss.base.aspect.service.UsersCacheService;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.config.HostConfig;
import com.ddxq.common.log.SystemLog;
import com.jzsn.utils.weixin.user.UserInfoManager;

/**
 * ClassName:UserCacheAspectj <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月22日 上午7:13:14 <br/>
 * @author   jkc
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Component
@Aspect
@Order(0)
public class UserCacheAspectj{
	@Autowired
	private SystemLog systemLog;
	@Autowired
	private UsersCacheService usersService;
    @Autowired
    HostConfig hostConfig;
	

//	//因为微信这边现在拿不到opednid，故利用openid等于userid来实现 ，方便程序改写
//	@Around("execution(* com.jzsn.weixin..*.*Controller.*(..))")
//	public Object processUserCache(ProceedingJoinPoint pjp) throws Throwable {
//		    systemLog.debugLog(UserCacheAspectj.class, "around advice 的方法拦截");
//	        Object[] args = pjp.getArgs();
//	        WeiXinUserPojo user = new WeiXinUserPojo();
//			HttpServletRequest request = (HttpServletRequest)(pjp.getArgs()[0]);
//			HttpServletResponse response = (HttpServletResponse)(pjp.getArgs()[1]);
//			String openid = request.getParameter("openid");
//			systemLog.debugLog(UserCacheAspectj.class, "拦截器首次得到的openid的值："+openid );
//						//首先判断是不是ajax	
//				if (request.getHeader("x-requested-with") != null 
//						&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
//					systemLog.debugLog(UserCacheAspectj.class, "发生了ajax提交!openid为："+openid);
//					if(null==openid || "".equals(openid)){//给予游客身份
//						user.setNick_name("游客");
//						user.setUser_name("游客");
//						user.setOpenid("-1");
//						user.setUser_id((long)-1);
//					}else if("-1".equals(openid) ){//点击菜单按钮，给出一个游客的身份
//						user.setNick_name("游客");
//						user.setUser_name("游客");
//						user.setOpenid("-1");
//						user.setUser_id((long)-1);
//					}else if("-2".equals(openid)){//第一次查询localstorage未得到openid，在数据库插入一条数据并返回openid
//						//插入数据并返回userid=openid
//						long userid = usersService.getUserID();//插入新的数据，并返回userid
//						user.setNick_name("游客");
//						user.setUser_name("游客");
//						user.setOpenid( String.valueOf(userid) );
//						user.setUser_id(userid);
//					}else{
//						user = usersService.getUserfromOpneid(openid);
//					}
//					if (args[2] != null && args.length > 0 && args[2].getClass() == WeiXinUserPojo.class) {				
//						args[2] = user;
//					}				
//					return pjp.proceed(args);
//				}else{
//					systemLog.debugLog(UserCacheAspectj.class, "发生form提交!");
//					if(null==openid || "".equals(openid)){//给予游客身份
//						user.setNick_name("游客");
//						user.setUser_name("游客");
//						user.setOpenid("-1");
//						user.setUser_id((long)-1);
//					}else if("-1".equals(openid) ){//点击菜单按钮，给出一个游客的身份
//						user.setNick_name("游客");
//						user.setUser_name("游客");
//						user.setOpenid("-1");
//						user.setUser_id((long)-1);
//					}else if("-2".equals(openid)){//第一次查询localstorage未得到openid，在数据库插入一条数据并返回openid
//						//插入数据并返回userid=openid
//						long userid = usersService.getUserID();//插入新的数据，并返回userid
//						user.setNick_name("游客");
//						user.setUser_name("游客");
//						user.setOpenid( String.valueOf(userid) );
//						user.setUser_id(userid);
//					}else{
//						user = usersService.getUserfromOpneid(openid);
//					}
//					if (args[2] != null && args.length > 0 && args[2].getClass() == WeiXinUserPojo.class) {//将数据参数传递给被拦截的controller
//						args[2] = user;
//					}
//					ModelAndView mav1 = (ModelAndView)pjp.proceed(args); 
//					mav1.addObject("openid", user.getOpenid());
//					systemLog.debugLog(UserCacheAspectj.class, "传给jsp的openid参数为："+user.getOpenid());
//					return mav1;
//				}
//	}
}	
