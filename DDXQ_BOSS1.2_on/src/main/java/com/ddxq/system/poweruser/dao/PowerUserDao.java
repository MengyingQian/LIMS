package com.ddxq.system.poweruser.dao;

import java.util.List;
import java.util.Map;

public interface PowerUserDao {
 public boolean  insertInfo(Map map);
 public boolean  editInfo(Map map);
 public boolean  removeInfo(String account);
 public List<Map<String, Object>> getUserViaActorid(String actorid);
List<Map<String, Object>> getUserPosition(String actorid);
List<Map<String, Object>> getUserStatus(String actorid);
}
