package com.ai.api.support.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/16 上午9:18
 * @description:
 */

@Slf4j
public class JDBCOperatorImpl implements IJDBCOperator{

    private DataSource dataSource;

    public JDBCOperatorImpl(DataSource dataSource){
        Assert.notNull(dataSource,"DataSource must not be null");
        this.dataSource = dataSource;
    }
    public DataSource getDataSource(){
        return dataSource;
    }

    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd=null;


    @Override
    public List<String> query(String sql, String mapKey) {
        List<String> mapVaule = new ArrayList<String>();
        List<Map<String,Object>> list = result(sql);
        for(Map<String,Object> map: list){
            for(Map.Entry<String,Object> m : map.entrySet()){
                if(mapKey.equalsIgnoreCase(m.getKey())){
                    mapVaule.add(m.getValue().toString());
                }
            }
        }
        log.info("数据库表中字段【"+mapKey+"】对应column值为："+mapVaule);
       return mapVaule;
    }

    @Override
    public int update(String sql) {
        int update =0;
        try {
            stmt = getConnection().createStatement();
            log.info("创建数据库statement连接");
            DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
            update = stmt.executeUpdate(sql);
            log.info(String.format("执行sql语句[%s],且该语句返回结果数为[%s]",sql,update));
            if (update !=0) {
                log.info(String.format("由于语句[%s]执行为结果为[%s]",sql,update));
                return update;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                JdbcUtils.closeStatement(stmt);
                log.info("关闭JDBCd stmt");
            }
            if(getConnection() != null){
                DataSourceUtils.releaseConnection(getConnection(),getDataSource());
                log.info("释放dataSource");
            }
        }
        return update;
    }

    @Override
    public boolean delete(String sql) {
        boolean delete = false;
        try {
            stmt = getConnection().createStatement();
            log.info("创建数据库statement连接");
            DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
            delete = stmt.execute(sql);
            log.info(String.format("执行sql语句[%s],且该语句返回结果为[%s]",sql,delete));
            if(!delete){
                log.info(String.format("由于语句[%s]执行为结果为[%s],返回结果为false就是删除成功",sql,delete));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                JdbcUtils.closeStatement(stmt);
                log.info("关闭JDBCd stmt");
            }
            if(getConnection() != null){
                DataSourceUtils.releaseConnection(getConnection(),getDataSource());
                log.info("释放dataSource");
            }
        }
        return delete;
    }

    /**
     *数据库的connnection
     */
    private Connection getConnection(){
        return DataSourceUtils.getConnection(getDataSource());
    }

    /**
     *数据库查询操作，私有方法，为查询提供服务
     */
    private List<Map<String,Object>> result(String sql){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();//返回所有记录
        int count= 0;
        try {
            stmt = getConnection().createStatement();
            log.info("创建数据库statement连接");
            DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
            rs = stmt.executeQuery(sql);
            log.info(String.format("执行sql查询语句【%s】",sql));
            rsmd = rs.getMetaData();
            count = rsmd.getColumnCount();
            while(rs.next()){
                Map  map = new HashMap();
                for(int i=1;i<=count;i++){
                    //获取指定列的表目录名称
                    String label=rsmd.getColumnLabel(i);
                    //以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
                    Object object= rs.getObject(i);
                    //把数据库中的字段名和值对应为一个map对象中的一个键值对
                    map.put(label.toLowerCase(), object);
                }
                list.add(map);
            }
            log.info("SQL语句【"+sql+"】返回的数据："+list.toString());
        }catch (SQLException ex){
            throw new DataAccessResourceFailureException("Could not obtain DataBase Date value",ex);
        }finally {
            if(rs != null){
                JdbcUtils.closeResultSet(rs);
                log.info("关闭JDBC的rs");
            }
            if(stmt != null){
                JdbcUtils.closeStatement(stmt);
                log.info("关闭JDBCd stmt");
            }
            if(getConnection() != null){
                DataSourceUtils.releaseConnection(getConnection(),getDataSource());
                log.info("释放dataSource");
            }
        }
        return list;
    }
}
