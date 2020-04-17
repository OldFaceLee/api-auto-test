package com.ai.api.services.impl;

import com.ai.api.common.ITestCase;
import com.ai.api.services.ITestCaseSv;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:49
 * @description:
 */
@Service
public class TestCaseSvImpl implements ITestCaseSv {

    @Override
    public JSONObject obtainList(String jsonParam) {
        JSONObject obj = JSON.parseObject("{\n" +
                "\"resultCode\":\"000000\",\n" +
                "\"data\":{\n" +
                "\"key\":\"通过\",\n" +
                "\"value\":\"不通过\"\n" +
                "}}");
        if(jsonParam.equals("lixuejun")){
             obj = JSON.parseObject("{\n" +
                    "\"resultCode\":\"000000\",\n" +
                    "\"data\":{\n" +
                    "\"key\":\"测试服务的删除用例功能接口\",\n" +
                    "\"value\":\"不通过\"\n" +
                    "}}");
        }
        return obj;
    }

    @Override
    public JSONObject obtainMap(int index) {
        JSONObject obj = null;
        if(index == 3){
            obj = JSON.parseObject("{\n" +
                    "\"resultCode\":\"000000\",\n" +
                    "\"data\":{\n" +
                    "\"key\":\"正确\",\n" +
                    "\"value\":\"不正确\"\n" +
                    "}}");
        }
        return obj;
    }
}
