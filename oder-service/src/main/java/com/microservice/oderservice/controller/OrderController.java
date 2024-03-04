package com.microservice.oderservice.controller;


import com.microservice.oderservice.dto.OrderDTO;
import com.microservice.oderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    // tìm hiểu thêm về completableFuture
    // https://gpcoder.com/4064-lap-trinh-da-luong-voi-completablefuture-trong-java-8/

    @Autowired
    OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory") // đảm bảo rằng name trùng với cấu hình ở properties
    public CompletableFuture<String> placeOrder(@RequestBody OrderDTO orderDTO){
       return CompletableFuture.supplyAsync(() ->orderService.placeOrder(orderDTO)) ;

    }
    public CompletableFuture<String> fallbackMethod(OrderDTO orderDTO,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong,try another time :D") ;
    }
}
