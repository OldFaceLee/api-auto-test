package com.ai.api.support.generatorCase;

import com.ai.api.configration.CaseGenerateConfig;
import com.ai.api.support.util.EasyExcelGenerator;
import com.ai.api.support.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/14 下午1:59
 * @description: 自动生成测试用例列表
 */
public class TestCaseListGenerator {

    public static void run(){
        FileUtil.getInstance().createFolder(CaseGenerateConfig.folderPath);
        EasyExcelGenerator.writeExcel(CaseGenerateConfig.folderPath+CaseGenerateConfig.fileName,CaseGenerateConfig.suitePackage);
    }

}
