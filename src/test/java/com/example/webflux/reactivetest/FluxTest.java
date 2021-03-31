package com.example.webflux.reactivetest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class FluxTest {
    @Test
    public void testFlux(){
        Flux<String> fluxTest = Flux.just("test1", "test2", "test3")
                //.concatWith(Flux.error(new RuntimeException("Exception")));
                .log();
        fluxTest.subscribe(System.out::println, e -> System.err.println(e));

        StepVerifier.create(fluxTest)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void testMono(){
        Mono<String> stringMono = Mono.just("Mono 1");
        StepVerifier.create(stringMono)
                .expectNext("Mono 1")
                .verifyComplete();
    }

    @Test
    public void testMono_WithError(){
        Mono<String> stringMono = Mono.just("Mono 1");
        StepVerifier.create(stringMono)
                .expectNext("Mono 1")
                .verifyComplete();

        StepVerifier.create(Mono.error(new RuntimeException("Exception")))
                .expectError(RuntimeException.class)
                .verify();
    }
}
