package com.ai.api.support.dataDriver;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午10:44
 * @description:  检查
 */
@Slf4j
public class Check {

    public static void verifyResultCode(String expect,String actualResultCode){
        log.info("期望值="+expect+", 实际值="+actualResultCode);
        Assert.assertEquals(expect,actualResultCode);
    }

    public static void verifyResultDataColumnValue(String expect,String actualResultData){
        log.info("期望值="+expect+", 实际值="+actualResultData);
        Assert.assertEquals(expect,actualResultData);
    }

    public static void verifyJsonResultContainsStr(String expect,String actualResultData){
        log.info("期望值="+expect+", 实际值="+actualResultData);
        Assert.assertTrue(actualResultData.contains(expect));
    }

    public static void verifyException(String expect,String actualException){
        log.info("期望值="+expect+", 实际值="+actualException);
        Assert.assertEquals(expect,actualException);
    }
}
