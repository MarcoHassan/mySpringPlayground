package com.example.springboot;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
@ComponentScan // Have to explicitly tell Spring to scan this configuration such that you will find it as a Bean
               // See more here: https://www.baeldung.com/spring-component-scanning
public class BeanConfiguration {
	
    @Bean
    public RestTemplate restTemplate() {
     return new RestTemplate();
    }

}
