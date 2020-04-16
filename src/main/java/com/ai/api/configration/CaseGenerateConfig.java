package com.ai.api.configration;


import com.ai.api.support.util.FileUtil;

import java.io.File;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/14 下午3:05
 * @description:
 */

public class CaseGenerateConfig {

    public static String fileName = "testcase_auto_generator.xlsx";

    public static String sheetName = "apiTestSuite";

    public static String suitePackage = "com.ai.api.testsuite.TestSuite";

    public static String folderPath = System.getProperty("user.dir")+ File.separator+"test-case"+File.separator;


}
