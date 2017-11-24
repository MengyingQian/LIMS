/**
 * FileName:    HostConfig.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月8日 下午4:57:01
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月8日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.common.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.ddxq.common.cache.CacheUtil;

/**
 * ClassName:HostConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月8日 下午4:57:01 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
public class HostConfig {
	
	@Autowired
	private CacheUtil cacheUtil;
	/**
	 * 存储管理后台的域名或者ip地址
	 */
    private String boss_domainName;
    /**
     * 存放移动服务器的域名或者ip地址
     */
    private String mobile_domainName;
    /**
     * fastdfs的域名或者ip地址
     */
    private String fastdfs_domainName;
    /**
     * tracker_http_port端口号
     */
    private int tracker_http_port;
    /**
     * tracker_server服务器ip地址
     */
    private String tracker_server;
	public String getBoss_domainName() {
		String boss_domainName1 = cacheUtil.stringGet("ddxq:system:host:boss_domainName");
		if (null == boss_domainName1 || boss_domainName1.length() < 1){
			return boss_domainName;
		} else {
			return boss_domainName1;
		}
	}
	public void setBoss_domainName(String boss_domainName) {
		this.boss_domainName = boss_domainName;
	}
	public String getMobile_domainName() {
		String mobile_domainName1 = cacheUtil.stringGet("ddxq:system:host:mobile_domainName");
		if (null == mobile_domainName1 || mobile_domainName1.length() < 1){
			return mobile_domainName;
		} else {
			return mobile_domainName1;
		}		
	}
	public void setMobile_domainName(String mobile_domainName) {
		this.mobile_domainName = mobile_domainName;
	}
	public String getFastdfs_domainName() {
		String fastdfs_domainName1 = cacheUtil.stringGet("ddxq:system:host:fastdfs_domainName");
		if (null == fastdfs_domainName1 || fastdfs_domainName1.length() < 1){
			return fastdfs_domainName;
		} else {
			return fastdfs_domainName1;
		}		
	}
	public void setFastdfs_domainName(String fastdfs_domainName) {
		this.fastdfs_domainName = fastdfs_domainName;
	}
	public int getTracker_http_port() {
		return tracker_http_port;
	}
	public void setTracker_http_port(int tracker_http_port) {
		this.tracker_http_port = tracker_http_port;
	}
	public String getTracker_server() {
		return tracker_server;
	}
	public void setTracker_server(String tracker_server) {
		this.tracker_server = tracker_server;
	}
	public String getRedisName(){
		String redisName = cacheUtil.stringGet("ddxq:system:name");
		if (null == redisName || redisName.length() < 1){
			return "神州小区";
		} else {
			return redisName;
		}		
	}
	public String getRedisRes(){
		String redisRes = cacheUtil.stringGet("ddxq:system:host:res");
		if (null == redisRes || redisRes.length() < 1){
			return "res.xiaoqu.izo.cn";
		} else {
			return redisRes;
		}		
	}
	public String getRedisPub(){
		String redisPub = cacheUtil.stringGet("ddxq:system:host:pub");
		if (null == redisPub || redisPub.length() < 1){
			return "pub.xiaoqu.izo.cn";
		} else {
			return redisPub;
		}		
	}
}
