package com.ddxq.boss.base.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.common.log.SystemLog;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年7月6日 上午9:28:22 
* 类说明 
*/
@Component("hotlevel")
public class HotlevelImpl implements Hotlevel {
	
	@Autowired
	SystemLog systemLog;
	@Autowired
	BaseDAO baseDAO;

	@Override
	public List<Map<String, Object>> getBianTilist() {
		Date today = new Date();
		String sql = "select debate_id, user_id, openid, debate_name, debate_content, start_time, expire_time, hot_level, articles_cnt from debate_list where ? > start_time and ? < expire_time order by hot_level DESC;";
		List<Map<String, Object>> reslist= baseDAO.queryForList(sql, new Object[]{today, today});
		return reslist;		
	}

	@Override
	public boolean updateHotlevel(Map<String, Object> map) {
		String sql=" update debate_list set hot_level=:hot_level where debate_id=:debate_id ";
		return baseDAO.executeNamedCommand(sql, map);
	}

}
