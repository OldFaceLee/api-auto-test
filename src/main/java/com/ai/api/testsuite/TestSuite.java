package com.ai.api.testsuite;

import com.ai.api.testcase.TC01_test;
import com.ai.api.testcase.TC02_test;
import com.ai.api.testcase.TestJDBC;
import com.ai.api.testdata.MapDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:35
 * @description:
 */
@Service
public class TestSuite extends BaseSuite {
    @Autowired
    TC01_test tc01_test;
    @Autowired
    TC02_test tc02_test;

    @Autowired
    TestJDBC testJDBC;

    @Test(groups = {"tc01","tc$"},description = "这就是一条测试用例",dataProviderClass = MapDataProvider.class,dataProvider = "tc01")
    public void tc011(Map<String,String> map){
        tc01_test.runScript(map);

    }
    @Test(groups = {"tc02","a","b"},dataProviderClass = MapDataProvider.class,dataProvider = "tc02")
    public void tc02(Map<String,String>map){
        tc02_test.runScript(map);
    }

    @Test(groups = {"jdbc"})
    public void TestJDBC(){
        testJDBC.runScript(null);
    }

    @AfterSuite
    public void pushReport(){
        System.out.println("推送了repoart"+new Date());
    }
}
