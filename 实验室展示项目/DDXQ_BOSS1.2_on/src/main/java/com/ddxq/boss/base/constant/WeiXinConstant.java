/**
 * FileName:    WeiXinConstant.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月19日 上午10:24:38
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月19日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.boss.base.constant;
/**
 * ClassName:WeiXinConstant <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月19日 上午10:24:38 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */
public class WeiXinConstant {
	/**
	 * 微信access_token不存在
	 */
    public static final int ERR_TOKEN_NOT_EXIST = -1;
    
    /**
	 * 微信access_token已存在，不用更新
	 */
    public static final int ERR_TOKEN_ALREADY_EXIST = -2;
    
    /**
     * 微信参数正常
     */
    public static final int OK = 0;
    /**
     * 微信参数失效
     */
    public static final int ERR_TOKEN_INVALID = -3;
}
