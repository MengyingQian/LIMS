package com.ddxq.boss.base.dictionary.dao;

import java.util.List;
import java.util.Map;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年8月31日 上午11:32:14 
* 类说明 
*/
public interface DictionaryDao {
	public List<Map< String,Object>> getEducation();
	public List<Map< String,Object>> getAge();
}
