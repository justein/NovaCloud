package com.nova.sbnetty.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 用于在程序中获取spring context中的bean
 * @author Lyn
 * 2019-02-24 19:20:22
 * */
@Component
public class SpringFactoryUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if ( SpringFactoryUtil.applicationContext == null ){
            SpringFactoryUtil.applicationContext = applicationContext;
        }
        System.out.println("========ApplicationContext配置成功," +
                "在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象," +
                "applicationContext="+SpringFactoryUtil.applicationContext+"========");
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
