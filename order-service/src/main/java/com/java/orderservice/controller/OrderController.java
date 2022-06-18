package com.java.orderservice.controller;

import com.java.orderservice.dto.OrderRequestDto;
import com.java.orderservice.dto.OrderResonseDTO;
import com.java.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequestDto));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CompletableFuture<String> fallBackMethod(OrderRequestDto orderRequestDto,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Something went wrong please try again in sometime");
    }

//    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
//    public CompletableFuture<String> fallBackMethod(OrderRequestDto orderRequestDto, TimeoutException timeoutException){
//        return CompletableFuture.supplyAsync(()->"time out error");
//    }

}
