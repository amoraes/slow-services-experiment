package com.github.amoraes.microservicehystrixthread;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "http://localhost:8001", name = "slow-service")
public interface SlowServiceClient {

    @RequestMapping
    String hello();

}
