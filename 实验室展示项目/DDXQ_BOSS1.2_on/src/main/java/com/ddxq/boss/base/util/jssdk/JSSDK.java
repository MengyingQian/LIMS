package com.ddxq.boss.base.util.jssdk;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddxq.common.cache.CacheUtil;
import com.jzsn.utils.https.HttpsManager;
import com.jzsn.utils.https.HttpsSender;
import com.jzsn.utils.weixin.token.WeiXinTokenManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 
 * ClassName:Jssdk
 * Description:TODO
 * @author Liu
 * @date 2016年5月3日 上午10:29:59
 *
 */
@Component("jSSDK")
public class JSSDK {
    // 微信JSSDK的ticket请求URL地址 
	public final static String weixin_jssdk_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	@Autowired
	WeiXinTokenManager weiXinTokenManager;
	@Autowired
	private CacheUtil cacheUtil;
	
	/**
	 * 
	 * @Title:getJSSDKTicket
	 * @Description:get jssdk_ticket by access_token
	 * @param:@param access_token
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	private static String getJSSDKTicket(String access_token) {  
        String returnString="";
        String requestUrl = weixin_jssdk_ticket_url.replace("ACCESS_TOKEN", access_token); 
		HttpsSender httpsSender = HttpsManager.getHttpsSender(); 
        JSONObject jsonObject = httpsSender.get_repsonse_JSON(requestUrl,3000,3000);
        // 如果请求成功   
	    if (null != jsonObject) {  
	        try {
	            returnString=jsonObject.getString("ticket");
	        } catch (JSONException e) {  
	            returnString = null;  
	        }
	    }  
        return returnString;  
    }
	
	/**
	 * 
	 * @Title:byteToHex
	 * @Description:byte to hex
	 * @param:@param hash
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	private static String byteToHex(final byte[] hash) {
	    Formatter formatter = new Formatter();
	    for (byte b : hash){
		        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
        formatter.close();
        return result;
	}

	/**
	 * 
	 * @Title:create_nonce_str
	 * @Description:create_nonce_str:a uuid
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	private static String create_nonce_str(){
	    return UUID.randomUUID().toString();
	}
	
	/**
	 * 
	 * @Title:create_timestamp
	 * @Description:create_timestamp:Long.toString(System.currentTimeMillis() / 1000)
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	private static String create_timestamp(){
	    return Long.toString(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 
	 * @Title:sign
	 * @Description:get url,jsapi_ticket,nonceStr,timestamp,signature by url,jsapi_ticket
	 * @param:@param jsapi_ticket
	 * @param:@param url
	 * @param:@return
	 * @return:Map<String,String>
	 * @throws
	 */
	private static Map<String, String> sign(String jsapi_ticket, String url){
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
	    String timestamp = create_timestamp();
		String string1;
	    String signature = "";
	    //注意这里参数名必须全部小写，且必须有序
	    
		string1 = "jsapi_ticket=" + jsapi_ticket +"&noncestr=" + nonce_str +
	              "&timestamp=" + timestamp +"&url=" + url;
		try
		{
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
	    }catch (UnsupportedEncodingException e){
	    	e.printStackTrace();
	    }
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
	    ret.put("timestamp", timestamp);
	    ret.put("signature", signature);
		return ret;
	}
	
	/**
	 * 
	 * @Title:jssdkReturn
	 * @Description:TODO
	 * @param:@param url
	 * @param:@return
	 * @return:Map<String,String>
	 * @throws
	 */
	public Map<String,String> jssdkReturn(String url){
		String jsapi_ticket;
		if(cacheUtil.keyIsExist("jsapi_ticket")){
			jsapi_ticket=cacheUtil.get("jsapi_ticket");
		}else{
			String js_accessToken = weiXinTokenManager.getToken().getString("access_token");//access_token
			jsapi_ticket = getJSSDKTicket(js_accessToken); //获取微信jssdk---ticket
			cacheUtil.put("jsapi_ticket", jsapi_ticket, 3600);
		}
		Map data = sign(jsapi_ticket, url);
		return data;
	}
}
