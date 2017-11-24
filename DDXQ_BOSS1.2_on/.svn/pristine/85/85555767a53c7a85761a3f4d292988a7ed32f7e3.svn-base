package com.ddxq.boss.base.util.json;

import java.text.DecimalFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class FloatValueProcessor implements JsonValueProcessor{
	DecimalFormat   fnum   =   new   DecimalFormat("##0.0");      
	
	@Override
	public Object processArrayValue(Object paramObject,
			JsonConfig paramJsonConfig) {
		// TODO Auto-generated method stub
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String paramString, Object paramObject,
			JsonConfig paramJsonConfig) {
		// TODO Auto-generated method stub
		return process(paramObject);
	}

	public Object process(Object value) {
		if (value instanceof Float) {
			if ((Float)value==0.0){
				return  fnum.format((Float)value);  
			}else{
				return value.toString();
			}
		}
		return value == null ? "" : value.toString();
	}
}
