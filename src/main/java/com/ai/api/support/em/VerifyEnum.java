package com.ai.api.support.em;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午6:10
 * @description:
 */
public enum VerifyEnum {

    RESULT_CODE_OK("000000","返回成功"),

    CHECK_RESPONSE_CODE("checkReponseCode","Excel中checkReponseCode列，接口返回期望响应code值"),
    CHECK_RESPONSE_DATA_ASSERT_TYPE("CheckResponseDataAssertType","excel中CheckResponseDataAssertType列，接口返回数据断言类型，相等、包含"),
    CHECK_RESPONSE_DATA("checkResponseData","excel中checkResponseData列，接口返回值"),
    CHECK_SQL("checkSQL","excel中checkSQL列，需要执行的sql语句"),
    CHECK_SQL_RESULT_ASSERT_TYPE("checkSqlResultAssertType","excel中checkSqlResultAssertType列，sql查询断言类型，相等、包含"),
    CHECK_SQL_COLUMN("checkSqlResult","excel中checkSqlResult列，sql返回唯一字段column_Key");

    VerifyEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    String value;
    String desc;
}
