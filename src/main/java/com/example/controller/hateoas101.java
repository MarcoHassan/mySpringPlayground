package com.example.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/** This is a 101 class for testing hateoas.
 * <p>
 * This will be important in order to program at level 3 of the rest maturity model.
 * Moreover, in such a way it will be possible for you to develop programs that programatically 
 * - out of the box - answer to the various calls.
 */
@RestController
public class hateoas101 {
	
	///////////////////////////////////////////////////////////////////////
	// HardCoded Links - Understand these objects and how you pass them  //
	///////////////////////////////////////////////////////////////////////
	
	@Operation(summary = "HETEOAS")
	@GetMapping(value = "/myCoolHATEOAS")
	public RepresentationModel<myHateoasClass> hateoasEndPoint () {

		Link link = Link.of("/something");
		
//		Link link = Link(String "heelo");
//				
//				entityLinks
//		          .linkFor(triggerEtlMicroService.class)
//		          .slash("recent")
//		          .withRel("recents");
		
		System.out.println(link.toString());
		System.out.println(link.getRel());
		
		
		myHateoasClass model = new myHateoasClass();
		model.test1 = "Dave";
		model.test2 = "Matthews";
		model.add(Link.of("https://blabla/people/42")); // see how this is returned. can you it. 
		                                                // client sent an Accept header set to application/hal+json
		System.out.println(model.toString());
		
		return model;
		
		// You can then have a table where you persist, shipped jobs, running jobs and completed jobs.

	}	
	
	@Operation(summary = "HETEOAS")
	@GetMapping(value = "/helloRap")
	public EntityModel<myHateoasClass> hateoasEndPoint3 () {
		
		myHateoasClass person = new myHateoasClass("Dave1", "Matthews1");
		EntityModel<myHateoasClass> model = EntityModel.of(person);

		model.add(Link.of("https://blabla/people/42")); // see how this is returned. can you it. 
		                                                // client sent an Accept header set to application/hal+json
		
		System.out.println(model.toString());
		
		return model;
		
		// You can then have a table where you persist, shipped jobs, running jobs and completed jobs.

	}		
	
	///////////////////////
	// Programmatic URLs //
	///////////////////////

	/** Link from specific class - no hard code. Get automatically uri.
	 * 
	 * This constructs the link out of the box collecting information from 
	 * the <b>specific class</b>. 
	 * 
	 * @return
	 */
	@Operation(summary = "HETEOAS")
	@GetMapping(value = "/testSelfContained")
	public EntityModel<myHateoasClass> outOfTheBoxLink () {
		
		myHateoasClass person = new myHateoasClass("Dave1", "Matthews1");
		EntityModel<myHateoasClass> model = EntityModel.of(person);

		model.add(linkTo(hateoas101.class).withRel("people")); // see how this is returned. can you it. 
		                                                			       // client sent an Accept header set to application/hal+json
		
		System.out.println(model.toString());
		
		return model;
		
		// You can then have a table where you persist, shipped jobs, running jobs and completed jobs.

	}		
	
	/** Link from specific method - no hard code. 
	 * 
	 * This constructs the link out of the box collecting information from 
	 * the specific <b>method</b>. 
	 * 
	 * Check as well {@link #affordance101()} - this is probably better for 
	 * referencing <b>other</b> methods.
	 */
	@Operation(summary = "HETEOAS")
	@GetMapping(value = "/testSelfContainedLink")
	public EntityModel<myHateoasClass> outOfTheBoxLinkMethod () {
		
		myHateoasClass person = new myHateoasClass("Dave1", "Matthews1");
		EntityModel<myHateoasClass> model = EntityModel.of(person);
		
        hateoas101 theController = WebMvcLinkBuilder.methodOn(hateoas101.class); // controller proxy. Needed for calling the method and getting the link.
		
		model.add(
				
				linkTo(theController.hateoasEndPoint())
			    .withSelfRel()
			    
				); 
		
		System.out.println(model.toString());
		
		return model;

	}
	
	/////////////////
	// Affordances //
	/////////////////
	
	/** This method performs a 101 example for working with Affordances
	 * 
	 * See <a href="https://docs.spring.io/spring-hateoas/docs/current/reference/html/#server.affordances">Affordances</a> 
	 * <p>
	 * In such a way it is possible to point to other methods and add them to the response 
	 * as affordances - i.e. pointing to such methods as things that the environent offers. 
	 * <p>
	 * Note that this should be used in order to point to other methods.
	 * This rather than through the example {@link #outOfTheBoxLinkMethod()} in order to make
	 * clear what is afforded by the system and what is inherent to the call you are making. 
	 * <p>
	 * Plus it is cool as in such a way you can as well register output, REST method etc.
	 * So in general much <b>more solid</b> than simply providing a link that you do not know how to use. 
	 * <p>
	 * In this sense the official documentation mentions: <i> REST-based resources 
	 * provide not just data but controls. The last ingredient to form a flexible 
	 * service are detailed affordances on how to use the various controls. </i>
	 */
	@Operation(summary = "HETEOAS")
	@GetMapping(value = "/testAffordance101", produces="application/json")
	public List<?> affordance101 () {
		
		// Note that it is possible to infer all of the response types and
		// schema with afford. In such a case your methods your objects must be
		// annotated with some other characteristic components. 
		// Skipping this for now. Want to start easy and understand the concept firs.
		// In order to do this start with the manual way of doing it. 
		// Then move to the other component.
		
		// See the official docu:
		// Pointing to those methods using the afford(…) methods will cause Spring
		// HATEOAS to analyze the request body and response types and capture 
		// metadata to allow different media type implementations to use that 
		// information to translate that into descriptions of the input and outputs.


		// Manual way of doing it:
		// Ok so still not very clean, but you get an idea of what is produced.
		// You can then search more and do more experiments and research when it 
		// will be useful.
		
        hateoas101 theController = WebMvcLinkBuilder.methodOn(hateoas101.class); 
		
		Link link = Affordances.of(
				linkTo(theController.hateoasEndPoint()).withSelfRel()
				) 
		    .afford(HttpMethod.POST) 
		    .withInputAndOutput(myHateoasClass.class) 
		    .withName("this is just an example - not actually doing that") 

		    .andAfford(HttpMethod.GET) 
		    .withOutput(myHateoasClass.class) 
		    // add Parameters just for completeness
//		    .addParameters(//
//		        QueryParameter.optional("name"),
//		        QueryParameter.optional("role")) 
		    .withName("search") 

		    .toLink();
		
//		myHateoasClass person = new myHateoasClass("Dave1", "Matthews1");
//		EntityModel<myHateoasClass> model = EntityModel.of(person, link);
		
		return link.getAffordances();
	}	
	
	// ---------------------------
	
	// Horrible inner class, just a way to quickly move for this 101 exercises.	
	@NoArgsConstructor
	@AllArgsConstructor
	class myHateoasClass extends RepresentationModel<myHateoasClass> {

		  String test1, test2;
		}
		
	
}


