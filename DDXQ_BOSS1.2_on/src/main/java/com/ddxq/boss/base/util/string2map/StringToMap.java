package com.ddxq.boss.base.util.string2map;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * ClassName:StringToMap
 * Description:TODO
 * @author Liu
 * @date 2016年7月1日 下午4:20:44
 *
 */
public class StringToMap {
	public static HashMap<String, String> string2Hashmap(String s){
		String[] erwei=s.replace("{", "").replace("}", "").replace(" ", "").split(",");
		ArrayList<String> alist=new ArrayList<String>();
		ArrayList<String> alistb=new ArrayList<String>();
		HashMap<String, String> hmm= new HashMap<String, String>();
		for(String stmap:erwei){
			alist.add(stmap);
		}
		for(int i=0;i<alist.size();i++){
			if(alist.get(i).toString().length()==1){
				hmm.put("", "");
			}else{
				String[] strs=alist.get(i).toString().split("=",2);
				for(String st:strs){
					alistb.add(st);
				}
			}
		}
		for(int i=0;i<alistb.size();i++){
			if(i%2==0){
				hmm.put(alistb.get(i), alistb.get(i+1));
			}
		}
		return hmm;
	}

}
