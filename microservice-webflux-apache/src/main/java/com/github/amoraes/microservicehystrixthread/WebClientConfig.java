package com.github.amoraes.microservicehystrixthread;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.ofDefaults;

@Configuration
public class WebClientConfig {

    @Bean
    public CloseableHttpAsyncClient httpAsyncClient() {
        return HttpAsyncClients.custom().setMaxConnPerRoute(5000).setMaxConnTotal(5000).build();
    }

}
