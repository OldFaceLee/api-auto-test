package com.ai.api.common;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:41
 * @description: 这是写case的标准，所有case都需要实现这个接口
 */
public interface ITestCase {

    void initTestData();

    void runScript(Map<String,String> excelData);

    void destroyTestData();

}
