package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
		// ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
    	// Testing Reactor in Srping - did not want to return to endpoint
		SpringReactor.createAFlux_just();
	} 

}
