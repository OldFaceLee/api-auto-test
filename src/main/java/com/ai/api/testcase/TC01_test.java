package com.ai.api.testcase;

import com.ai.api.common.ITestCase;
import com.ai.api.constant.URi;
import com.ai.api.support.dataDriver.AssociatedParam;
import com.ai.api.support.dataDriver.Check;
import com.ai.api.services.ITestCaseSv;
import com.ai.api.support.em.CheckEnum;
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
public class TC01_test implements ITestCase {

    @Autowired
    ITestCaseSv sv;

    @Autowired
    URi uRi;

    @Autowired
    Check check;


    @Override
    public void initTestData() {

    }

    @Override
    public synchronized void runScript(Map<String, String> excelData) {
        log.info(Thread.currentThread().getName()+"tc01线程");
        TestParamPool testParamPool = new TestParamPool(excelData);
        String caseDesc = testParamPool.getString(CheckEnum.CASE_DESC.getValue());
        log.info("TC01_test: "+caseDesc);
        String sbuID = testParamPool.getString("sbuID");
        JSONObject obj = sv.obtainList(sbuID);
        String actualCode = obj.get("resultCode").toString();
        String expectCode = testParamPool.getString(CheckEnum.CHECK_RESPONSE_CODE.getValue());

        check.verifyResponseCodeEqualsExpect(expectCode,actualCode);
        check.verifyResponseDataEqualsExpect(testParamPool.getString(CheckEnum.CHECK_RESPONSE_DATA.getValue()),obj.getJSONObject("data").get("key").toString());
        check.verifyEqualsBySQL(testParamPool.getString(CheckEnum.CHECK_SQL.getValue()),testParamPool.getString(CheckEnum.CHECK_SQL_COLUMN.getValue()),testParamPool.getString(CheckEnum.CHECK_RESPONSE_DATA.getValue()));
    }

    @Override
    public void destroyTestData() {

    }

}
