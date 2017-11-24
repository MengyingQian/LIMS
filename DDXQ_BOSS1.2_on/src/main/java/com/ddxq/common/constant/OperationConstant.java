package com.ddxq.common.constant;

/**
 * 操作类型常量类，
 * 在记录日志的时候，需要明确日志操作类型是什么
 * 其他需要进行操作类型状态区分的时候，也要先使用该类进行类型注册,前缀OPERATION_
 * @author zxz
 * @version 1.0.0
 * @since 2015-02-01
 * */
public class OperationConstant {

	/**
	 * serivce层查询操作
	 */
	public static final String OPERATION_SER_SELECT = "operation_select";
	
	/**
	 * serivce层删除操作
	 */
	public static final String OPERATION_SER_DELETE = "operation_delete";
	
	/**
	 * serivce层修改操作
	 */
	public static final String OPERATION_SER_UPDATE = "operation_update";
	
	/**
	 * serivce层新增操作
	 */
	public static final String OPERATION_SER_ADD = "operation_add";
	/**
	 * dao层查询操作
	 */
	public static final String OPERATION_DAO_SELECT = "operation_select";
	
	/**
	 * dao层删除操作
	 */
	public static final String OPERATION_DAO_DELETE = "operation_delete";
	
	/**
	 * dao层修改操作
	 */
	public static final String OPERATION_DAO_UPDATE = "operation_update";
	
	/**
	 * dao层新增操作
	 */
	public static final String OPERATION_DAO_ADD = "operation_add";
	
	/**
	 * 统计分析操作
	 */
	public static final String OPERATION_ANALYSE = "operation_analyse";
	
	/**
	 * 数据导出操作
	 */
	public static final String OPERATION_EXPORT = "operation_export";
	
	/**
	 * 数据导入操作
	 */
	public static final String OPERATION_IMPORT = "operation_import";
	
	/**
	 * 调用外部接口操作
	 */
	public static final String OPERATION_OUTUSE = "operation_outuse";
	
	/**
	 * 用户http访问
	 */
	public static final String OPERATION_WEBACCESS = "operation_webaccess";
	
	/**
	 * 开发/测试调试操作
	 */
	public static final String OPERATION_DEVANDTEST = "operation_devandtest";
	/**
	 * 用户操作级别
	 */
	public static final String OPERTION_ACTIONLEVEL_INFO = "INFO";
	public static final String OPERTION_ACTIONLEVEL_DEBUG = "DEBUG";
	public static final String OPERTION_ACTIONLEVEL_ERROR = "ERROR";

	
	/**
	 * 用户操作结果
	 */
	public static final String OPERTION_ACTION_RESULT_SUCCESS = "SUCCESS";
	public static final String OPERTION_ACTION_RESULT_FAILURE = "FAILURE";
	public static final String OPERTION_ACTION_RESULT_DELAY = "DELAY";
	
}
