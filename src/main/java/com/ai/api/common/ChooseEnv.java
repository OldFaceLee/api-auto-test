package com.ai.api.common;

import com.ai.api.configration.GatewayConfig;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午7:58
 * @description:
 */
@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChooseEnv {
    @Autowired
    GatewayConfig gatewayConfig;

    public String switchToGateway(){
        String url = null;
        if(gatewayConfig.getSandbox().length() ==0 && gatewayConfig.getUat().length() == 0){
            return null;
        }else if(gatewayConfig.getUat().length() == 0&& gatewayConfig.getSandbox().length() != 0){
            url = gatewayConfig.getSandbox();
        }else if(gatewayConfig.getUat().length() !=0 && getGatewayConfig().getSandbox().length() == 0){
            url = gatewayConfig.getUat();
        }else if(gatewayConfig.getUat().length() !=0 && getGatewayConfig().getSandbox().length() !=0){
            try {
                throw new Exception("沙箱URL与UAT的URL不能同时赋值");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}
