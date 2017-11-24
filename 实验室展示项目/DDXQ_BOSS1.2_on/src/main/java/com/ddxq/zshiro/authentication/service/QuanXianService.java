package com.ddxq.zshiro.authentication.service;

import java.util.List;
import java.util.Map;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年10月17日 上午11:23:11 
* 类说明 
*/
public interface QuanXianService {
	
	public Map<String,Object> getUser(String mobile);
	public List<String> getRoles(String user_id);
	public List<String> getPerms(String user_id);
	

}
 