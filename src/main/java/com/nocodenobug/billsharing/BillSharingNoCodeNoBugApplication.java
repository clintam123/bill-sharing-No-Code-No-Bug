package com.nocodenobug.billsharing;

import com.nocodenobug.billsharing.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;




@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableScheduling
@EnableWebSocketMessageBroker
public class BillSharingNoCodeNoBugApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillSharingNoCodeNoBugApplication.class, args);
    }

}

