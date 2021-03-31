package com.example.webflux.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebFluxTest
class DebugControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testFlux_approach1() {

        Flux<Integer> result = webTestClient.get().uri("/test-flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(result)
                .expectSubscription()
                .expectNextCount(4)
                .verifyComplete();
    }


    @Test
    void testFlux_approach2() {

        webTestClient.get().uri("/test-flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .hasSize(4);
    }


    @Test
    void testFlux_approach3() {

        webTestClient.get().uri("/test-flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .hasSize(4);
    }

    @Test
    void testFlux_approach4() {
        List<Integer> expectedList = Arrays.asList(1,2,3,4);

        webTestClient.get().uri("/test-flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .consumeWith(result -> assertEquals(expectedList, result.getResponseBody()));
    }


    @Test
    void testFluxStream() {
        Flux<Long> result = webTestClient.get().uri("/test-flux-stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();
        StepVerifier.create(result)
                .expectNext(0l)
                .expectNext(1l)
                .expectNext(2l)
                .thenCancel()
                .verify();

    }

    @Test
    void testMono() {

        webTestClient.get().uri("/test-mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith((res) -> assertEquals(new Integer(1), res.getResponseBody()));
    }

}