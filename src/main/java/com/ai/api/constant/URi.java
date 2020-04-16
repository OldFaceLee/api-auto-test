package com.ai.api.constant;

import com.ai.api.common.ChooseEnv;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:20
 * @description:
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class URi {
    @Autowired
    ChooseEnv chooseEnv;

    public String gatewayUrl(){
        return chooseEnv.switchToGateway();
    }
}
