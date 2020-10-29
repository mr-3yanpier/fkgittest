package com.fk.springboot.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("fk")
@RefreshScope
public class FKConfig {

    private String name;
    private Integer age;
}
