package com.ddxq.boss.base.util.json2map;

import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONObject;

public class JSONAndMap {
	   public static HashMap<String, String> json2HashMap(JSONObject jsonObject)  
	   {  
	       HashMap<String, String> data = new HashMap<String, String>();     
	       Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           String value = String.valueOf(jsonObject.get(key));  
	           data.put(key, value);  
	       }  
	       return data;  
	   }  
}
