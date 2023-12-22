package org.example.config;

import org.example.DefaultNotebook;
import org.example.Notebook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource(value = "classpath:application.properties")
@Profile("default")
public class DefaultAppConfig {

    @Bean
    public Notebook notebook(){
        return new DefaultNotebook();
    }

}
