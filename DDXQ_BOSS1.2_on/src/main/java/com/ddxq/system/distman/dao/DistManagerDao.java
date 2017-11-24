package com.ddxq.system.distman.dao;

import java.util.List;
import java.util.Map;

public interface DistManagerDao {
	 public boolean  insertInfo(Map map);
	 public boolean check(String employeeId);
	 public boolean  editInfo(Map map);
	 public boolean  removeInfo(String account);
	 public List<Map<String, Object>> getUserViaActorid(String actorid);
}
