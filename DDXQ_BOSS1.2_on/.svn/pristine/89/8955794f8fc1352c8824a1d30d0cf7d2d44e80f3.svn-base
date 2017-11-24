package com.ddxq.zshiro.authentication.realm;

import java.io.Serializable;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddxq.common.log.SystemLog;
import com.ddxq.zshiro.authentication.domain.ShiroUser;
import com.ddxq.zshiro.authentication.service.QuanXianService;
import com.ddxq.zshiro.mcustom.CustomToken;
import com.google.common.collect.Maps;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年10月13日 下午2:48:49 
* 类说明 
*/
public class MyShiroDbrealm extends AuthorizingRealm{
	
	@Autowired 
	private  SystemLog systemLog;
	
	@Autowired
	private QuanXianService quanxianservice;
    
	public static final String HASH_ALGORITHM = "SHA-1";
    public static final int SALT_SIZE = 8;
    public static final int HASH_INTERATIONS = 1024;
    

    
    public MyShiroDbrealm() {
        super();
        setAuthenticationTokenClass(CustomToken.class);
    }

    /**
     * 认证回调函数,登录时调用.
     * AuthenticationInfo 中principal会设置到Session中并通过SessionDao保存起来
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException{
        CustomToken token = (CustomToken) authcToken;
        String mobile = token.getMobile();
        String loginPassword = token.getPassword();
        String host = token.getHost();
        systemLog.debugLog(getClass(), "=======>mobile is:"+mobile+", host is:"+host+"<=========");
        Map<String, Object> resUser = quanxianservice.getUser(mobile);
        if (null!=resUser.get("company_position_id") || (int)resUser.get("company_position_id")==0 ){
        	String password = (String) resUser.get("password");
        	String id=String.valueOf(resUser.get("user_id"));
        	systemLog.debugLog(getClass(),"===============>数据库获得的密码是:"+password+",用户输入的密码是:"+loginPassword);
        	if (password.equals(loginPassword)){
        		// 创建principal身份
                ShiroUser su = new ShiroUser.Builder(id, mobile).name("name").isAdmin(true).ip(host).builder();
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(su, token.getPassword(), getName());
                Subject subject = SecurityUtils.getSubject();
                Serializable sessionId = subject.getSession().getId();
                systemLog.debugLog(getClass(),"=====================>session id is:"+sessionId);
                Map<String, String> customMap = Maps.newHashMap();
                customMap.put("success", "true");
                return info;
        	}else{
        		//密码错误
        		String message = "输入密码错误";
        		throw new AuthenticationException(message);
        	}
 
        }else{
        	//用户名不存在
        	systemLog.debugLog(getClass(), "=====================>login failed!");
        	String message = "人员信息不存在";
    		throw new AuthenticationException(message);
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * 只会被缓存，不会被设置到Session中
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        System.out.println("授权函数 在这里实现用户的权限管理 id: "+shiroUser.getId()+"  IP: "+shiroUser.getIp()
        
        		+" loginname: "+shiroUser.getLoginName()+" name: "+shiroUser.getName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String user_id = shiroUser.getId();
        info.addRoles(quanxianservice.getRoles(user_id));
        info.addStringPermission("kan");
        
        return info;
    }

}
 