package com.ddxq.boss.base.config.dao;

import java.util.Map;

public interface ConfigMySQLDao {
	//得到某个序号的配置，比如将来不同省市情况不同，可以设置不同的配置
	//id为1的配置是缺省配置
	public Map<String, Object> getconfigViaIndex(int index);
}
