package com.ddxq.boss.base.user.dao;
//本类专门处理subscribe_users表，该表主要用于对于所有关注了该表的用户进行群发消息管理
//与users表结构相同，但是users主要用于网页页面的管理

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.boss.base.user.pojo.WeiXinUserPojo;
import com.ddxq.boss.base.util.MapBean.MapBeanUtilImpl;
import com.ddxq.common.log.SystemLog;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("subscribeUsersDao")
public class SubscribeUsersDaoImpl implements SubscribeUsersDao{
	
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	MapBeanUtilImpl	mapBeanUtil;
	@Autowired
	SystemLog systemlog;
	
	@Override
	public long getUserId() {
		String sql=" select max(user_id) from subscribe_users ;";
		Map<String,Object> map = baseDAO.queryForMap(sql);
		System.out.println(map.toString());
		if(null != map){
			return (long)map.get("max(user_id)");
		}
		return -1;
	}


	@Override
	public HashMap<String, Object[]> jsonObject_To_HashMap(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		HashMap hashMap = new HashMap<String,Object[]>();
		
		List<Object> list = new ArrayList<Object>();
		List values = new ArrayList();
		List keys   = new ArrayList();
		
		Iterator iterator = jsonObject.keys();
		
		while(iterator.hasNext()){
			Object nextKey = iterator.next();
			Object nextValue = jsonObject.get(nextKey);
			if(nextValue != null && !nextValue.toString().equals("") && !nextKey.toString().equals("birth") && !nextKey.toString().equals("update_time") && !nextKey.toString().equals("create_time"))
			{
				list.add(nextValue.toString());
				values.add("?");
				keys.add(nextKey);
			}
		}
		
		String keyslist = " (" + StringUtils.join(keys,", ") + ") ";
		String valueslist = " values (" + StringUtils.join(values,", ") + ");";
		
		String[] keysql = {keyslist};
		String[] valuesql = {valueslist};
		
		hashMap.put("keys",keysql);
		hashMap.put("values",valuesql);
		hashMap.put("ObjectArray", list.toArray());
		
		return hashMap;
	}

	@Override
	public boolean saveUserInfo(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		String sql = "insert into subscribe_users";
		HashMap<String,Object[]> hm = jsonObject_To_HashMap(jsonObject);
		Object[] keys = hm.get("keys");
		Object[] values = hm.get("values");
		Object[] objectArray = hm.get("ObjectArray");
		
		sql += keys[0].toString() + values[0].toString();
		return baseDAO.executeCommand(sql,objectArray);
	}

	@Override
	public boolean deleteUserViaOpenId(String OpenId) {
		// TODO Auto-generated method stub
		String sql = "delete from subscribe_users where openid=?";
		Object [] obj = {OpenId};
		return baseDAO.executeCommand(sql, obj);
	}

	@Override
	public boolean updateUserViaOpenId(String OpenId, JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		String sql = "update subscribe_users set";
		
		List<Object> list = new ArrayList<Object>();
		String keyslist   = " ";
		
		Iterator iterator = jsonObject.keys();
		
		while(iterator.hasNext()){
			
			Object nextKey = iterator.next();
			Object nextValue = jsonObject.get(nextKey).toString();
			if(nextValue != null && !nextValue.equals(""))
			{
				list.add(nextValue.toString());
				
				keyslist   += nextKey + " = ? ";
				if(iterator.hasNext()){
					keyslist   += ",";
				}
			}
		}
		sql += keyslist + "where openid = \"" + OpenId + "\";";
		return baseDAO.executeCommand(sql, list.toArray());
	}

	@Override
	public Map queryUserViaOpenId(String OpenId) {
		// TODO Auto-generated method stub
		
		String sql = " select * from subscribe_users where openid = :openid ;";
		Map map = new HashMap();
		map.put("openid", OpenId);
		Map mymap =  baseDAO.queryForMap(sql, map);
		
		return mymap;
	}


