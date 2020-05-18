package com.ai.api.common;

import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/18
 * @description: 这是基础类，方便每条用例进行extends,每条用例在implements ItestCase中的runScript时，
 * 需要对传入正常流程有效的参数返回的结果进行存储redis,方便其他用例可以读取返回结果作为参数的传入
 */
public class BaseCase {

    public static String storeResponse(Map<String,String> excelData, String excelKey, String expectedParam, String responseJson){
        if(responseJson.contains("期望值例如000000")){
            if(excelData.get(excelKey).equals(expectedParam)){
                System.out.println("存储返回值JsonStr,进行存储redis"+responseJson);
                return responseJson;
            }
        }else {
            System.out.println("返回值不正确，不存储内容,返回null");
        }
        return null;
    }
}
