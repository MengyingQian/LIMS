package com.ddxq.employee.login.dao.copy;

import java.util.Map;

import javax.management.InstanceAlreadyExistsException;

public interface LoginDao {
	
	public  int isHave(String name,String password);
	public Map getMsg1(String name);
	public Map getMsg2(String name);
	public int haveAccount(String name);
	public boolean havePass(String name,String pass);
}
