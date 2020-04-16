package com.ai.api.support.util;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午5:52
 * @description:
 */
public class ReflectUtil {

    private ReflectUtil(){}
    private static class Singleton{
        private static final ReflectUtil instance = new ReflectUtil();
    }
    public static ReflectUtil getInstance(){
        return Singleton.instance;
    }

    public String getCurrentMehodName(int methodLevel){
        return Thread.currentThread().getStackTrace()[methodLevel].getMethodName();
    }

    public void invokeMethod(String clazzLocation,Map<String,String> mapParam) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(clazzLocation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method declaredMethod = clazz.getDeclaredMethod("runScript",Map.class);
        declaredMethod.invoke(clazz.newInstance(),mapParam);

    }

    /**
     * 获取注解带@Test的的方法名
     * @param clazzLocation
     * @return
     */
    public List<String> getTestAnnotationMethod(String clazzLocation) {
        List<String> methods = new ArrayList<>();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(clazzLocation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        String[] annStrs = null;
        for (int i = 0; i < declaredMethods.length; i++) {
            boolean isTestNGAnnPresent = declaredMethods[i].isAnnotationPresent(Test.class);
            if (isTestNGAnnPresent) {
                methods.add(declaredMethods[i].getName());
            }
        }
        return methods;
    }
}
