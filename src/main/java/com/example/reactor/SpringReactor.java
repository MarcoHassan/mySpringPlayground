package com.example.reactor;

import reactor.core.publisher.Flux;

public class SpringReactor {
    
    public static void createAFlux_just() {
	Flux<String> fruitFlux = Flux
	    .just("Apple", "Orange", "Grape", "Banana", "Strawberry");

	fruitFlux.subscribe(
			    f -> System.out.println("Here's some fruit: " + f)
			    );    

    }
}





