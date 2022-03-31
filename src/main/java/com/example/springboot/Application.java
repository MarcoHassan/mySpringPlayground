package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication @ComponentScan(basePackages = { "com.example.swagger","com.example.blob"} )

public class Application {

    public static void main(String[] args) {
		
    	ApplicationContext ctx = SpringApplication.run(Application.class, args); // entry point Spring Application
		
    	// Testing Reactor in Srping - did not want to return to endpoint
		// SpringReactor.createAFlux_just();
	} 

}
