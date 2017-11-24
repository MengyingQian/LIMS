package com.ddxq.employee.login.service.copy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddxq.employee.login.dao.copy.LoginDao;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	LoginDao loginDao;
	@Override
	public Map getMsg(String name, String password) {
		Map map=new HashMap<>();
		Map map2=loginDao.getMsg2(name);
		map.put("name",map2.get("name"));
		map.put("sex", map2.get("sex"));
		map.put("age", map2.get("age"));
		map.put("ID", map2.get("ID"));
		
		return map;

		
	}
	@Override
	public String getTypeName(String actorid) {
		switch(actorid){
			case "100":
				return "小区管理员";
			case "110":
				return "小区片警";
			case "120":
				return "家庭医生";
			case "150":
				return "小区中介";
			case "170":
				return "快递代收";				
			case "200":
				return "小区物业";
			case "300":
				return "小区居委会";
			case "400":
				return "小区客服";
			case "500":
				return "业委会用户";
			case "700":
				return "小区骑手";
			case "800":
				return "小区商家";
		}
		return "";
	}
	@Override
	public int check(String name, String password) {
		int result = loginDao.haveAccount(name);//3,不存在  ，4普通用户停用，5管理员停用
		if(result < 2){
			if(loginDao.havePass(name, password)){
				return 1;
			}else{
				return 2;
			}
		}else{
			return result;
		}
	}

}
