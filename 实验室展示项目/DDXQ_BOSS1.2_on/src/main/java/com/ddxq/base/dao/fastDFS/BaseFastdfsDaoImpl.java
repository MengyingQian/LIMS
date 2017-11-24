package com.ddxq.base.dao.fastDFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import com.ddxq.common.config.HostConfig;
import com.ddxq.common.log.SystemLog;

/**
 * 
 * ClassName:BaseFastdfsDao Description:FastDFS基本操作
 * 
 * @author Liu quan wei
 * @date 2016年4月20日 下午4:30:48
 *
 */
public class BaseFastdfsDaoImpl {
	
	@Autowired
	HostConfig hostConfig;
	@Autowired 
    SystemLog systemLog;
	
	/**
	 * FastDFS配置的路径信息
	 */
	private static String conf_filename;                                                                              
	
	/**
	 * 
	 * @Title:fastdfsUpload
	 * @Description:fastdfs上传
	 * @param:@param file_buff
	 * @param:@param file_ext_name
	 * @param:@param meta_list
	 * @param:@return
	 * @return:String[]
	 * @throws
	 */
	public String[] fastdfsUpload(byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) {
		try {
			ClientGlobal.setG_anti_steal_token(false);
        	ClientGlobal.setG_charset("UTF-8");
        	ClientGlobal.setG_connect_timeout(2000);
        	ClientGlobal.setG_network_timeout(30000);
        	ClientGlobal.setG_secret_key("FastDFS1234567890");
        	ClientGlobal.setG_tracker_http_port(hostConfig.getTracker_http_port());
        	InetSocketAddress[] trackerServers = new InetSocketAddress[1];
        	trackerServers[0] = new InetSocketAddress(hostConfig.getTracker_server(),22122);
        	TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
        	ClientGlobal.setG_tracker_group(trackerGroup);
			//ClientGlobal.init(getConf_filename());
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			
			String[] fileIds = storageClient.upload_file(file_buff, file_ext_name, meta_list);

			return fileIds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title:fastdfsUploadUrl
	 * @Description:封装fastdfsUpload，直接返回url
	 * @param:@param file_buff
	 * @param:@param file_ext_name
	 * @param:@param meta_list
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public String fastdfsUploadUrl(byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) {
		String[] fileIds = fastdfsUpload(file_buff, file_ext_name, meta_list);
		systemLog.debugLog(BaseFastdfsDaoImpl.class, "fastdfs返回的fileId:"+fileIds[0]+"::"+fileIds[1]);
		String url = "/"+fileIds[0]+"/"+fileIds[1];
		return url;
	}
	/**
	 * 
	 * @Title:fastdfsDownload
	 * @Description:fastdfs下载
	 * @param:@param fileIds
	 * @param:@return
	 * @return:byte[]
	 * @throws
	 */
	public byte[] fastdfsDownload(String[] fileIds) {
		try {
			ClientGlobal.setG_anti_steal_token(false);
        	ClientGlobal.setG_charset("UTF-8");
        	ClientGlobal.setG_connect_timeout(2000);
        	ClientGlobal.setG_network_timeout(30000);
        	ClientGlobal.setG_secret_key("FastDFS1234567890");
        	ClientGlobal.setG_tracker_http_port(hostConfig.getTracker_http_port());
        	InetSocketAddress[] trackerServers = new InetSocketAddress[1];
        	trackerServers[0] = new InetSocketAddress(hostConfig.getTracker_server(),22122);
        	TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
        	ClientGlobal.setG_tracker_group(trackerGroup);
			//ClientGlobal.init(getConf_filename());
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			byte[] b = storageClient.download_file(fileIds[0], fileIds[1]);

			return b;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title:fastdfsGetFileInfo
	 * @Description:fastdfs获取文件信息
	 * @param:@param fileIds
	 * @param:@return
	 * @return:FileInfo
	 * @throws
	 */
	public FileInfo fastdfsGetFileInfo(String[] fileIds) {
		try {
			ClientGlobal.setG_anti_steal_token(false);
        	ClientGlobal.setG_charset("UTF-8");
        	ClientGlobal.setG_connect_timeout(2000);
        	ClientGlobal.setG_network_timeout(30000);
        	ClientGlobal.setG_secret_key("FastDFS1234567890");
        	ClientGlobal.setG_tracker_http_port(hostConfig.getTracker_http_port());
        	InetSocketAddress[] trackerServers = new InetSocketAddress[1];
        	trackerServers[0] = new InetSocketAddress(hostConfig.getTracker_server(),22122);
        	TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
        	ClientGlobal.setG_tracker_group(trackerGroup);
			//ClientGlobal.init(getConf_filename());
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			FileInfo fi = storageClient.get_file_info(fileIds[0], fileIds[1]);
			// (fi.getSourceIpAddr());
			// (fi.getFileSize());
			// (fi.getCreateTimestamp());
			// (fi.getCrc32());

			return fi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title:fastdfsGetFileMate
	 * @Description:fastdfs获取文件Mate
	 * @param:@param fileIds
	 * @param:@return
	 * @return:NameValuePair[]
	 * @throws
	 */
	public NameValuePair[] fastdfsGetFileMate(String[] fileIds) {
		try {
			ClientGlobal.setG_anti_steal_token(false);
        	ClientGlobal.setG_charset("UTF-8");
        	ClientGlobal.setG_connect_timeout(2000);
        	ClientGlobal.setG_network_timeout(30000);
        	ClientGlobal.setG_secret_key("FastDFS1234567890");
        	ClientGlobal.setG_tracker_http_port(hostConfig.getTracker_http_port());
        	InetSocketAddress[] trackerServers = new InetSocketAddress[1];
        	trackerServers[0] = new InetSocketAddress(hostConfig.getTracker_server(),22122);
        	TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
        	ClientGlobal.setG_tracker_group(trackerGroup);
			//ClientGlobal.init(getConf_filename());
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			NameValuePair nvps[] = storageClient.get_metadata(fileIds[0], fileIds[1]);
			// (nvp.getName() + ":" + nvp.getValue());

			return nvps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title:fastdfsDelete
	 * @Description:fastdfs删除
	 * @param:@param fileIds
	 * @param:@return
	 * @return:int
	 * @throws
	 */
	public int fastdfsDelete(String[] fileIds) {
		int i = 1;
		try {
			ClientGlobal.setG_anti_steal_token(false);
        	ClientGlobal.setG_charset("UTF-8");
        	ClientGlobal.setG_connect_timeout(2000);
        	ClientGlobal.setG_network_timeout(30000);
        	ClientGlobal.setG_secret_key("FastDFS1234567890");
        	ClientGlobal.setG_tracker_http_port(hostConfig.getTracker_http_port());
        	InetSocketAddress[] trackerServers = new InetSocketAddress[1];
        	trackerServers[0] = new InetSocketAddress(hostConfig.getTracker_server(),22122);
        	TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
        	ClientGlobal.setG_tracker_group(trackerGroup);
			//ClientGlobal.init(getConf_filename());
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			i= storageClient.delete_file(fileIds[0], fileIds[1]);

			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 
	 * @Title:url2Strs
	 * @Description:从url中得到文件所在的组和文件路径
	 * @param:@param url
	 * @param:@return
	 * @return:String[]
	 * @throws
	 */
	public String[] url2Strs(String url){
		String[] fileIds=new String[2];
		int index = url.indexOf("/", 7);//not smaller than 7 is ok
		char[] ch = url.toCharArray();
		String lastString = String.copyValueOf(ch, index + 1, ch.length - index - 1); 
		int index2 = lastString.indexOf("/");
		fileIds[0]=lastString.substring(0, index2);
		fileIds[1]=lastString.substring(index2+1);
		return fileIds;
	}

	public static String getConf_filename() throws FileNotFoundException {
		File clsFile = ResourceUtils.getFile("classpath:fastdfs.conf");
		conf_filename=clsFile.getPath();
		return conf_filename;
	}

	public static void setConf_filename(String conf_filename) {
		BaseFastdfsDaoImpl.conf_filename = conf_filename;
	}
}
