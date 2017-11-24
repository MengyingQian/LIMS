package com.ddxq.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ddxq.base.service.BaseService;

/**
 * controller基类，所有controller必须继承此类，此类也是封装各个controller层基本方法的地方
 * @author zxz
 * @version 1.0.0
 * @since 2015-02-01
 * */
@Component("baseController")
public class BaseController {
	
	@Autowired
	private BaseService baseService;//基类service，便于一些业务逻辑十分简单的场景，直接操作数据库读写
	
	/**
	 * 便于在前后台之间数据传递时，绑定并注入各种比如时间，DOUBLE类型的VO
	 * @param binder 绑定池
	 * */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(java.sql.Timestamp.class,
				new CustomDateEditor(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseReadService(BaseService baseService) {
		this.baseService = baseService;
	}
	
}
