package com.example.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
	 * @return
	 */
	// Current having inssue with this point 3.1.1
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
		
	@NoArgsConstructor
	@AllArgsConstructor
	class myHateoasClass extends RepresentationModel<myHateoasClass> {

		  String test1, test2;
		}
		
	
}


