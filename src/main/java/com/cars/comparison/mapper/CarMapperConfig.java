package com.cars.comparison.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarMapperConfig {

    @Bean
    public CarMapper carMapper() {
        return CarMapper.INSTANCE;
    }
}
