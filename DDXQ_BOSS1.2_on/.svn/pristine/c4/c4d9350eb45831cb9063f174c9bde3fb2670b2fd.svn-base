package com.ddxq.employee.kuaidi.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.common.cache.CacheUtil;
import com.ddxq.employee.kuaidi.dao.KuaiDiDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("kuaiDiService")
public class KuaiDiServiceImpl implements KuaiDiService{
	
	@Autowired
	private KuaiDiDao kuaiDiDao;
	private static final int TIMEOUT = 5000;//设置超时时间5000毫秒即5秒超时
	private static final String REGISTERPHONE = "MobileBoss"; //redis中获取进行短暂的存储
	private static final int STAYTIME = 1800;//redis存储时间为1800秒
	
	@Override
	public Map getinfo(String id) {
		return kuaiDiDao.getSellerInfo(id);
	}
	@Override
	public boolean SendMessage(JSONObject obj) {
		String mobile = obj.getString("mobile");
		String address = obj.getString("address");
		String mobile2 = obj.getString("mibile2");
		Map<String,Object> map = new HashMap<String,Object>();
        String content = "";
        try {
        	/*尊敬的智慧小区用户：您有快递由本代收点代收，请前来领取，地点是[^,]*?，联系电话[^,]*?，祝您生活愉快！*/
            content = URLEncoder.encode("尊敬的智慧小区用户：您有快递由本代收点代收，请前来领取，地点是" + address 
            		+ "，联系电话" + mobile2 + "，祝您生活愉快！", "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        String url = "http://122.49.30.10:9900/sendXSms.do?username=szxq&password=abcd1234&productid=787999&content="
                + content + "&mobile=" + mobile;
        String res =null;
        try {
            res = doget(url, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送验证码出错:" + e);
            return false;
        }
        System.out.println("验证码发送:" + res );
        return true;
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

	
}
