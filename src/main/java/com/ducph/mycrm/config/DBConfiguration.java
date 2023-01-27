package com.ducph.mycrm.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Data
@Slf4j
public class DBConfiguration {

    private String url;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        log.info("DB Connection for dev profile");
        log.info("URL: " + url);
        return "DB Connection for dev profile";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        log.info("DB Connection for prod profile");
        log.info("URL: " + url);
        return "DB Connection for dev profile";
    }
}
