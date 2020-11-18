package com.bereda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }

    @Bean
    public Clock clockBean() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public String baseUrl(@Value("${apiRequest.url}") final String baseUrl){
        return baseUrl;
    }


}
