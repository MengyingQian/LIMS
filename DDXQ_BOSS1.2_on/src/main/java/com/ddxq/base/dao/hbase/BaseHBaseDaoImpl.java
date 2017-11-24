package com.ddxq.base.dao.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("baseHBaseDAO")
public class BaseHBaseDaoImpl implements BaseHBaseDAO{
	//声明logger
	private final static Logger logger = LoggerFactory.getLogger(BaseHBaseDaoImpl.class);   
    // 声明静态配置
    static Configuration conf = null;
    static HTablePool pool = null;
    static {
        conf = HBaseConfiguration.create();
        pool = new HTablePool();
    }

    /*
     * 创建表
     * 
     * @tableName 表名
     * 
     * @family 列族列表
     */
    public void creatTable(String tableName, String[] family)
            throws Exception {
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor desc = new HTableDescriptor(tableName);
        for (int i = 0; i < family.length; i++) {
            desc.addFamily(new HColumnDescriptor(family[i]));
        }
        if (admin.tableExists(tableName)) {
        	logger.error("table Exists!");
            System.exit(0);
        } else {
            admin.createTable(desc);
            logger.debug("create table Success!");
        }
    }

    /*
     * 为表添加数据（适合知道有多少列族的固定表）
     * 
     * @rowKey rowKey
     * 
     * @tableName 表名
     * 
     * @column1 第一个列族列表
     * 
     * @value1 第一个列的值的列表
     * 
     */
    public void addData(String rowKey, String tableName,
            String[][] column, String[][] value)
            throws IOException {
        Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
        HTableInterface table = pool.getTable(tableName);// HTabel负责跟记录相关的操作如增删改查等//
                                                                    // 获取表
        HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // 获取所有的列族
                .getColumnFamilies();

        for (int i = 0; i < columnFamilies.length; i++) {
            String familyName = columnFamilies[i].getNameAsString(); // 获取列族名
            for (int j = 0; j < column[i].length; j++) {
                put.add(Bytes.toBytes(familyName),
                        Bytes.toBytes(column[i][j]), Bytes.toBytes(value[i][j]));
            }
        }
        table.put(put);
        logger.debug("add data Success!");
    }
    
