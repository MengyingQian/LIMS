package com.ddxq.mytest.globe.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.ddxq.common.log.SystemLog;

/**
 * demo定时任务
 * */
public class DemoQuartz {

	@Autowired
	SystemLog systemLog;
	
	private int isOpen = 0;// 监测开关，默认关闭

	//定时器轮询方法
	public void executeInternal() throws Exception {
		if (isOpen == 1) {//定时器开关打开了
			doBusiness();
		}
	}

	/**
	 * 定时器业务逻辑
	 * */
	private void doBusiness(){
		systemLog.debugLog(DemoQuartz.class, "这是一个测试的quartz!");
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

}
