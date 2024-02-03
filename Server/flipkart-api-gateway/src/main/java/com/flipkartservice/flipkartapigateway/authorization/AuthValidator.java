package com.flipkartservice.flipkartapigateway.authorization;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthValidator {
    private final List<String> securedRequests = List.of("/cart", "/cart/addItem", "/cart/remove", "/cart/updateQuantity", "/order/create", "/payment/createTransaction");


   public Predicate<ServerHttpRequest> isSecuredRequest = request -> securedRequests.stream().anyMatch(uri -> request.getURI().getPath().contains(uri));
}
