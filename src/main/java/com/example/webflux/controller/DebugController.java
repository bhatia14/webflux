package com.example.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class DebugController {

    @GetMapping("/test-flux")
    public Flux<Integer> testFlux(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/test-flux-stream" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> testFluxStream(){
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping("/test-mono")
    public Mono<Integer> testMono(){
        return Mono.just(1)
                .log();
    }
}
