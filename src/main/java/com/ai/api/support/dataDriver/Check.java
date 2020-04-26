package com.ai.api.support.dataDriver;

import com.ai.api.support.database.IJDBCOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.util.List;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午10:44
 * @description:  检查工具类,因为用到了spring的JDBC,所以使用时候需要用@Autowired自动装载，不能new
 */
@Slf4j
@Service
public class Check {

    @Autowired
    IJDBCOperator jdbcOperator;

    /**
     *私有方法，共用相等对比
     */
    private void assertEquals(String expect,String actual){
        log.info("期望值="+expect+", 实际值="+actual);
        Assert.assertEquals(expect,actual);
    }

    /**
     * 验证返回状态吗是否【相等】
     */
    public void verifyResponseCodeEqualsExpect(String expect,String actualResultCode){
        this.assertEquals(expect,actualResultCode);
    }

    /**
     *验证返回指定的jsonKey字段的value是否与期望【相等】
     */
    public void verifyResponseDataEqualsExpect(String expect,String actualResultData){
        this.assertEquals(expect,actualResultData);
    }

    /**
     *验证实际返回的json中是否【包含】期望值
     */
    public void verifyResponseJsonContainsExpect(String expect,String actualResultData){
        log.info("期望值=【"+expect+"】, 实际值=【"+actualResultData+"】");
        Assert.assertTrue(actualResultData.contains(expect));
    }

    /**
     *验证捕获的异常是否与期望的异常e.print()相等
     */
    public void verifyExceptionEqualsExpect(String expect,String actualException){
        this.assertEquals(expect,actualException);
    }

    /**
     *数据库查询验证是否相等
     */
    public void verifyEqualsBySQL(String sql,String expectColumn,String expectResponseValue){
        if(sql == null || expectColumn == null){
            log.info("sql【"+sql+"】或者 checkSqlResult【"+expectColumn+"】为空，不执行JDBC" );
            return;
        }
        List<String> expect = jdbcOperator.query(sql,expectColumn);
        if(expect.size() > 0){
            log.info("执行sql返回字段【"+expectColumn+"】= 【"+expect.get(0)+"】");
            this.assertEquals(expectResponseValue,expect.get(0));
        }
    }
}
