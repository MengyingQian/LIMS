package com.ddxq.boss.base.config.dao;

import net.sf.json.JSONObject;

public interface ConfigRedisDao {
	public void addConfigintoRedis(JSONObject config);
	public int getNumber_of_banghuigu();
	public int getMax_rank();
	public int getLimit_count();
	public Integer getBianwen_count_per_page();
	public int getBianwen_count_in_redis();
}
