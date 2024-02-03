package com.flipkartservice.cartservice.payment_gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/createTransaction")
    public ResponseEntity<TransactionOrderDetails> prepareTransaction(@RequestParam("totalAmount") Integer amount) {
       return new ResponseEntity<>(paymentService.createTransaction(amount), HttpStatus.OK);
    }
}
