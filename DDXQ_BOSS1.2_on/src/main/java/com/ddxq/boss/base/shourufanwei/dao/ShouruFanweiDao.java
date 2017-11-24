package com.ddxq.boss.base.shourufanwei.dao;

import java.util.List;
import java.util.Map;
/**
 * 
 * ClassName:ShouruFanweiDao
 * Description:TODO
 * @author Liu
 * @date 2016年5月20日 上午10:41:12
 *
 */
public interface ShouruFanweiDao {
	/**
	 * 
	 * @Title:getShouruFanwei
	 * @Description:获取收入范围返回给jsp页面选项
	 * @param:@return
	 * @return:List<Map<String,Object>>
	 * @throws
	 */
	public List<Map<String, Object>> getShouruFanwei();
}
