package com.example.jackson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jacksonTest {
	
	// Serialization //
	
//	@Test
//	public void whenSerializingUsingJsonGetter_thenCorrect()
//	  throws JsonProcessingException {
//	 
//	    MyBean bean = new MyBean(1, "My bean");
//
//	    String result = new ObjectMapper().writeValueAsString(bean);
//	    
//	    System.out.println(result);
//	 
//	    assertTrue(result.contains("My bean"));
//	    assertTrue(result.contains("1"));
//	}	

	@Test
	public void understandJsonValue()
	  throws JsonProcessingException, IOException {
		  
		    String result = new ObjectMapper()
		      .writeValueAsString(TypeEnumWithValue.TYPE1);
	    
	    System.out.println(result);
	 
//	    assertTrue(result.contains("My bean"));
//	    assertTrue(result.contains("1"));
	}		
	
	// Deserialization //
	
	@Test
	public void standardUnmarshalling()
	  throws IOException {
		  
		String json = "{\"id\":1,\"name\":\"My bean\"}";
	    
		MyBean bean = new ObjectMapper()
				          .readerFor(MyBean.class)
			              .readValue(json);
		
		System.out.println(bean.id + " " + bean.name + " " + bean.helloworld);
	 
//	    assertTrue(result.contains("My bean"));
//	    assertTrue(result.contains("1"));
	}		
	
}
