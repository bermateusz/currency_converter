package com.bereda.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {


    @Bean
    public RestTemplate restTemplateBean(final ObjectMapper objectMapper) {
        return new RestTemplate();
    }

}
