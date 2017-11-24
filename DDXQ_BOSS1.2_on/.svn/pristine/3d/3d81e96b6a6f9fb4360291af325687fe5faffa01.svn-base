package com.ddxq.boss.base.user.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.user.dao.UsersDao;
import com.ddxq.boss.base.user.dao.UsersRedisDao;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.common.cache.CacheUtilTImpl;

import net.sf.json.JSONObject;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月23日 下午2:06:00 
* 类说明 
*/
@Service("WeiXinUserService")
public class WeiXinUserServiceImpl implements WeiXinUserService {
	@Autowired
	UsersDao usersDao;
	@Autowired
	UsersRedisDao usersRedisDao;
	@Autowired 
	CacheUtilTImpl cacheUtilImpl;
	
	
	
	private static final int TIMEOUT = 5000;//设置超时时间5000毫秒即5秒超时
	private static final String REGISTERPHONE = "RegisterMobile"; //redis中获取进行短暂的存储
	private static final int STAYTIME = 1800;//redis存储时间为1800秒
	
	@Override
	public boolean updateZhuCe(HttpServletRequest request, WeiXinUserPojo weixinUser) {

		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		weixinUser.setMobile(mobile);
		weixinUser.setPassword(password);
		Map<String,Object> usermap =new HashMap<String,Object>();
		usermap.put("mobile", mobile);
		usermap.put("password", password);
		usersRedisDao.putUser(Long.toString(weixinUser.getUser_id()), weixinUser);
		return usersDao.updateUserPojo(weixinUser.getUser_id(), usermap);
	}

//	@Override
//	public Map<String,Object> getCode(String mobile,String change) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		int verificationCode = 0;	
//		if("null".equals(change) ){
//			return map;
//		}else{
//			return map;
//		}
//		Map<String,Object> phoneredis = cacheUtilImpl.get(REGISTERPHONE+"_"+mobile, mobile, Map.class);
//		if( null != phoneredis ){
//			int time = (int)phoneredis.get("time"); 						
//			if(time > 5){//限制获取验证码的次数
//				map.put("success", false);    						     
//				map.put("reason", "获取验证码超过次数限制");
//				return map;
//			}
//			verificationCode = (int) phoneredis.get("verificationCode");   						
//			phoneredis.put("time", time+1);
//		}else{
//			phoneredis = new HashMap<String,Object>();
//			Random rand = new Random(); 						
//	        verificationCode = rand.nextInt(8000) + 1000;
//	        phoneredis.put("time", 1);
//		}
//        phoneredis.put("verificationCode", verificationCode);     							
//        cacheUtilImpl.put(REGISTERPHONE+"_"+mobile, mobile, phoneredis, STAYTIME);
//        String content = "";
//        try {
//            content = URLEncoder.encode("欢迎成为君子淑女会员！您的验证码为：" + String.valueOf(verificationCode)
//                    + "。如非本人操作，请予忽略。", "utf-8");
//        } catch (UnsupportedEncodingException e2) {
//            e2.printStackTrace();
//        }
//        String url = "http://122.49.30.10:9900/sendXSms.do?username=jzsn&password=abcd1234&productid=787999&content="
//                + content + "&mobile=" + mobile;
//        String res =null;
//        try {
//            res = doget(url, "utf-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("发送验证码出错:" + e);
//        }
//        System.out.println("验证码发送:" + res );
//        map.put("verificationCode",String.valueOf(verificationCode));
//        map.put("success",true);
//        return map;
//	}
	@Override
	public Map<String,Object> yanzheng(JSONObject obj) {
		Map<String,Object> map = new HashMap<String,Object>();
		String mobile = obj.getString("mobile");
		String  verificationCode = obj.getString("verificationCode");
		String flag =obj.getString("flag");
		Map<String,Object> result = cacheUtilImpl.get(REGISTERPHONE+"_"+mobile, mobile, Map.class);
		if(null ==  result ){
			map.put("success",false);
			return map;
		}
		String veriredis =String.valueOf( result.get("verificationCode") ) ;
		if(veriredis.equals(verificationCode)){
			map.put("success",true);
			if( "register".equals(flag) ){
				return map;
			}else{
				String openid_boss= usersDao.openidMobile(mobile);
				map.put("openid_boss", openid_boss);
				return map;
			}
		}
		map.put("success", false);
		return map;
	}
	public String doget(String url,String charset){
	    /*
	     * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤: 1:生成一个 HttpClinet 对象并设置相应的参数。
	     * 2:生成一个 GetMethod 对象并设置响应的参数。 3:用 HttpClinet 生成的对象来执行 GetMethod 生成的Get
	     * 方法。 4:处理响应状态码。 5:若响应正常，处理 HTTP 响应内容。 6:释放连接。
	     */
	    /* 1 生成 HttpClinet 对象并设置参数 */
	    HttpClient httpClient = new HttpClient();
	    // 设置 Http 连接超时为5秒
	    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
	    /* 2 生成 GetMethod 对象并设置参数 */
	    GetMethod getMethod = new GetMethod(url);
	    // 设置 get 请求超时为 5 秒
	    getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIMEOUT);
	    // 设置请求重试处理，用的是默认的重试处理：请求三次
	    getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,	new DefaultHttpMethodRetryHandler());
	    String response = "";
	    /* 3 执行 HTTP GET 请求 */
	    try {
	      int statusCode = httpClient.executeMethod(getMethod);
	      /* 4 判断访问的状态码 */
	      if (statusCode != HttpStatus.SC_OK) {
	        System.err.println("请求出错: "+ getMethod.getStatusLine());
	      }
	      /* 5 处理 HTTP 响应内容 */
	      // HTTP响应头部信息，这里简单打印
	      Header[] headers =  getMethod.getResponseHeaders();
	      for (Header h : headers)
	        System.out.println(h.getName() + "------------ " + h.getValue());
	      // 读取 HTTP 响应内容，这里简单打印网页内容
	      byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
	      response = new String(responseBody, charset);
	      System.out.println("----------response:" + response);
	      // 读取为 InputStream，在网页内容数据量大时候推荐使用
	      // InputStream response = getMethod.getResponseBodyAsStream();
	    } catch (HttpException e) {
	      // 发生致命的异常，可能是协议不对或者返回的内容有问题
	      System.out.println("请检查输入的URL!");
	      e.printStackTrace();
	    } catch (IOException e) {
	      // 发生网络异常
	      System.out.println("发生网络异常!");
	      e.printStackTrace();
	    } finally {
	      /* 6 .释放连接 */
	      getMethod.releaseConnection();
	    }
	    return response;
	}

	@Override
	public Map<String, Object> loginyanzheng(JSONObject obj) {
		String mobile = obj.getString("mobile");
		String password = obj.getString("password");
		Map<String,Object> map = new HashMap<String,Object>();
		if( false==usersDao.isMobile(mobile)  ){
			map.put("success", false);
			map.put("reason", "手机号不存在，请重新输入或注册");
			return map;
		}
		String opeind_boss = usersDao.getOpenid(mobile, password);
		if( null== opeind_boss ){
			map.put("success", false);
			map.put("reason", "手机号与密码不匹配，请从新输入或者修改密码");
			return map;
		}else{
			map.put("success", true);
			map.put("opeind_boss", opeind_boss);//不管正确与否直接替换openid
			return map;
		}
	}

	@Override
	public Map<String, Object> genrenzhongxin(String openid) {
		Map<String,Object> map =new HashMap<String,Object>();
		Map<String,Object> usercache = usersDao.OpenidGetMap(openid);
		if(null==usercache){
			System.out.println("5");
			map.put("success", false);
			return map;
			
		}else if( null ==usercache.get("mobile") ){
			System.out.println("6");
			map.put("success", false);
			return map;
		}
		System.out.println("7");
		map.put("success", true);
		map.putAll(usercache);
		System.out.println("8");
		return map;
	}

}
