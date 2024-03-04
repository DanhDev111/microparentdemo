package com.microservice.oderservice.webconfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // Vi 1 service co the co nhieu instance
    // nó sẽ  confused không biết đang chạy cái nào Chính vì vậy cần client side loadbalanced
//    @Bean
//    public WebClient webClient(){
//        return WebClient.builder().build();
//    }
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
}
