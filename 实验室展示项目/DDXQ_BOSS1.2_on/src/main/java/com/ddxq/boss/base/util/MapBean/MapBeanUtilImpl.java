package com.ddxq.boss.base.util.MapBean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年5月7日 上午10:52:26 
* 类说明 
*/
@Component("mapBeanUtil")
public class MapBeanUtilImpl implements MapBeanUtil {
    // Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
    public Boolean transMap2Bean2(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return false;
        }
        try {
            BeanUtils.populate(obj, map);
            return true;
        } catch (Exception e) {
        	 
            System.out.println("transMap2Bean2 Error " + e);
            return false;
        }
    }

    // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    public  void transMap2Bean3(Map<String, Object> map, Object obj) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }

            }

        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }

        return;

    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public Map<String, Object> transBean2Map(Object obj) {

        if(obj == null){
            return null;
        }        
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

}
