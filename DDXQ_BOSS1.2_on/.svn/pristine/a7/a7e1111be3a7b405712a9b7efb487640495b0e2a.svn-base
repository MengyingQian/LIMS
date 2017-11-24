package com.ddxq.base.dao.hbase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Result;

public interface BaseHBaseDAO {
	//查询结果
		public void creatTable(String tableName, String[] family) throws Exception;

		/** 
	     * 通过表名  tableName 和 列族 和列 获取一个数据 
	     * @param tableName 
	     * @param rowKey 
	     * @param cloumn 
	     * @param value 
	     * @return 
	     */  
		 public void addData(String rowKey, String tableName,
		            String[][] column, String[][] value)
		            throws IOException;
		 
		 
		 public void addData(String rowKey, String tableName,
		            List<Map<String, String>> rowCfs) throws IOException;
		
		/**
		 * 
		 * @param tableName
		 * @param rowKey
		 * @return
		 * @throws IOException
		 */
		public Result getResult(String tableName, String rowKey)
		            throws IOException; 

		/**
	     * 遍历查询hbase表
	     * 
	     * @tableName 表名
	     */
	    public  void getResultScann(String tableName) throws IOException;
	    
	    /*
	     * 遍历查询hbase表
	     * 
	     * @tableName 表名
	     */
	    public void getResultScann(String tableName, String start_rowkey,
	            String stop_rowkey) throws IOException;
	    public Map<String,Object> getResultScannMap(String tableName, String start_rowkey,
	            String stop_rowkey) throws IOException;
	    public List< Map<String,Object> >getResultScanList(String tableName, String start_rowkey,
	            String stop_rowkey) throws IOException;
	    
	    /*
	     * 查询表中的某一列
	     * 
	     * @tableName 表名
	     * 
	     * @rowKey rowKey
	     */
	    public  void getResultByColumn(String tableName, String rowKey,
	            String familyName, String columnName) throws IOException;
	    
	    /*
	     * 更新表中的某一列
	     * 
	     * @tableName 表名
	     * 
	     * @rowKey rowKey
	     * 
	     * @familyName 列族名
	     * 
	     * @columnName 列名
	     * 
	     * @value 更新后的值
	     */
	    public  void updateTable(String tableName, String rowKey,
	            String familyName, String columnName, String value)
	            throws IOException;
	    
	    /*
	     * 查询某列数据的多个版本
	     * 
	     * @tableName 表名
	     * 
	     * @rowKey rowKey
	     * 
	     * @familyName 列族名
	     * 
	     * @columnName 列名
	     */
	    public  void getResultByVersion(String tableName, String rowKey,
	            String familyName, String columnName, int maxVersions) throws IOException;
	    
	    /*
	     * 删除指定的列
	     * 
	     * @tableName 表名
	     * 
	     * @rowKey rowKey
	     * 
	     * @familyName 列族名
	     * 
	     * @columnName 列名
	     */
	    public  void deleteColumn(String tableName, String rowKey,
	            String falilyName, String columnName) throws IOException;
	    
	    /*
	     * 删除指定的列
	     * 
	     * @tableName 表名
	     * 
	     * @rowKey rowKey
	     */
	    public  void deleteAllColumn(String tableName, String rowKey)
	            throws IOException;
	    
	    /*
	     * 删除表
	     * 
	     * @tableName 表名
	     */
	    public  void deleteTable(String tableName) throws IOException;
}
