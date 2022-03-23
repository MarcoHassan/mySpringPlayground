import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

public class SpringReactorTest {
	@Test
	public void createAFlux_range() { // check at the range function. similar to Python.
	 Flux<Integer> intervalFlux =
	 Flux.range(1, 5);
	 StepVerifier.create(intervalFlux)
	 .expectNext(1)
	 .expectNext(2)
	 .expectNext(3)
	 .expectNext(4)
	 .expectNext(5)
	 .verifyComplete();
	}
	
	@Test
	@Disabled("For demonstration purposes")
    @Timeout(value = 3, unit = TimeUnit.SECONDS)	// see how this is failing. just passing one thing every second
	public void createAFlux_interval() {
	 Flux<Long> intervalFlux =
	 Flux.interval(Duration.ofSeconds(1)) // see how 
	 .take(5);
	 StepVerifier.create(intervalFlux)
	 .expectNext(0L)
	 .expectNext(1L)
	 .expectNext(2L)
	 .expectNext(3L)
	 .expectNext(4L)
	 .verifyComplete();
	}
	
	@Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)	// see how this is failing. just passing one thing every second
	public void createAFlux_intervalHighTimeout() {
	 Flux<Long> intervalFlux =
	 Flux.interval(Duration.ofSeconds(1)) // see how 
	 .take(5);
	 StepVerifier.create(intervalFlux)
	 .expectNext(0L)
	 .expectNext(1L)
	 .expectNext(2L)
	 .expectNext(3L)
	 .expectNext(4L)
	 .verifyComplete();
	}
	
	// Merging Flux
	@Test
	public void mergeFluxes() {
		
	 Flux<String> characterFlux = Flux
	 .just("Garfield", "Kojak", "Barbossa")
	 .delayElements(Duration.ofMillis(500)); // delay streaming time of each element
	 
	 Flux<String> foodFlux = Flux
	 .just("Lasagna", "Lollipops", "Apples")
	 .delaySubscription(Duration.ofMillis(250)) // i.e. 250 starts sending messages to subscriber. you see then these messages within the one above.
	 .delayElements(Duration.ofMillis(500));
	 
	 Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux); // merging the two fluxes 
	 
	 StepVerifier.create(mergedFlux)
	 .expectNext("Garfield")
	 .expectNext("Lasagna")
	 .expectNext("Kojak")
	 .expectNext("Lollipops")
	 .expectNext("Barbossa")
	 .expectNext("Apples")
	 .verifyComplete();
	}
	
	
	// Note that mergewith cannot guarantee a perfect back and forth between its sources,
	// you may want to consider the zip() operation instead. 
	// When two Flux objects ar zipped together, it results in a new Flux that produces a tuple of items, where the
	// tuple contains one item from each source Flux.
	// Note this is the exact same function you already encountered in Spark
	
	@Test
	public void zipFluxes() {
	 Flux<String> characterFlux = Flux
	 .just("Garfield", "Kojak", "Barbossa");
	 Flux<String> foodFlux = Flux
	 .just("Lasagna", "Lollipops", "Apples");
	 Flux<Tuple2<String, String>> zippedFlux =
	 Flux.zip(characterFlux, foodFlux);
	 StepVerifier.create(zippedFlux)
	 .expectNextMatches(p ->
	 p.getT1().equals("Garfield") &&
	 p.getT2().equals("Lasagna"))
	 .expectNextMatches(p ->
	 p.getT1().equals("Kojak") &&
	 p.getT2().equals("Lollipops"))
	 .expectNextMatches(p ->
	 p.getT1().equals("Barbossa") &&
	 p.getT2().equals("Apples"))
	 .verifyComplete();
	}	 

}



