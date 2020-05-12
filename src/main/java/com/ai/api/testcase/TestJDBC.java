package com.ai.api.testcase;

import com.ai.api.common.ITestCase;
import com.ai.api.support.database.IJDBCOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/16 上午10:18
 * @description:
 */
@Service
public class TestJDBC implements ITestCase {
    @Autowired
    IJDBCOperator jdbc;

    @Override
    public void initTestData() {
        MockMvc mockMvc;

    }

    @Override
    public void runScript(Map<String, String> map) {
        String sql = "SELECT * from tp_testcase where request_type = 'get'";
        String sqlUpdate = "update tp_testcase set case_id = 'neww' where request_type = 'get'";
        String sqlDel = "delete from tp_testcase where case_id = 'tc55555'";

        System.out.println("select：" + jdbc.query(sql,"case_id"));

    }

    @Override
    public void destroyTestData() {

    }
}
