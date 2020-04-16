package com.ai.api.testdata;

import com.ai.api.support.util.ExcelPoiUtil;
import com.ai.api.support.util.ReflectUtil;

import java.io.File;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午5:38
 * @description:
 */
public class MapDataProvider {

    private static final String TEST_PARAM_FILE = System.getProperty("user.dir")+ File.separator+"test-param"+File.separator+"testParam.xlsx";

    @org.testng.annotations.DataProvider(name = "tc01")
    public static Object[][] tc01(){
        return ExcelPoiUtil.getInstance().getTestData(TEST_PARAM_FILE,"tc01" );
    }

    @org.testng.annotations.DataProvider(name = "tc02")
    public static Object[][] tc02(){
        return ExcelPoiUtil.getInstance().getTestData(TEST_PARAM_FILE,"tc02" );
    }
}
