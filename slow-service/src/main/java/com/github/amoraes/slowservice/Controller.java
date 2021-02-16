package com.github.amoraes.slowservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/")
@Slf4j
public class Controller {

    private int i = 0;

    @RequestMapping
    public Mono<String> hello() {
        log.info("Request received at / ({})", i++);
        return Mono.delay(Duration.ofSeconds(5)).thenReturn("Hello!");
    }

}
