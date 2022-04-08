package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class EndPointAzureTest {
		
		@Operation(summary = "Greetings")
		@GetMapping("/myCoolGreet")
		public String index() {
			return "Greetings! What a cool day";
		}
	
}
