package com.ddxq.common.log;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.constant.JmsConstant;
import com.ddxq.common.constant.OperationConstant;
import com.ddxq.common.id.IdFactory;
/**
 * 记录日志的基类，提供debug，error和过程中的记录日志方法
 * 
 * @author zxz
 * @version 1.0
 * @since 2014-12-24
 * */
@Component("systemLog")
public class SystemLog {
	@Autowired
	private IdFactory idFactory;//ID生成器，记录日志时需要进行排序，方便切分
	
	private InetAddress netAddress = getInetAddress();  
	private String tomcatPort = getTomcatPort();
	SystemLog systemlog;
	/**
	 * 记录调试信息，比如运行的SQL，或者其他
	 * 
	 * @param className      记录日志的类
	 * @param message        详细日志信息
	 * */
	public void debugLog(Class<?> className, String message){
		Logger log = LoggerFactory.getLogger(className);  
		if(log.isDebugEnabled()){
			log.debug("MyDebug------>" + message + "<------MyDebug");
		}
	}
	/**
	 * 
	 * @Title: infoLog
	 * @Description: 原始的infolog
	 * @param: @param className
	 * @param: @param userName
	 * @param: @param userAlias
	 * @param: @param userIP
	 * @param: @param modelName
	 * @param: @param operationType
	 * @param: @param message   
	 * @return: void   
	 * @throws
	 */
	public void infoLog(Class<?> className, String userName, String userAlias, String userIP, String modelName,String operationType,String message){
		long id = idFactory.nextId();//生成日志ID
		String ip = getHostIp(netAddress);//System.getProperty("localaddress");//获取当前服务器IP地址
		int port = Integer.parseInt(tomcatPort);
//		int port=Integer.parseInt(System.getProperty("reyo.localPort"));//需要自己配置
		Logger log = LoggerFactory.getLogger(className);  
		if(log.isInfoEnabled()){
			log.info(">>>>>>[" + ip + ":" + port + "]" + "--->" + "(" + userName + "-" + userAlias + "-"+ userIP +")" + "--->" + "{" + modelName + "}" + "--->" + "#" + operationType + "#" + "--->" + "*" + message + "*");
		}
		//SystemLogPojo systemLogPojo = new SystemLogPojo(id,className,userName,userAlias,ip,port,modelName,operationType,message);
		//mongoTemplate.save(systemLogPojo);//用户行为日志，暂时放入mongodb中去，后期也可以考虑放入文件系统或者数据库，便于对接大数据
	}
	/**
 	* 
 	* @Title: conInfoLog
 	* @Description: TODO(这里用一句话描述这个方法的作用)
 	* @ @param className
 	* @ @param request
 	* @ @param weixinUser
 	* @ @param action_level（在常量定义里面寻找相应的常量，如果没有可以自己添加）OperationConstant.
 	* @ @param action_type（在常量定义里面寻找相应的常量，如果没有可以自己添加）OperationConstant.
 	* @ @param action_desc 操作描述，自己定义的字符串，比如说“访问注册的页面”  
 	* @return: void   
 	* @throws
 	*/
	public void conInfoLog(Class className,HttpServletRequest request, WeiXinUserPojo weixinUser, String action_level,
			String action_type,String action_desc){
		long id = idFactory.nextId();//生成日志ID
		Logger log = LoggerFactory.getLogger(className); 
		boolean flag = true;
		if(null==weixinUser){//用户信息为空，添加需要的参数
			flag = false;
		}
		String action_result =  OperationConstant.OPERTION_ACTION_RESULT_SUCCESS;//当执行到这一步，默认执行结果正确 
		try {
			String start_time = request.getParameter("start_time");//如果jsp页面没有获得stat_time,就取当前时间
			if(start_time==null || "".equals(start_time)){ 	
				Long control_time = System.currentTimeMillis();
				start_time =Long.toString(control_time);
			}
			ConLogPojo Log = null;
			if(flag){
				if(log.isInfoEnabled()){//日志中存的记录	
					log.info(className+":"+weixinUser.getOpenid()+","+start_time+","+action_level+getHostIp(netAddress)+","+Integer.parseInt(tomcatPort)+","+InetAddress.getLocalHost().toString()+","+action_type+","+className+","+action_result+","+request.getParameter("jspname")+","+request.getRequestURL().toString()+","+request.getParameter("stay_time")+","+weixinUser.getNick_name()+","+weixinUser.getUser_id()+","+request.getParameter("money"));
				}
			    Log = new ConLogPojo(start_time,action_level,getHostIp(netAddress),System.getProperty("reyo.localPort"),InetAddress.getLocalHost().toString(),
							action_type,className.toString(),action_desc,action_result,request.getParameter("jspname"),
							request.getRequestURL().toString(),request.getParameter("stay_time"),weixinUser.getNick_name(),Long.toString(weixinUser.getUser_id()),request.getParameter("money"));
			}else{
				if(log.isInfoEnabled()){//日志中存的记录	
					log.info(className+":"+"-1"+","+start_time+","+action_level+getHostIp(netAddress)+","+Integer.parseInt(tomcatPort)+","+InetAddress.getLocalHost().toString()+","+action_type+","+className+","+action_result+","+request.getParameter("jspname")+","+request.getRequestURL().toString()+","+request.getParameter("stay_time")+","+"visitor"+","+"-1"+","+request.getParameter("money"));
				}
			    Log = new ConLogPojo(start_time,action_level,getHostIp(netAddress),System.getProperty("reyo.localPort"),InetAddress.getLocalHost().toString(),
							action_type,className.toString(),action_desc,action_result,request.getParameter("jspname"),
							request.getRequestURL().toString(),request.getParameter("stay_time"),"visitor","-1",request.getParameter("money"));
			}
	
			Map<String, Serializable> msgMap = new HashMap<String, Serializable>();
			msgMap.put("Log",(Serializable)Log);
			msgMap.put("operationType",JmsConstant.JMS_HBASE);
			msgMap.put("logType", JmsConstant.JMS_CONLOG);
		
		} catch (UnknownHostException e) {//错误日志记录
			log.error("controller_log error,message"+e.getMessage());
		}

	}
	/**
	 * 
	 * @Title: servInfoLog
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @ @param className
	 * @ @param weixinUser
	 * @ @param serfrom_IP提供service的Ip
	 * @ @param serfrom_port提供service的端口
	 * @ @param action_level（在常量定义里面寻找相应的常量，如果没有可以自己添加）OperationConstant.
	 * @ @param action_type（在常量定义里面寻找相应的常量，如果没有可以自己添加）OperationConstant.
	 * @ @param action_desc操作描述，自己定义的字符串，比如说“用户注册的业务”
	 * @ @param action_time操作时间，获取方式为：long start = System.currentTimeMillis();//函数开始执行时加入
                                                                                   程序执行完毕以后：传入参数为 ： System.currentTimeMillis-start
	 * @ @param money 涉及到的钱数  
	 * @return: void   
	 * @throws
	 */

