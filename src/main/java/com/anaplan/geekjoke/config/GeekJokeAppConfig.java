package com.uday.geekjoke.config;

import com.uday.geekjoke.filter.ResponseHeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeekJokeAppConfig {

    @Bean
    public ResponseHeadersFilter responseHeadersFilter(){
        return new ResponseHeadersFilter();
    }

}