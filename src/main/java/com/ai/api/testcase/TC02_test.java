package com.ai.api.testcase;

import com.ai.api.common.ITestCase;
import com.ai.api.support.dataDriver.AssociatedParam;
import com.ai.api.support.dataDriver.Check;
import com.ai.api.services.ITestCaseSv;
import com.ai.api.support.dataDriver.TestParamPool;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:37
 * @description:
 */
@Service
@Slf4j
public class TC02_test implements ITestCase {

    @Autowired
    ITestCaseSv sv;

    @Autowired
    Check check;


    @Override
    public void initTestData() {

    }

    @Override
    public void runScript(Map<String, String> map) {
        log.info(Thread.currentThread().getName()+"tc02线程");
        TestParamPool testParamPool = new TestParamPool(map);
        int index = testParamPool.getInt("index");
        JSONObject obj = sv.obtainMap(index);

//        Check.verifyJsonResultContainsStr(testParamPool.getString(VerifyEnum.CHECK_RESULT_DATA_COLUMN.getValue()),obj.get("key").toString());
    }

    @Override
    public void destroyTestData() {

    }
}
