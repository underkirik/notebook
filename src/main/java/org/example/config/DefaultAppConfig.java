package org.example.config;

import org.example.DefaultNotebook;
import org.example.Notebook;
import org.example.YamlPropertySourceFactory;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
@Profile("default")
@ComponentScan("org.example")
public class DefaultAppConfig {

    @Bean
    public Notebook notebook(){
        return new DefaultNotebook();
    }

}
