/**
 * FileName:    Action.java
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By JZSN
 * Copyright:   Copyright(C) 北京里里外外文化发展有限公司 2016
 * Company      北京里里外外文化发展有限公司
 * @author:   名字
 * @version   V1.0 
 * Createdate:        2016年4月13日 上午7:41:45
 *
 * Modification History:
 * Date        Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月13日  zhu xu zhen          0.1             0.1
 * Why & What is modified: <修改原因描述>
 */

package com.ddxq.common.aspectj.action;
/**
 * ClassName:Action <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月13日 上午7:41:45 <br/>
 * @author   zxz
 * @version  0.1
 * @since    JDK 1.7
 * @see 	 
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ddxq.common.constant.OperationConstant;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Action {
    String action() default OperationConstant.OPERATION_WEBACCESS;
}
