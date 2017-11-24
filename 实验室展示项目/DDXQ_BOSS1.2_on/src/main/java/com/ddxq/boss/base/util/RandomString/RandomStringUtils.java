package com.ddxq.boss.base.util.RandomString;

import java.util.Random;

import org.springframework.stereotype.Component;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年12月2日 下午2:22:27 
* 类说明 
*/
@Component("randomStringUtils")
public class RandomStringUtils {
	 public String getRandomString(int length) {
	        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        Random random = new Random();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < length; ++i) {
	            int number = random.nextInt(52);// [0,51)
	            sb.append(str.charAt(number));
	        }
	        return sb.toString();
	    }
}
 