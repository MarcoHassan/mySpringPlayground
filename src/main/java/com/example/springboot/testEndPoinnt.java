package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class testEndPoinnt {
	@Operation(summary = "Greetings")
	@GetMapping("/helloWorld")
	public String hello() {
		return "EESTWOOOORLDYOLOOOO";
	}

}
