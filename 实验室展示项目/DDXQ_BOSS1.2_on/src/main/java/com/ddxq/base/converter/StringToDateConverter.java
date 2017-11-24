package com.ddxq.base.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月13日 上午8:57:11 
* 类说明 
*/
public class StringToDateConverter implements Converter<String, Date> {
	
	private String datePattern;
	
	public StringToDateConverter(String datePattern){
		this.datePattern = datePattern;
	}
	
	public Date convert(String s){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
			dateFormat.setLenient(false);
			return dateFormat.parse(s);
		} catch (ParseException e) {
				
			throw new IllegalArgumentException(  "invalid date format. Please use this pattern\""
                    + datePattern + "\"" );
			
		}
	}
}
