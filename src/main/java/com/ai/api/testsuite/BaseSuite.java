package com.ai.api.testsuite;

import com.ai.api.Application;
import com.ai.api.support.generatorCase.TestCaseListGenerator;
import com.ai.api.support.initTestNG.TestNGAdapter;
import com.ai.api.support.util.EasyExcelGenerator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.io.File;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:29
 * @description:
 */
@SpringBootTest(classes = Application.class)
public class BaseSuite extends AbstractTestNGSpringContextTests {
    public static void main(String[] args) {
//        TestCaseListGenerator.run();
        TestNGAdapter.run();
    }
}
