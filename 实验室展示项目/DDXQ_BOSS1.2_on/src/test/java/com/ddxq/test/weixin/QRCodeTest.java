package com.ddxq.test.weixin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jzsn.utils.weixin.WeiXinBase;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext.xml","classpath*:/application-weixin.xml","classpath*:/application-init.xml","classpath*:/application-jms-send.xml","classpath*:/application-jms-receive.xml","classpath*:/application-writeDataSource.xml","classpath*:/application-readDataSource.xml","classpath*:/application-redis.xml","classpath*:/abstractSessionTest.xml"})
public class QRCodeTest {

	@Autowired
	WeiXinBase weixinBase;
	@Test
	public void getQRCode(){
		System.out.println(WeiXinBase.getApp_ID());
	}
}
