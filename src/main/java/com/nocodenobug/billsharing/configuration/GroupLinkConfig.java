package com.nocodenobug.billsharing.configuration;

import com.nocodenobug.billsharing.model.entity.GroupLink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupLinkConfig {

    @Bean
    public GroupLink getGroupLink() {
        return new GroupLink();
    }
}
