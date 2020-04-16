package com.ai.api.support.dataDriver;

import com.ai.api.support.exception.TestParamPoolException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午10:52
 * @description: 临时存储接口获取的数据
 */
public class AssociatedParam {
    private AssociatedParam(){}
    private static final class Singleton{
        private static AssociatedParam instance = new AssociatedParam();
    }
    public static AssociatedParam getInstance(){
        return Singleton.instance;
    }

    private static Map<String,Object> paramMap = new HashMap<>();

    /**
     * 通过key获取存储的value
     * @param key
     * @return
     */
    public Object getParamMapValueByKey(String key){
        Set<String> keySet = paramMap.keySet();
        if(!keySet.contains(key)){
            throw new TestParamPoolException("关联参数Map中不存在当前key【"+key+"】");
        }else {
            return paramMap.get(key);
        }
    }

    /**
     * k-v 存储数据
     * @param key
     * @param value
     */
    public void putKeyValue(String key,Object value){
        paramMap.put(key,value);
    }
}
