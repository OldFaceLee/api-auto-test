package com.ai.api.support.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/16 上午10:33
 * @description: 配置jdbc在springboot初始化时装配bean
 */
@Configuration
public class JdbcConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public IJDBCOperator ijdbcOperator(){
        return new JDBCOperatorImpl(dataSource);
    }
}
