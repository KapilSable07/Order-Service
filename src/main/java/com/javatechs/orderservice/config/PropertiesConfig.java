package com.javatechs.orderservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("limit-service")
public class PropertiesConfig {
    private int minimum;
    private int maximun;

}
