package com.kt.myspringboot.config;

import com.kt.myspringboot.dto.AccountDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class ProdConfig {
    @Bean
    public AccountDTO account() {
        return AccountDTO.builder() //AccountDTOBuilder
                .username("prod")
                .password("prod1234")
                .mode("Production 환경")
                .build();
    }
}
