package com.flipkartservice.cartservice.order_management;

import com.flipkartservice.cartservice.order_management.model.CreateOrderWithDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<UUID> createOrder(@RequestBody CreateOrderWithDetails order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    }
}
