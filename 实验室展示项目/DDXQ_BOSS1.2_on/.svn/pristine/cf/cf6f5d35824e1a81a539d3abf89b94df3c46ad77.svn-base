package com.ddxq.weixin.base.fastdfsfile.service;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ddxq.base.dao.fastDFS.BaseFastdfsDaoImpl;
import com.ddxq.base.dao.file.BaseFileDao;
import com.ddxq.common.config.HostConfig;
import com.ddxq.weixin.base.fastimage.service.ChangeSizeService;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年11月23日 下午9:43:19 
* 类说明 
*/
@Service("uploadFileService")
public class uploadFileServiceImpl implements uploadFileService {
	
	@Autowired
	private BaseFileDao fileDao;
	@Autowired
	private BaseFastdfsDaoImpl fastdfsDao;
	@Autowired
	private HostConfig hostConfig;
	@Autowired
	private ChangeSizeService changeSizeService;	

	@Override
	public String[] uploadImage(HttpServletRequest request) throws IOException {
		String [] result = new String[5];
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upfile");
		String file_name = file.getOriginalFilename();
		InputStream input = file.getInputStream();
		String file_ext_name = file_name.substring(file_name.lastIndexOf(".")+1);
		long file_lenth = file.getSize();
		byte[] file_buff = fileDao.fileToByte(input, file_lenth);
		NameValuePair[] meta_list = new NameValuePair[3];
		meta_list[0] = new NameValuePair("file_name", file_name);
		meta_list[1] = new NameValuePair("file_ext_name", file_ext_name);
		meta_list[2] = new NameValuePair("file_lenth", String.valueOf(file_lenth));
		
		String[] url = fastdfsDao.fastdfsUpload(file_buff, file_ext_name, meta_list);
		result[0]= "http://"+hostConfig.getFastdfs_domainName()+"/"+ url[0]+"/"+url[1];
		result[1]= String.valueOf(file_lenth);
		result[2]= file_name;
		result[3]= file.getName();
		result[4]= file.getContentType();
		return result;
	}
	@Override
	public String[] uploadImage1(HttpServletRequest request) throws IOException {
		String [] result = new String[2];
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upfile");
		String file_name = file.getOriginalFilename();
		InputStream input = file.getInputStream();
		String file_ext_name = file_name.substring(file_name.lastIndexOf(".")+1);
		long file_lenth = file.getSize();
/*		byte[] file_buff = fileDao.fileToByte(input, file_lenth);*/
		byte[] file_buff = changeSizeService.changeImageSize(file, 220, 220);
		NameValuePair[] meta_list = new NameValuePair[3];
		meta_list[0] = new NameValuePair("file_name", file_name);
		meta_list[1] = new NameValuePair("file_ext_name", file_ext_name);
		meta_list[2] = new NameValuePair("file_lenth", String.valueOf(file_lenth));
		String[] url = fastdfsDao.fastdfsUpload(file_buff, file_ext_name, meta_list);
		result[0]= "http://"+hostConfig.getFastdfs_domainName()+"/"+ url[0]+"/"+url[1];
		result[1]= "/"+ url[0]+"/"+url[1];
		return result;
	}

}
 