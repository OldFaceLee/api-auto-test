package com.ai.api.services;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:47
 * @description:
 */
public interface ITestCaseSv {

    JSONObject obtainList(String jsonParam);

    JSONObject obtainMap(int index);


}