    /*
     * 为表添加数据（适合知道有多少列族的固定表）
     * 
     * @rowKey rowKey
     * 
     * @tableName 表名
     * 
     * @column1 第一个列族列表
     * 
     * @value1 第一个列的值的列表
     * 
     */
    public void addData(String rowKey, String tableName,
            List<Map<String, String>> rowCfs)
            throws IOException {
        Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
        HTableInterface table = pool.getTable(tableName);// HTabel负责跟记录相关的操作如增删改查等//
                                                                    // 获取表
        HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // 获取所有的列族
                .getColumnFamilies();

        for (int i = 0; i < columnFamilies.length; i++) {
            String familyName = columnFamilies[i].getNameAsString(); // 获取列族名
            //取出第i个列族的Map值
            Map<String, String> cf = rowCfs.get(i);
            //遍历Map插入列族的列值
            for (Map.Entry<String, String> entry : cf.entrySet()){
            	put.add(Bytes.toBytes(familyName),
                        Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
            }
        }
        table.put(put);
        logger.debug("add data Success!");
    }
    

    /*
     * 根据rwokey查询
     * 
     * @rowKey rowKey
     * 
     * @tableName 表名
     */
    public Result getResult(String tableName, String rowKey)
            throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTableInterface table = pool.getTable(tableName);
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
        	logger.debug("family:" + Bytes.toString(kv.getFamily()));
        	logger.debug("qualifier:" + Bytes.toString(kv.getQualifier()));
        	logger.debug("value:" + Bytes.toString(kv.getValue()));
        	logger.debug("Timestamp:" + kv.getTimestamp());
        	logger.debug("-------------------------------------------");
        }
        return result;
    }

    /*
     * 遍历查询hbase表
     * 
     * @tableName 表名
     */
    public void getResultScann(String tableName) throws IOException {
        Scan scan = new Scan();
        ResultScanner rs = null;
        HTableInterface table = pool.getTable(tableName);
        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue kv : r.list()) {
                    logger.debug("row:" + Bytes.toString(kv.getRow()));
                    logger.debug("family:"
                            + Bytes.toString(kv.getFamily()));
                    logger.debug("qualifier:"
                            + Bytes.toString(kv.getQualifier()));
                    System.out
                            .println("value:" + Bytes.toString(kv.getValue()));
                    logger.debug("timestamp:" + kv.getTimestamp());
                    System.out
                            .println("-------------------------------------------");
                }
            }
        } finally {
            rs.close();
        }
    }

    /*
     * 遍历查询hbase表
     * 
     * @tableName 表名
     */
    public void getResultScann(String tableName, String start_rowkey,
            String stop_rowkey) throws IOException {
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(start_rowkey));
        scan.setStopRow(Bytes.toBytes(stop_rowkey));
        ResultScanner rs = null;
        HTableInterface table = pool.getTable(tableName);
        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue kv : r.list()) {
                    logger.debug("row:" + Bytes.toString(kv.getRow()));
                    logger.debug("family:"
                            + Bytes.toString(kv.getFamily()));
                    logger.debug("qualifier:"
                            + Bytes.toString(kv.getQualifier()));
                    System.out
                            .println("value:" + Bytes.toString(kv.getValue()));
                    logger.debug("timestamp:" + kv.getTimestamp());
                    System.out
                            .println("-------------------------------------------");
                }
            }
        } finally {
            rs.close();
        }
    }

    /*
     * 查询表中的某一列
     * 
     * @tableName 表名
     * 
     * @rowKey rowKey
     */
    public void getResultByColumn(String tableName, String rowKey,
            String familyName, String columnName) throws IOException {
    	HTableInterface table = pool.getTable(tableName);
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)); // 获取指定列族和列修饰符对应的列
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            logger.debug("family:" + Bytes.toString(kv.getFamily()));
            System.out
                    .println("qualifier:" + Bytes.toString(kv.getQualifier()));
            logger.debug("value:" + Bytes.toString(kv.getValue()));
            logger.debug("Timestamp:" + kv.getTimestamp());
            logger.debug("-------------------------------------------");
        }
    }

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
    public void updateTable(String tableName, String rowKey,
            String familyName, String columnName, String value)
            throws IOException {
    	HTableInterface table = pool.getTable(tableName);
        Put put = new Put(Bytes.toBytes(rowKey));
        put.add(Bytes.toBytes(familyName), Bytes.toBytes(columnName),
                Bytes.toBytes(value));
        table.put(put);
        logger.debug("update table Success!");
    }

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
    public void getResultByVersion(String tableName, String rowKey,
            String familyName, String columnName, int maxVersions) throws IOException {
    	HTableInterface table = pool.getTable(tableName);
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
        get.setMaxVersions(maxVersions);
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            logger.debug("family:" + Bytes.toString(kv.getFamily()));
            System.out
                    .println("qualifier:" + Bytes.toString(kv.getQualifier()));
            logger.debug("value:" + Bytes.toString(kv.getValue()));
            logger.debug("Timestamp:" + kv.getTimestamp());
            logger.debug("-------------------------------------------");
        }
        /*
         * List<?> results = table.get(get).list(); Iterator<?> it =
         * results.iterator(); while (it.hasNext()) {
         * logger.debug(it.next().toString()); }
         */
    }

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
    public void deleteColumn(String tableName, String rowKey,
            String falilyName, String columnName) throws IOException {
    	HTableInterface table = pool.getTable(tableName);
        Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
        deleteColumn.deleteColumns(Bytes.toBytes(falilyName),
                Bytes.toBytes(columnName));
        table.delete(deleteColumn);
        logger.debug(falilyName + ":" + columnName + "is deleted!");
    }

    /*
     * 删除指定的列
     * 
     * @tableName 表名
     * 
     * @rowKey rowKey
     */
    public void deleteAllColumn(String tableName, String rowKey)
            throws IOException {
    	HTableInterface table = pool.getTable(tableName);
        Delete deleteAll = new Delete(Bytes.toBytes(rowKey));
        table.delete(deleteAll);
        logger.debug("all columns are deleted!");
    }

    /*
     * 删除表
     * 
     * @tableName 表名
     */
    public void deleteTable(String tableName) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
        logger.debug(tableName + "is deleted!");
    }

	@Override
	 public List< Map<String,Object> >getResultScanList(String tableName, String start_rowkey,
	            String stop_rowkey) throws IOException {
	        Scan scan = new Scan();
	        scan.setStartRow(Bytes.toBytes(start_rowkey));
	        scan.setStopRow(Bytes.toBytes(stop_rowkey));
	        ResultScanner rs = null;
	        HTableInterface table = pool.getTable(tableName);
	        List< Map<String,Object> > list = new ArrayList();
	     
	        try {
	            rs = table.getScanner(scan);
	            for (Result r : rs) {
	            	
	                for (KeyValue kv : r.list()) {
	                	
	                	Map<String,Object> map = new HashMap();
	                	map.put(Bytes.toString(kv.getQualifier()), Bytes.toString(kv.getValue()));
	                	list.add(map);
	                	
	                	
	                    logger.debug("row:" + Bytes.toString(kv.getRow()));
	                    logger.debug("family:"
	                            + Bytes.toString(kv.getFamily()));
	                    logger.debug("qualifier:"
	                            + Bytes.toString(kv.getQualifier()));
	                    System.out
	                            .println("value:" + Bytes.toString(kv.getValue()));
	                    logger.debug("timestamp:" + kv.getTimestamp());
	                    System.out
	                            .println("-------------------------------------------");
	                }
	                
	            }
	        } finally {
	            rs.close();
	        }
	        return list;
	        
	    }

	@Override
    public Map<String,Object> getResultScannMap(String tableName, String start_rowkey,
            String stop_rowkey) throws IOException {
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(start_rowkey));
        scan.setStopRow(Bytes.toBytes(stop_rowkey));
        ResultScanner rs = null;
        HTableInterface table = pool.getTable(tableName);
        Map<String,Object> map = new HashMap();
        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue kv : r.list()) {
                	
                	map.put(Bytes.toString(kv.getQualifier()), Bytes.toString(kv.getValue()));
                    logger.debug("row:" + Bytes.toString(kv.getRow()));
                    logger.debug("family:"
                            + Bytes.toString(kv.getFamily()));
                    logger.debug("qualifier:"
                            + Bytes.toString(kv.getQualifier()));
                    System.out
                            .println("value:" + Bytes.toString(kv.getValue()));
                    logger.debug("timestamp:" + kv.getTimestamp());
                    System.out
                            .println("-------------------------------------------");
                }
                
            }
        } finally {
            rs.close();
        }
        return map;
        
    }  
    


