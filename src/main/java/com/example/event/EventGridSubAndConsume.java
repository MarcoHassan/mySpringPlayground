package com.example.event;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import EventGridConsumer

@RestController
public class EventGridSubAndConsume {

	@PostMapping("/eventUpdate")
	public String getAndReturnJson(@RequestBody String helloworld) 		
		 throws Exception {

			  return helloworld;

			}
	
	
    
    // For CloudEvent
//    EventGridPublisherClient<CloudEvent> cloudEventClient = new EventGridPublisherClientBuilder()
//        .endpoint("<endpoint of your event grid topic/domain that accepts CloudEvent schema>")
//        .credential(new AzureKeyCredential("<key for the endpoint>"))
//        .buildCloudEventPublisherClient();    

	// 
	
}