	@Override
	public boolean isUserExist(String OpenId) {
		// TODO Auto-generated method stub
		Map mymap = queryUserViaOpenId(OpenId);
		if(mymap.get("nick_name") != "" && mymap.get("nick_name") != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean normalizeUserstatus(String OpenId) {
		// TODO Auto-generated method stub
		
		
		JSONObject json = new JSONObject();
		json.put("status", "1");
		return updateUserViaOpenId(OpenId,json);
		
	}
	
	@Override
	public boolean freezeUserStatus(String OpenId) {
		// TODO Auto-generated method stub
		
		JSONObject json = new JSONObject();
		json.put("status", "0");
		return updateUserViaOpenId(OpenId,json);
	}

	@Override
	public List getAllUsersOpenId() {
		// TODO Auto-generated method stub
		String sql = "select openid from subscribe_users;";
		
		List IDS = baseDAO.queryForList(sql);
		
        JSONArray jsonObj = JSONArray.fromObject(IDS);
        List openIdList = new ArrayList();
        
        int length = IDS.size();
        for(int i=0;i<length;i++){
            openIdList.add(jsonObj.getJSONObject(i).get("openid"));
        }
		return openIdList;
	}
	
	@Override
	public boolean saveUsersInfoToMysql(JSONObject jsonUsersInfo){
		// TODO Auto-generated method stub
		
		JSONArray jsonArray = jsonUsersInfo.getJSONArray("user_info_list");
		int length = jsonArray.size();
		for(int i = 0 ; i < length ; i++){
			if(!saveUserInfo(jsonArray.getJSONObject(i)))
				return false;
		}
		return true;
	}

/*	@Override
	public boolean saveUserViaUserPojo(WeiXinUserPojo weiXinUser){
		JSONObject userJson = JSONObject.fromObject(weiXinUser);
		return saveUserInfo(userJson);
	}*/
	
/*	public Map<String,Object> reflectObj(WeiXinUserPojo weiXinUser) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
//	    Object weiXinUser = new Object();
        Class<?> weiXinUserClass = weiXinUser.getClass();
        Field[] fields = weiXinUserClass.getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), weiXinUserClass);
            Method getMethod = pd.getReadMethod();
            Object o = getMethod.invoke(weiXinUser);
            map.put(field.getName(), o);
        }
        return map;
	}*/
	@Override
	public boolean saveUserViaUserPojo(WeiXinUserPojo weiXinUser){
		if(weiXinUser ==null){
			return false;
		}
		Map<String,Object> usermap =  new HashMap<String,Object>();
		usermap = mapBeanUtil.transBean2Map(weiXinUser);
		StringBuffer sql = new StringBuffer();
		sql.append("insert into subscribe_users (  ");//ge_ren_id,ge_ren_name,industry,jspoint,people_num,rank) values(:ge_ren_id,:ge_ren_name,?,?,?,?) ;");
		List<String> keylist =  new ArrayList<String>();
		List<Object> valuelist = new ArrayList<Object>();
		for(Map.Entry<String, Object> entry:usermap.entrySet()){
			if( entry.getValue()==null || entry.getValue().equals("")){}
			else{
				keylist.add(entry.getKey());
				valuelist.add(entry.getValue());
			}

		}
		String key = org.apache.commons.lang.StringUtils.join(keylist.toArray(),",");
		StringBuffer sb = new StringBuffer();
		sb.append("( ?");
		for(int i=0;i<keylist.size()-1;i++){
			sb.append(",?");
		}
		sb.append(" )");
		sql.append(key+")"+" "+"values" +sb.toString());
		Object []args = valuelist.toArray();
		return baseDAO.executeCommand(sql.toString(), args);
	}
	/**
	 * 根据用户的userid得到用户信息
	 * <p>Title: useridGetUserPojo</p>
	 * <p>Description: </p>
	 * @param userid
	 * @return
	 * @see com.ddxq.boss.base.user.dao.UsersDao#useridGetUserPojo(long)
	 */
	@Override
	public WeiXinUserPojo useridGetUserPojo(long userid) {
		WeiXinUserPojo weixin = new WeiXinUserPojo();
		Map<String,Object> usermap = new HashMap<String,Object>();
		String sql = " select * from subscribe_users where user_id = :user_id ";
		usermap.put("user_id", userid);
		Map mymap =  baseDAO.queryForMap(sql, usermap);
		
		if(mapBeanUtil.transMap2Bean2(mymap, weixin)){
			return weixin;
		}else{
			return null;
		}
	}

	@Override
	public boolean updateUserPojo(long id,Map<String,Object> usermap) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update subscribe_users set  ");//ge_ren_id,ge_ren_name,industry,jspoint,people_num,rank) values(:ge_ren_id,:ge_ren_name,?,?,?,?) ;");
		List<String> keylist =  new ArrayList<String>();
		List<Object> valuelist = new ArrayList<Object>();
		for(Map.Entry<String, Object> entry:usermap.entrySet()){
			if( entry.getValue()==null || entry.getValue().equals("")){
			
			}else{
				keylist.add(entry.getKey());
				valuelist.add(entry.getValue());
			}
		}
		String key = org.apache.commons.lang.StringUtils.join(keylist.toArray(),"= ? ,");
		sql.append(key+"= ? "+"  where user_id = ?");
		valuelist.add(id);
		Object []args = valuelist.toArray();
		return baseDAO.executeCommand(sql.toString(), args);
		
	}	

	@Override
	public Map<String, Object> UseridGetMap(String user_id) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("user_id", user_id);
		return baseDAO.queryForMap(" select * from subscribe_users where user_id = :user_id ", map);
	}

	@Override
	public Map<String, Object> OpenidGetMap(String openid) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("openid", openid);
		return baseDAO.queryForMap(" select * from subscribe_users where openid = :openid ", map);
	}
	
}
