package com.ddxq.base.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ddxq.base.service.BaseService;
import com.ddxq.boss.base.util.md5.MD5Util;
import com.ddxq.common.constant.JmsConstant;
import com.ddxq.common.log.ConLogPojo;
import com.ddxq.common.log.DaoLogPojo;
import com.ddxq.common.log.ErrorLogPojo;
import com.ddxq.common.log.ServLogPojo;
import com.ddxq.common.log.SystemLog;
import com.ddxq.common.page.PageObject;
import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;

/**
 * 写service实现类用来提供最基本的数据库事务性写操作，不含任何业务逻辑
 * @author zxz
 * @version 1.0.0
 * @since 2015-02-01
 * */
@SuppressWarnings("unchecked")
@Service("baseService")
public class BaseServiceImpl implements
		BaseService {

	@Autowired
	private BaseDAO baseDAO;//dao基类接口
	@Autowired 
	private SystemLog systemLog;
	@Autowired
	private BaseHBaseDAO hbaseDao;
	@Autowired
	private MD5Util mD5Util;
	/**
	 * 修改指定服务器的ID信息
	 * 
	 * @param args
	 *            执行SQL需要的参数
	 * @return boolean
	 */
	public boolean updateIdInfo(Object[] args){
		String sql = " update idtable set laststr = ? ,newid = ? where ipandport = ? ";
		return baseDAO.executeCommand(sql, args);
	}
	
	/**
	 * 插入指定服务器的ID信息
	 * 
	 * @param args
	 *            执行SQL需要的参数
	 * @return boolean
	 */
	public boolean insertIdInfo(Object[] args){
		String sql = " insert into idtable ( ipandport,laststr,newid ) values(?,?,?) ";
		return baseDAO.executeCommand(sql, args);
	}
	
	/**
	 * 获取指定服务器的ID信息
	 * 
	 * @param args
	 *            执行SQL需要的参数
	 * @return Map
	 */
	public Map queryForIdInfo(String args){
		String sql = " select * from idtable where ipandport = :ipandport ";
		Map map = new HashMap();
		map.put("ipandport", args);
		return baseDAO.queryForMap(sql, map);
	}

	/**
	 * 获取指定页的记录,每页显示的条数使用默认值
	 * 
	 * @param sql
	 *            要执行的sql
	 * @param currentPage
	 *            当前页
	 * @return PageObject
	 */
	public PageObject queryForMPageList(String sql, Object[] args, Map req) {
		return baseDAO.queryForMPageList(sql, args, req);
	}

	/**
	 * 如果cacheName缓存集中的CacheKey有值，返回CacheKey的内容，否则执行SQL查询，返回List。
	 * reWrite为true时强制覆写缓存
	 * 
	 *
	 *
	 * @param CacheKey
	 *            缓存的key值
	 * @param reWrite
	 *            缓存是否强制覆写
	 * @param sql
	 *            执行sql的内容
	 * @param args
	 *            执行sql的参数
	 * @return List类型的数据集
	 */
	public List queryForList(String sql, Object[] args) {
		return baseDAO.queryForList(sql, args);
	}

	/**
	 * 如果cacheName缓存集中的CacheKey有值，返回CacheKey的内容，否则执行SQL查询，返回List，
	 * reWrite为true时强制覆写缓存
	 * 
	 *
	 *
	 * @param CacheKey
	 *            缓存的key值
	 * @param reWrite
	 *            缓存是否覆写
	 * @param sql
	 *            执行sql的内容
	 * @param map
	 *            执行sql的参数
	 * @return List类型的数据集
	 */
	public List queryForList(String sql, Map map) {
		return baseDAO.queryForList(sql, map);
	}



	/**
	 * 获取指定页的记录
	 * 
	 * @param sql
	 *            要执行的sql
	 * @param args
	 *            要执行sql的参数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示多少条记录
	 * @return PageObject
	 */
	public PageObject queryForPageList(String sql, Object[] args,
			int currentPage, int pageSize) {
		return baseDAO.queryForPageList(sql, args, currentPage, pageSize);
	}

	/**
	 * 查询单个记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param args
	 *            执行sql的参数
	 * @return
	 */
	public Map queryForMap(String sql, Object[] args) {
		return baseDAO.queryForMap(sql, args);
	}

	/**
	 * 查询单个记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @return
	 */
	public Map queryForMap(String sql) {
		return baseDAO.queryForMap(sql);
	}
	
	/**
	 * 查询一组记录
	 * @param sql
	 *            执行sql的内容
	 * @return
	 */
	public List queryForList(String sql){
		return baseDAO.queryForList(sql);
	}

	/**
	 * 查询单个记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param map
	 *            执行sql的参数
	 * @return
	 */
	public Map queryForMap(String sql, Map map) {
		return baseDAO.queryForMap(sql, map);
	}

	/**
	 * 获取指定页的记录,每页显示的条数使用默认值
	 * 
	 * @param sql
	 *            要执行的sql
	 * @param map
	 *            要执行sql的参数
	 * @param currentPage
	 *            当前页
	 * @return PageObject
	 */
	public PageObject queryForNamedPageList(String sql, Map map, int currentPage) {
		return baseDAO.queryForNamedPageList(sql, map, currentPage);
	}

	/**
	 * 获取指定页的记录,每页显示的条数使用默认值
	 * 
	 * @param sql
	 *            要执行的sql
	 * @param currentPage
	 *            当前页
	 * @return PageObject
	 */
	public PageObject queryForPageList(String sql, int currentPage) {
		return baseDAO.queryForPageList(sql, currentPage);
	}

	/**
	 * 获取指定页的记录,每页显示的条数使用设定值
	 * 
	 * @param sql
	 *            要执行的sql
	 * @param map
	 *            执行参数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示的条数
	 * @return PageObject
	 */
	public PageObject queryForNamedPageList(String sql, Map map,
			int currentPage, int pageSize) {
		return baseDAO.queryForNamedPageList(sql, map, currentPage, pageSize);
	}

	/**
	 * 获取指定页的记录,每页显示的条数使用默认值
	 * 
	 * @param sql
	 *            要执行的sql
	 * @param map
	 *            要执行sql的参数
	 * @param currentPage
	 *            当前页
	 * @return PageObject
	 */
	public PageObject queryForPageList(String sql, Object[] args,
			int currentPage) {
		return baseDAO.queryForPageList(sql, args, currentPage);
	}

	/**
	 * 执行SQL，获取查询到的所有记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param map
	 *            执行sql的参数
	 * @return
	 */
	public List getNamedRecordSet(String sql, Map map)
			throws DataAccessException {
		return baseDAO.getNamedRecordSet(sql, map);
	}

	/**
	 * 执行SQL，获取查询到的第1行记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param map
	 *            执行sql的参数
	 * @return
	 */
	public Map getNamedFirstRowValue(String sql, Map map)
			throws DataAccessException {
		return baseDAO.getNamedFirstRowValue(sql, map);
	}

	/**
	 * 执行SQL，执行insert,update,delete操作
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param map
	 *            执行sql的参数
	 * @return
	 */
	public boolean executeNamedCommand(String sql, Map map)
			throws DataAccessException {
		return baseDAO.executeNamedCommand(sql, map);
	}

	/**
	 * 执行SQL，获取查询到的记录总数
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param map
	 *            执行sql的参数
	 * @return
	 */
	public long getNamedRecordCount(String sql, Map map)
			throws DataAccessException {
		return baseDAO.getNamedRecordCount(sql, map);
	}

	/**
	 * 执行SQL，查询是否存在记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param map
	 *            执行sql的参数
	 * @return
	 */
	public boolean findNamedSQL(String sql, Map map) throws DataAccessException {
		return baseDAO.findNamedSQL(sql, map);
	}

	/**
	 * 执行SQL，获取查询到的所有记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param args
	 *            执行sql的参数
	 * @return
	 */
	public List getRecordSet(String sql, Object[] args)
			throws DataAccessException {
		return baseDAO.getRecordSet(sql, args);
	}

	/**
	 * 执行SQL，获取查询到的第1行记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param args
	 *            执行sql的参数
	 * @return
	 */
	public Map getFirstRowValue(String sql, Object[] args)
			throws DataAccessException {
		return baseDAO.getFirstRowValue(sql, args);
	}

	/**
	 * 执行SQL，执行insert,update,delete操作
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param args
	 *            执行sql的参数
	 * @return
	 */
	public boolean executeCommand(String sql, Object[] args)
			throws DataAccessException {
		return baseDAO.executeCommand(sql, args);
	}

	/**
	 * 执行SQL，获取查询到的记录总数
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param args
	 *            执行sql的参数
	 * @return
	 */
	public long getRecordCount(String sql, Object[] args)
			throws DataAccessException {
		return baseDAO.getRecordCount(sql, args);
	}

	/**
	 * 执行SQL，查询是否存在记录
	 * 
	 * @param sql
	 *            执行sql的内容
	 * @param args
	 *            执行sql的参数
	 * @return
	 */
	public boolean findSQL(String sql, Object[] args)
			throws DataAccessException {
		return baseDAO.findSQL(sql, args);
	}
	
	/**
	 * 执行SQL，获取查询到的记录总数
	 * @param sql
	 *            要执行的sql
	 * @return
	 */
	public long getRecordCount(String sql) throws DataAccessException{
		return baseDAO.getRecordCount(sql);
	}
	/**
	 * 将日志文件传送到Hbase中
	 * <p>Title: saveBlogToHbase</p>
	 * <p>Description: </p>
	 * @param map
	 * @return
	 * @throws Throwable 
	 * @see com.ddxq.base.service.BaseService#saveBlogToHbase(java.util.Map)
	 */
	@Override
	public boolean saveBlogToHbase(Map<String, Object> map) throws Throwable {
		
		
		String tableName = null;
		String rowKey = null;//根据logType类型的不同，选择存储到不同的 表格中
		String logType = (String) map.get("logType");
	
		if(logType.equals(JmsConstant.JMS_CONLOG)){
			//control_log的处理函数，数据封装
			tableName = "controller_log";
			ConLogPojo log =(ConLogPojo)map.get("Log");
			rowKey=mD5Util.StringToMD5_16(log.getAct_level())+mD5Util.StringToMD5_16(log.getUserid())+mD5Util.StringToMD5_16(log.getTime());
			//列名		
			String[] column1={"time","action_level","server_IP","server_port","user_IP",
					"action_type","action_info","action_desc","action_result","jsp_name",
					"request_url","stay_time","nickname","userid","money"};
			String [][]column = {column1,null};//4/29/2016, 4:30:35 PM	
			//列值
			String[] value1={log.getTime(),log.getAct_level(),log.getServer_IP(),log.getServer_port(),log.getUser_IP()
					,log.getAction_type(),log.getAction_info(),log.getAction_desc(),log.getAction_result(),log.getJsp_name(),
					log.getRequest_url(),log.getStay_time(),log.getNickname(),log.getUserid(),log.getMoney()};
			//hbase的value值不能为null，用---来代替
			for(int i=0; i<value1.length; i++){
				if(null==value1[i] || "".equals(value1[i]))
					value1[i]="---";
			}
			String [][]value = {value1,null};
			try {
				hbaseDao.addData(rowKey, tableName, column, value);
				return true;
			} catch (IOException e) {
				return false;
			}				
		}else if(logType.equals(JmsConstant.JMS_SERLOG)){
			//service_log的处理函数
			tableName = "service_log";
			ServLogPojo log =(ServLogPojo)map.get("Log");
			rowKey=mD5Util.StringToMD5_16(log.getAct_level())+mD5Util.StringToMD5_16(log.getUserid())+mD5Util.StringToMD5_16(log.getTime());
			//列名	
			String[] column1={"time","action_level","action_type","server_IP","server_port",
					"serfrom_IP","serfrom_port","action_info","action_desc","action_result",
					"action_time","nickname","userid","money"};
			String [][]column = {column1,null};//4/29/2016, 4:30:35 PM	
			//列值
			String[] value1={log.getTime(),log.getAct_level(),log.getAction_type(),log.getServer_IP(),log.getServer_port(),
					log.getSerfrom_IP(),log.getSerfrom_port(),log.getAction_info(),log.getAction_desc(),log.getAction_result(),
					log.getAction_time(),log.getNickname(),log.getUserid(),log.getMoney()};
			//hbase的value值不能为null，用---来代替
			for(int i=0; i<value1.length; i++){
				if(null==value1[i] || "".equals(value1[i]))
					value1[i]="---";
			}
			String [][]value = {value1,null};	
			try {
				hbaseDao.addData(rowKey, tableName, column, value);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}				
		}else if(logType.equals(JmsConstant.JMS_DAOLOG)){
			//dao_log的处理函数
			tableName = "dao_log";
			DaoLogPojo log =(DaoLogPojo)map.get("Log");
			rowKey=mD5Util.StringToMD5_16(log.getAct_level())+mD5Util.StringToMD5_16(log.getUserid())+mD5Util.StringToMD5_16(log.getTime());
			//列名	
			String[] column1={"time","action_level","action_type","data_name","server_IP",
					"server_port","data_IP","data_port","action_info","action_desc",
					"action_language","action_result","action_time","nickname","userid",
					"money"};
			String [][]column = {column1,null};//4/29/2016, 4:30:35 PM	
			//列值
			String[] value1={log.getTime(),log.getAct_level(),log.getAction_type(),log.getData_name(),log.getServer_IP(),
					log.getServer_port(),log.getData_IP(),log.getData_port(),log.getAction_info(),log.getAction_desc(),
					log.getAction_language(),log.getAction_result(),log.getAction_time(),log.getNickname(),log.getUserid(),
					log.getMoney()};
			//hbase的value值不能为null，用---来代替
			for(int i=0; i<value1.length; i++){
				if(null==value1[i] || "".equals(value1[i]))
					value1[i]="---";
			}
			String [][]value = {value1,null};	
			try {
				hbaseDao.addData(rowKey, tableName, column, value);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}				
		}else {
			//error_log处理函数
			tableName  ="error_log";
			ErrorLogPojo log =(ErrorLogPojo)map.get("Log");
			rowKey=mD5Util.StringToMD5_16(log.getUserid())+mD5Util.StringToMD5_16(log.getTime());
			//列名	
			String[] column1={"time","server_IP","server_port","action_info","action_desc",
				     "nickname","userid","error_message"};
			String [][]column = {column1,null};//4/29/2016, 4:30:35 PM	
			//列值
			String[] value1={log.getTime(),log.getServer_IP(),log.getServer_port(),log.getAction_info(),log.getAction_desc(),
					log.getNickname(),log.getUserid(),log.getError_massage()};
			//hbase的value值不能为null，用---来代替
			for(int i=0; i<value1.length; i++){
				if(null==value1[i] || "".equals(value1[i]))
					value1[i]="---";
			}
			String [][]value = {value1,null};	
			try {
				hbaseDao.addData(rowKey, tableName, column, value);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
		}
	}
}
