/**
 * FileName:    UsersDaoImpl.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月21日 上午10:26:03
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月21日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.user.dao;

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
import com.ddxq.common.config.HostConfig;
import com.ddxq.common.log.SystemLog;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ClassName:UsersDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月21日 上午10:26:03 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
@Repository("usersDao")
public class UsersDaoImpl implements UsersDao{
	
	@Autowired
	BaseDAO baseDAO;
	@Autowired
	MapBeanUtilImpl	mapBeanUtil;
	@Autowired
	SystemLog systemlog;
	@Autowired
	private HostConfig hostConfig;
	
	@Override
	public long getUserId() {
		String sql1="INSERT into users (nick_name) values('游客');";
		baseDAO.getJdbcTemplate().update(sql1);
		long id=baseDAO.getJdbcTemplate().queryForInt("SELECT LAST_INSERT_ID()");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nick_name", "游客"+String.valueOf(id));
		map.put("user_name", "游客"+String.valueOf(id));
		map.put("openid", String.valueOf(id));
		map.put("industry_id", 0);
		map.put("income", 0);
		updateUserPojo(id,map);
		return id;
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
		
		String sql = "insert into users";
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
		String sql = "delete from users where openid=?";
		Object [] obj = {OpenId};
		return baseDAO.executeCommand(sql, obj);
	}

	@Override
	public boolean updateUserViaOpenId(String OpenId, JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		String sql = "update users set";
		
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
		
		String sql = " select * from users where openid = :openid ;";
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
		String sql = "select openid from users;";
		
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
		sql.append("insert into users (  ");//ge_ren_id,ge_ren_name,industry,jspoint,people_num,rank) values(:ge_ren_id,:ge_ren_name,?,?,?,?) ;");
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
		String sql = " select * from users where user_id = :user_id ";
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
		sql.append(" update users set  ");//ge_ren_id,ge_ren_name,industry,jspoint,people_num,rank) values(:ge_ren_id,:ge_ren_name,?,?,?,?) ;");
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
		return baseDAO.queryForMap(" select * from users where user_id = :user_id ", map);
	}

	@Override
	public Map<String, Object> OpenidGetMap(String openid) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("openid", openid);
		return baseDAO.queryForMap(" select * from users where openid = :openid ", map);
	}


	@Override
	public boolean isMobile(String mobile) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("mobile", mobile);
		String sql = " select openid from users where mobile= :mobile ;";
		List<Map<String,Object>> list = baseDAO.queryForList(sql, map);
		if(list.size() >=1 ){
			return true;
		} 
		return false;
	}


	@Override
	public String getOpenid(String mobile, String password) {
		Map<String,Object> parammap=new HashMap<String,Object>();
		parammap.put("mobile", mobile);
		parammap.put("password", password);
		String sql = " select openid from users where mobile=:mobile and password=:password ;";
		Map<String,Object> map = baseDAO.queryForMap(sql, parammap);
		if(null==map){
			return null;
		}
		if(null!= map.get("openid") ){
			return (String)map.get("openid");
		}
		return null;
	}


	@Override
	public String openidMobile(String mobile) {
		Map<String,Object> parammap = new HashMap<String,Object>();
		parammap.put("mobile", mobile);
		String sql = " select openid from users where mobile=:mobile ;";
		Map<String,Object> map = baseDAO.queryForMap(sql, parammap);
		if(null==map){
			return null;
		}
		if(null!=map.get("openid") ){
			return (String)map.get("openid");
		}
		return null;
	}

}
