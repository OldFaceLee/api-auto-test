package com.ai.api.support.initTestNG;

import com.ai.api.configration.CaseGenerateConfig;
import com.ai.api.reporter.ExtentTestNGIReporterListener;
import com.ai.api.support.util.ExcelPoiUtil;
import com.ai.api.testsuite.TestSuite;
import lombok.extern.slf4j.Slf4j;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.Arrays;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/14 上午11:29
 * @description:
 */
@Slf4j
public class TestNGAdapter {

    /**
     * 执行testNG的入口
     */
    public static void run(){
        String sheetName = CaseGenerateConfig.sheetName;
        String filePath = CaseGenerateConfig.folderPath+CaseGenerateConfig.fileName;
        String testNGThreadCount = ExcelPoiUtil.getCellValue(filePath,sheetName,1,7);
        if(isNeedParallel(filePath,sheetName)){
            runTestNG(runTestngGroups(filePath,sheetName),isNeedParallel(filePath,sheetName),Integer.parseInt(testNGThreadCount));
        }else {
            runTestNG(runTestngGroups(filePath,sheetName),false,0);
        }


    }
    /**
     * 执行testNG套件
     */
    private static void runTestNG(String groups,boolean isParallelByMethod,int testngThreadCount){
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{TestSuite.class});//设置执行的测试套件
        testNG.setGroups(groups); //执行的测试组
        log.info("执行testNG组=【"+groups+"】");
        Class[] listenerClass = {ExtentTestNGIReporterListener.class};//ReportNGReport监听器设置
        testNG.setListenerClasses(Arrays.asList(listenerClass));//ReportNGReport监听器设置
        //判断是否需要并发
        if(isParallelByMethod){
            testNG.setParallel(XmlSuite.ParallelMode.METHODS);
            log.info("设置testNG并发ByMethod");
            testNG.setThreadCount(testngThreadCount);
            log.info("设置testNG多线程数为"+testngThreadCount);

        }
        testNG.run();
    }


    /**
     *判断excel是否group传值，并且解析传入的group值
     */
    private static String runTestngGroups(String exceFile,String sheetName){
        String isRun = ExcelPoiUtil.getCellValue(exceFile,sheetName,1,4);
        if(isRun.equals("是")){
            return ExcelPoiUtil.getCellValue(exceFile,sheetName,1,5);
        }else {
            log.info("没有设置是否执行组，所以不执行！");
        }
        return null;
    }

    /**
     *判断是否开启testNG多线程模式，线程级别 = method,因为method是针对@Test方法进行多线程
     */
    private static boolean isNeedParallel(String exceFile,String sheetName){
        String isParallel = ExcelPoiUtil.getCellValue(exceFile,sheetName,1,6);
        boolean isNeedParallel = false;
        if(isParallel.equals("是")){
            return true;
        }
        return isNeedParallel;
    }
}
