package com.kt.myspringboot.config;

import com.kt.myspringboot.dto.AccountDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {
    @Bean
    public AccountDTO account() {
        return AccountDTO.builder() //AccountDTOBuilder
                .username("test")
                .password("test1234")
                .mode("Test 환경")
                .build();
    }
}