	public void servInfoLog(Class className,WeiXinUserPojo weixinUser,String serfrom_IP,
			String serfrom_port,String action_level,String action_type,String action_desc,Long action_time,Long money){//message为service层个性化信息
		long id = idFactory.nextId();//生成日志ID
		Logger log = LoggerFactory.getLogger(className); 
		boolean flag = true;
		if(null==weixinUser){//用户信息为空，添加需要的参数
			flag=false;
		}
		String action_result =  OperationConstant.OPERTION_ACTION_RESULT_SUCCESS;//当执行到这一步，默认执行结果正确 
		try {
			String start_time = Long.toString(System.currentTimeMillis());
			ServLogPojo Log = null;
			if(flag){
					//log日志输出bnm,
				if(log.isInfoEnabled()){
					log.info(className+":"+weixinUser.getOpenid()+","+start_time+","+action_level+","+action_type+","+className+","+action_result+","+action_time+","+weixinUser.getNick_name()+","+weixinUser.getUser_id()+","+money);
				}
				//hbase数据库输出
				//生成Log的参数，存储到hbase里面
				Log = new ServLogPojo(start_time,action_level,action_type,getHostIp(netAddress),System.getProperty("reyo.localPort"),
						serfrom_IP,serfrom_port,className.toString(),action_desc,action_result,
						Long.toString(action_time),weixinUser.getNick_name(),Long.toString(weixinUser.getUser_id()),Long.toString(money));
			}else{
					//log日志输出bnm,
				if(log.isInfoEnabled()){
					log.info(className+":"+"-1"+","+start_time+","+action_level+","+action_type+","+className+","+action_result+","+action_time+","+"visitor"+","+"-1"+","+money);
				}
				//hbase数据库输出
				//生成Log的参数，存储到hbase里面
				Log = new ServLogPojo(start_time,action_level,action_type,getHostIp(netAddress),System.getProperty("reyo.localPort"),
						serfrom_IP,serfrom_port,className.toString(),action_desc,action_result,
						Long.toString(action_time),"vistor","-1",Long.toString(money));
			}

			Map<String, Serializable> msgMap = new HashMap<String, Serializable>();
			msgMap.put("Log",(Serializable)Log);
			msgMap.put("operationType",JmsConstant.JMS_HBASE);
			msgMap.put("logType", JmsConstant.JMS_SERLOG);
	
		} catch (Exception e) {
			log.error("service_log error,message"+e.getMessage());
		}
	}
	/**
	 *  
	 * @Title: daoInfoLog
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @ @param className
	 * @ @param weixinUser
	 * @ @param action_level（在常量定义里面寻找相应的常量，如果没有可以自己添加）OperationConstant.
	 * @ @param action_type（在常量定义里面寻找相应的常量，如果没有可以自己添加）OperationConstant.
	 * @ @param data_name数据库名字
	 * @ @param data_IP数据库需要连接的ID
	 * @ @param data_port访问数据库需要的端口
	 * @ @param action_desc操作描述，自己定义的字符串，比如说“用户信息插入mysql”
	 * @ @param action_language操作语句，就是要执行的之领域具是什么
	 * @ @param action_time操作时间，获取方式为：long start = System.currentTimeMillis();//函数开始执行时加入
                                                                                   程序执行完毕以后：传入参数为 ： System.currentTimeMillis-start
	 * @ @param money 涉及到的钱数
	 * @return: void   
	 * @throws
	 */
	public void daoInfoLog(Class<?> className,WeiXinUserPojo weixinUser,String action_level,
			String action_type,String data_name,String data_IP,String data_port,String action_desc,String action_language,
			Long action_time,Long money){//message为dao层个性化信息
		long id = idFactory.nextId();//生成日志ID
		Logger log = LoggerFactory.getLogger(className); 
		boolean flag = true;
		if(null==weixinUser){//用户信息为空，添加需要的参数
			flag = false;
		}
		String action_result =  OperationConstant.OPERTION_ACTION_RESULT_SUCCESS;//当执行到这一步，默认执行结果正确 
		try {
			String start_time = Long.toString(System.currentTimeMillis());
			DaoLogPojo Log=null;

			if(flag){
				//log日志输出
				if(log.isInfoEnabled()){
					log.info(className+":"+weixinUser.getOpenid()+","+start_time+","+action_level+","+action_type+","+className+","+action_result+","+action_time+","+weixinUser.getNick_name()+","+weixinUser.getUser_id());
				}
				//hbase数据库输出
				 Log = new DaoLogPojo(start_time,action_level,action_type,data_name,getHostIp(netAddress),
						System.getProperty("reyo.localPort"),data_IP,data_port,className.toString(),action_desc,
						action_language,action_result,Long.toString(action_time),weixinUser.getNick_name(),Long.toString(weixinUser.getUser_id()),
						Long.toString(money));
			}else{
				//log日志输出
				if(log.isInfoEnabled()){
					log.info(className+":"+"-1"+","+start_time+","+action_level+","+action_type+","+className+","+action_result+","+action_time+","+"visitor"+","+"-1");
				}
				//hbase数据库输出
				 Log = new DaoLogPojo(start_time,action_level,action_type,data_name,getHostIp(netAddress),
						System.getProperty("reyo.localPort"),data_IP,data_port,className.toString(),action_desc,
						action_language,action_result,Long.toString(action_time),"visitor","-1",
						Long.toString(money));
			}

			Map<String, Serializable> msgMap = new HashMap<String, Serializable>();
			msgMap.put("Log",(Serializable)Log);
			msgMap.put("operationType",JmsConstant.JMS_HBASE);
			msgMap.put("logType", JmsConstant.JMS_DAOLOG);
			
		} catch (Exception e) {
			log.error("dao_log error,message"+e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: errLog
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @ @param className
	 * @ @param weixinUser
	 * @ @param action_desc“自己描述的字符串，比如说：用户访问注册页面出错”
	 * @ @param ex   
	 * @return: void   
	 * @throws
	 */

	public void errLog(Class<?> className,WeiXinUserPojo weixinUser,String action_desc,Exception ex) {
		String ip = getHostIp(netAddress);//System.getProperty("localaddress");//获取当前服务器IP地址
		int port = Integer.parseInt(tomcatPort);//获取当前服务器端口
		Logger log = LoggerFactory.getLogger(className);  
		String start_time = Long.toString(System.currentTimeMillis());//获取当前时间
		boolean flag =true;
		if(null==weixinUser){//用户信息为空，添加需要的参数
			flag=false;
		}
		if(null==ex){//未检测到异常信息
			ex=new Exception("未知异常信息");
		}
		ErrorLogPojo Log=null;
		if(flag){
			log.error(className+":"+weixinUser.getOpenid()+","+start_time+","+ip+","+port+","+className+","+weixinUser.getNick_name()+","+weixinUser.getUser_id()+","+ex.getMessage());
		    //生成Log的参数，存储到hbase里面
			Log = new ErrorLogPojo(start_time,getHostIp(netAddress),System.getProperty("reyo.localPort"),className.toString(),action_desc,
								weixinUser.getNick_name(),Long.toString(weixinUser.getUser_id()),ex.getMessage());
		}else{
			log.error(className+":"+"-1"+","+start_time+","+ip+","+port+","+className+","+"visitor"+","+"-1"+","+ex.getMessage());
		    //生成Log的参数，存储到hbase里面
			Log = new ErrorLogPojo(start_time,getHostIp(netAddress),System.getProperty("reyo.localPort"),className.toString(),action_desc,
								"visitor","-1",ex.getMessage());
		}

		Map<String, Serializable> msgMap = new HashMap<String, Serializable>();
		msgMap.put("Log",(Serializable)Log);
		msgMap.put("operationType",JmsConstant.JMS_HBASE);
		msgMap.put("logType", JmsConstant.JMS_ERRLOG);
	}
	

	
	/**
	 * 记录报错信息并打印错误日志，用在catch里边
	 *  
	 * @param className      记录日志的类
	 * @param userName       当前操作用户名(若无法提供，则给字符串：-- )
	 * @param userAlias      当前操作中文名(若无法提供，则给字符串：-- )
	 * @param modelName      记录的哪个模块（每个模块（甚至是页面）要统一名称，统一在BusinessConstant中声明定义，若无法提供，则给字符串：-- ）
	 * @param operationType  操作类型：查询；删除；修改；新增；统计分析；数据导出；数据导入；调用外部接口；开发/测试调试（如果操作类型不足，请在OperationConstant中进行查询或添加，要保证统一）
	 * @param message        详细日志信息
	 * @param t              错误信息
	 * */
	public void errorLog(Class<?> className,String userName,String userAlias,String modelName,String operationType,String message,Exception t){
		String ip = getHostIp(netAddress);//System.getProperty("localaddress");//获取当前服务器IP地址
		int port = Integer.parseInt(tomcatPort);//获取当前服务器端口
		Logger log = LoggerFactory.getLogger(className);  
		log.error(">>>>>>[" + ip + ":" + port + "]" + "--->" + "(" + userName + "-" + userAlias + ")" + "--->" + "{" + modelName + "}" + "--->" + "#" + operationType + "#" + "--->" + "*" + message + "*",t);
	}
	
	public static InetAddress getInetAddress(){  
        try{  
            return InetAddress.getLocalHost();  
        }catch(UnknownHostException e){  
            System.out.println("unknown host!");  
        }  
        return null;  
    }  
  
    public static String getHostIp(InetAddress netAddress){  
        if(null == netAddress){  
            return null;  
        }  
        String ip = netAddress.getHostAddress(); //get the ip address  
        return ip;  
    }  
  
    public static String getHostName(InetAddress netAddress){  
        if(null == netAddress){  
            return null;  
        }  
        String name = netAddress.getHostName(); //get the host address  
        return name;  
    }  
    
    public static String getTomcatPort(){
		String strXml = "";
		String tomcatBase = System.getProperty("catalina.base");	
		Document document;
		String port = "";
		try {
			  SAXReader sr = new SAXReader();//获取读取xml的对象。
			  Document doc = sr.read(tomcatBase+"/conf/server.xml");//得到xml所在位置。然后开始读取。并将数据放入doc中
			  Element el_root = doc.getRootElement();//向外取数据，获取xml的根节点。
			  Attribute attr = el_root.attribute("port");
			  port = attr.getValue();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port;
    }
}
