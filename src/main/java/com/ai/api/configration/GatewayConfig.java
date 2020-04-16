package com.ai.api.configration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午8:03
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "gateway")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GatewayConfig {

    String sandbox;

    String uat;
}
