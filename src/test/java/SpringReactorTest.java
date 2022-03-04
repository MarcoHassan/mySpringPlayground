import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

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

}



