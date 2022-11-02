package com.saber.spring_boot_web_demo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
                .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE,false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                .configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT,true)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true)
                ;
    }
}