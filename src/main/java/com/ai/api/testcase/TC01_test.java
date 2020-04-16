package com.ai.api.testcase;

import com.ai.api.common.ITestCase;
import com.ai.api.constant.URi;
import com.ai.api.support.dataDriver.AssociatedParam;
import com.ai.api.support.dataDriver.Check;
import com.ai.api.services.ITestCaseSv;
import com.ai.api.support.em.VerifyEnum;
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


    @Override
    public void runScript(Map<String, String> map) {
        log.info(Thread.currentThread().getName()+"tc01线程");
        TestParamPool testParamPool = new TestParamPool(map);
        log.info("地址"+uRi.gatewayUrl());
        String sbuID = testParamPool.getString("sbuID");
        JSONObject obj = sv.obtainList(sbuID);
        String actual = obj.get("resultCode").toString();
        log.info("实际"+actual);
        String expect = testParamPool.getString(VerifyEnum.CHECK_RESULT_CODE_COLUMN.getValue());
        log.info("期望"+expect);
        AssociatedParam.getInstance().putKeyValue("actual",actual);
        Check.verifyResultCode(expect,actual);
        Check.verifyResultDataColumnValue(testParamPool.getString(VerifyEnum.CHECK_RESULT_DATA_COLUMN.getValue()),obj.getJSONObject("data").get("key").toString());
    }
}
