package com.ai.api.support.database;

import java.util.List;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/16 上午9:22
 * @description:
 */
public interface IJDBCOperator {

    public List<String> query(String sql,String mapKey);

    public int update(String sql);

    public boolean delete(String sql);


}
