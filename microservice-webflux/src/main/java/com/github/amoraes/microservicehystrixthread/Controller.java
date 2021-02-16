package com.github.amoraes.microservicehystrixthread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
@Slf4j
public class Controller {

    private final SlowServiceClient slowServiceClient;

    private int i = 0;

    public Controller(SlowServiceClient slowServiceClient) {
        this.slowServiceClient = slowServiceClient;
    }

    @RequestMapping
    public Mono<String> hello() {
        log.info("Request received at / {}", i++);
        return slowServiceClient.hello();
    }

}
