package com.ddxq.system.dictionary.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.base.dao.hbase.BaseHBaseDAO;
import com.ddxq.common.cache.CacheUtil;

@Repository("dictManagerDao")
public class DictManagerDaoImpl implements DictManagerDao {
	
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	CacheUtil cacheUtil;
	
	private boolean checkKeys(String key) {
		String rediskey="ddxq:goods:cates:name-id";
		if(cacheUtil.hashHaveKey(rediskey, key)){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<Map> getMenuA() {
		String sql = "select * from ddxq_goods_cates where level=1 order by ordernum ;";
		return baseDAO.queryForList(sql);
	}
	@Override
	public List<Map> getMenuB(String idA) {
		String sql = "select * from ddxq_goods_cates where cateid like \"" +idA + "%\" and "
				+ " level = 2 order by ordernum ;";
		return baseDAO.queryForList(sql);
	}
	@Override
	public boolean insertInfo(Map map) {
		String sql = "insert into ddxq_goods_cates (cateid,catename,level,ordernum,memo)"
				+ " values(:cateid,:catename,:level,:ordernum,:memo);";
		return baseDAO.executeNamedCommand(sql, map);
	}
	@Override
	public boolean updateInfo(Map map) {
		String sql = "update ddxq_goods_cates set cateid=:cateid,catename=:catename,level=:level,ordernum=:ordernum,"
				+ "status=:status where id=:id";
		return baseDAO.executeNamedCommand(sql, map);
	}
	@Override
	public boolean removeInfo(Map map) {
		String sql =  "delete from ddxq_goods_cates where id=:id;";
		return baseDAO.executeNamedCommand(sql,map);
	}
}
/*@Override
public boolean insert(String name, String cateid, String[] keys) {
	String rediskey="ddxq:goods:cates:id-name";
	Map map=new HashMap<>();
	map.put(cateid,name);
	cacheUtil.hashPut(rediskey, map);		
	String  keyredis="ddxq:goods:cates:name-id";
	Map map2=new HashMap<>();
	map2.put(name,cateid);
	if(keys==null){
		cacheUtil.hashPut(rediskey, map2);
		return true;
	}else{
		for (int i = 0; i < keys.length; i++) {
			String kk=keys[i].trim();
			if(checkKeys(kk)||kk.equals("")){
				continue;
			}
			map2.put(kk, cateid);
		}
		cacheUtil.hashPut(keyredis, map2);
		return true;
	}
}*/
