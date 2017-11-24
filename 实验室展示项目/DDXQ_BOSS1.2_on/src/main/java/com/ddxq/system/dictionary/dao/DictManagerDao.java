package com.ddxq.system.dictionary.dao;

import java.util.List;
import java.util.Map;

public interface DictManagerDao {
	public List<Map> getMenuA();
	public List<Map> getMenuB(String idA);
	public boolean insertInfo(Map map);
	public boolean updateInfo(Map map);
	public boolean removeInfo(Map map);
}
