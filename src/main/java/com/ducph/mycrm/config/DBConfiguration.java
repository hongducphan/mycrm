package com.ducph.mycrm.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Data
public class DBConfiguration {
    
    private Logger logger = LoggerFactory.getLogger(DBConfiguration.class);
    
    private String url;
    private String username;
    private String password;
    
    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        logger.info("DB Connection for dev profile");
        logger.info("URL: " + url);
        logger.info("Username: " + username);
        logger.info("Password: " + password);
        return "DB Connection for dev profile";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        logger.info("DB Connection for prod profile");
        logger.info("URL: " + url);
        logger.info("Username: " + username);
        logger.info("Password: " + password);
        return "DB Connection for dev profile";
    }
}
