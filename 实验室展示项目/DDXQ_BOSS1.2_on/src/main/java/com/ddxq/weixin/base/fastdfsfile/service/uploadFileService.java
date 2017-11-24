package com.ddxq.weixin.base.fastdfsfile.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年11月23日 下午9:42:42 
* 类说明 
*/
public interface uploadFileService {
	
	public String[] uploadImage(HttpServletRequest request)throws IOException;
	public String[] uploadImage1(HttpServletRequest request) throws IOException;
	
}
 