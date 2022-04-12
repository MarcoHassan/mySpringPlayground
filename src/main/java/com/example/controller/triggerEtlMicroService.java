package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dataobjects.jsonPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This class implements a basic controller that will trigger the asynchronous communication pattern with
 * the microservice performing the ETL job on excels. 
 * <p>
 * @author MARCO.HASSAN
 * @since 1.0
 */

@RestController
public class triggerEtlMicroService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	// just one short example, then the basic structure is equal
	public String testEndpoint() {
	 return restTemplate.getForObject("http://localhost:5000/api/v0/greetings",
     String.class);  
	}
	
	@Operation(summary = "Greetings")
	@GetMapping("/testAPI")
	public String index() {
		return this.testEndpoint();
	}
	
	// Create Objects with increasing IDs. Pass them to the endpoint and check how it is responding
	// just one short example, then the basic structure is equal
	public String testPostEndpoint(jsonPayload myload) throws JsonProcessingException {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
	    HttpEntity<String> request = 
	    	      new HttpEntity<String>(new ObjectMapper().writeValueAsString(myload), headers);
		
		return restTemplate.postForObject("http://localhost:5000/api/v0/slowprocess",
										   request, String.class);  
	}	

	
	@Operation(summary = "Greetings")
	@GetMapping("/sendMultipleRequest")
	public String testThreadsComputing() throws JsonProcessingException {
		
		String Id = "1";
		String message = "gee";
		String fileLocation = "hello";
		
		List<jsonPayload> myObRequest = new ArrayList(); 		
		jsonPayload test = new jsonPayload(Id, message, fileLocation);		
		myObRequest.add(test);
		
		// TODO create function that calls the postEndpoint and passes all of the objects
		
		return this.testPostEndpoint(test);
	}
	
}
