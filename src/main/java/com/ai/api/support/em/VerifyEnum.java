package com.ai.api.support.em;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午6:10
 * @description:
 */
public enum VerifyEnum {

    RESULT_CODE_OK("000000","返回成功"),

    CHECK_RESULT_CODE("checkReponseCode","excel中检测code列名"),
    CHECK_RESULT_DATA("checkResponseData","excel中检测字段值或者包含string列名"),
    CHECK_SQL("checkSQL","需要执行的sql语句"),
    CHECK_SQL_COLUMN("SqlResultColumn","sql返回的字段column_Key");

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
