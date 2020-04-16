package com.ai.api.support.util;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/14 下午1:38
 * @description:  获取testNG中@Test注解下的相关数据
 */
public class AnnotationUtil {
    private AnnotationUtil(){}
    private static AnnotationUtil instance = null;
    public static AnnotationUtil getInstance(){
        if(instance == null){
            instance = new AnnotationUtil();
        }
        return instance;
    }

    /**
     * 获取@Test里groups的值
     * @param clazzLocation
     * @return
     */
    public Map<String, List<String>> getAnnotations(String clazzLocation){
        Map<String,List<String>> map = new HashMap<>();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(clazzLocation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        String [] annStrs = null;
        String [][] rememberAnnStrs =  null;
        for (int i = 0; i < declaredMethods.length; i++) {
            boolean isTestNGAnnPresent = declaredMethods[i].isAnnotationPresent(Test.class);
            if(isTestNGAnnPresent){
                Test testAnn =  declaredMethods[i].getAnnotation(Test.class);
                annStrs = testAnn.groups();
                rememberAnnStrs = new String[][]{
                        annStrs
                };
                for(String[] strings : rememberAnnStrs){
                    map.put(declaredMethods[i].getName(), Arrays.asList(strings));
                }
            }
        }

        return map;
    }

    public Map<String,String> getTestAnnDescription(String clazzLocation){
        Map<String,String> map = new HashMap<>();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(clazzLocation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        String  annStrs = null;
        String [] rememberAnnStrs =  null;
        for (int i = 0; i < declaredMethods.length; i++) {
            boolean isTestNGAnnPresent = declaredMethods[i].isAnnotationPresent(Test.class);
            if(isTestNGAnnPresent){
                Test testAnn =  declaredMethods[i].getAnnotation(Test.class);
                annStrs = testAnn.description();
                rememberAnnStrs = new String[]{
                        annStrs
                };
                for(String strings : rememberAnnStrs){
                    map.put(declaredMethods[i].getName(), strings);
                }
            }
        }

        return map;
    }
}
