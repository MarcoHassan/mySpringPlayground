package com.example.dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class jsonPayload {
	
    @JsonProperty("Id")
	private String Id;
    
    @JsonProperty("message")
	private String message;
    
    @JsonProperty("fileLocation")
	private String fileLocation;
	
	public jsonPayload(String Id, String message, String fileLocation) {
		this.Id = Id;
		this.message = message;
		this.fileLocation = fileLocation;
		
	}

}
