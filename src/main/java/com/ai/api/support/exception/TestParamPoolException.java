package com.ai.api.support.exception;

import java.io.Serializable;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午7:01
 * @description:
 */
public class TestParamPoolException extends RuntimeException{


    private static final long serialVersionUID = 4136782380159246336L;

    private String message;

    public TestParamPoolException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