//    public static void main(String[] args) throws Exception {
//
//        // 创建表
//        String tableName = "blog2";
//        String[] family = { "article", "author" };
//        // creatTable(tableName, family);
//
//        // 为表添加数据
//
//        String[] column1 = { "title", "content", "tag" };
//        String[] value1 = {
//                "Head First HBase",
//                "HBase is the Hadoop database. Use it when you need random, realtime read/write access to your Big Data.",
//                "Hadoop,HBase,NoSQL" };
//        String[] column2 = { "name", "nickname" };
//        String[] value2 = { "nicholas", "lee" };
//        addData("rowkey1", "blog2", column1, value1, column2, value2);
//        addData("rowkey2", "blog2", column1, value1, column2, value2);
//        addData("rowkey3", "blog2", column1, value1, column2, value2);
//
//        // 遍历查询
//        getResultScann("blog2", "rowkey4", "rowkey5");
//        // 根据row key范围遍历查询
//        getResultScann("blog2", "rowkey4", "rowkey5");
//
//        // 查询
//        getResult("blog2", "rowkey1");
//
//        // 查询某一列的值
//        getResultByColumn("blog2", "rowkey1", "author", "name");
//
//        // 更新列
//        updateTable("blog2", "rowkey1", "author", "name", "bin");
//
//        // 查询某一列的值
//        getResultByColumn("blog2", "rowkey1", "author", "name");
//
//        // 查询某列的多版本
//        getResultByVersion("blog2", "rowkey1", "author", "name");
//
//        // 删除一列
//        deleteColumn("blog2", "rowkey1", "author", "nickname");
//
//        // 删除所有列
//        deleteAllColumn("blog2", "rowkey1");
//
//        // 删除表
//        deleteTable("blog2");
//
//    }
}