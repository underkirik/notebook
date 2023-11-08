package org.example.config;

import org.example.InitNotebook;
import org.example.Notebook;
import org.example.YamlPropertySourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application-init.yaml", factory = YamlPropertySourceFactory.class)
@Profile("init")
public class InitAppConfig {

    @Bean
    public Notebook notebook() {
        return new InitNotebook();
    }

}
