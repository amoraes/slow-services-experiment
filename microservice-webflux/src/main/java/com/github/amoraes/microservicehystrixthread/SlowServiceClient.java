package com.github.amoraes.microservicehystrixthread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SlowServiceClient {

    private static final String URL = "http://localhost:8001/";

    private final WebClient webClient;

    public SlowServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> hello() {
        return webClient.get().uri(URL).retrieve().bodyToMono(String.class);
    }

}
