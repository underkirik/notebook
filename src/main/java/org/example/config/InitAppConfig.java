package org.example.config;

import org.example.InitNotebook;
import org.example.Notebook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class InitAppConfig {

    @Bean
    public Notebook notebook() {
        return new InitNotebook();
    }

}
