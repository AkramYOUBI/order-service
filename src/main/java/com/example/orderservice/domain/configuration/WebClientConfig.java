package com.example.orderservice.domain.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced //LoadBalanced is used, so we can call the instances one by one
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
