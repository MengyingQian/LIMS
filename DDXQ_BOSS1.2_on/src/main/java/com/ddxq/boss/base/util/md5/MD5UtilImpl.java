package com.ddxq.boss.base.util.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年4月22日 下午8:14:19 
* 类说明 
*/
@Component("mD5Util")
public class MD5UtilImpl implements MD5Util {

	@Override
	public String StringToMD5_16(String str) throws Throwable {
		// TODO Auto-generated method stub
		// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
		MessageDigest messageDigest;
		
		messageDigest = MessageDigest.getInstance("MD5");

		// 输入的字符串转换成字节数组
		byte[] inputByteArray = str.getBytes();
				// inputByteArray是输入字符串转换得到的字节数组
		messageDigest.update(inputByteArray);
				// 转换并返回结果，也是字节数组，包含16个元素
		byte[] resultByteArray = messageDigest.digest();
				// 字符数组转换成字符串返回
				// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
				// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray =new char[resultByteArray.length * 2];
				// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : resultByteArray) {
				resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
				resultCharArray[index++] = hexDigits[b& 0xf];
		}
		// 字符数组组合成字符串
		return new String(resultCharArray).substring(8,24);

	
	}

	@Override
	public String StringToMD5_32(String str) throws Throwable {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
		MessageDigest messageDigest;
		
			messageDigest = MessageDigest.getInstance("MD5");

			// 输入的字符串转换成字节数组
		byte[] inputByteArray = str.getBytes();
				// inputByteArray是输入字符串转换得到的字节数组
		messageDigest.update(inputByteArray);
				// 转换并返回结果，也是字节数组，包含16个元素
		byte[] resultByteArray = messageDigest.digest();
				// 字符数组转换成字符串返回
				// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
				// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray =new char[resultByteArray.length * 2];
				// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : resultByteArray) {
				resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
				resultCharArray[index++] = hexDigits[b& 0xf];
		}
		// 字符数组组合成字符串
		return new String(resultCharArray).toLowerCase();
	}

}
