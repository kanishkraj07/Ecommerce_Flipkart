package com.flipkartserver.accountservice.customer;


import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/isAccountRegistered")
    public ResponseEntity<Boolean> isAccountRegistered(@RequestParam("mobileNo") long mobileNo) throws AccountNotFoundException {
        return new ResponseEntity<>(customerService.isAccountRegistered(mobileNo), HttpStatus.OK);
    }

    @PostMapping("/register/{customerId}")
    public ResponseEntity<CustomerAccountDetails> registerAccount(@PathVariable("customerId") UUID customerId, @QueryParam("mobileNo") long mobileNo) {
        return new ResponseEntity<>(customerService.registerAccount(customerId, mobileNo), HttpStatus.OK);
    }

    @GetMapping("/accountId")
    public ResponseEntity<UUID> getAccountId(@RequestParam("mobileNo") long mobileNo) {
        return new ResponseEntity<>(customerService.getAccountId(mobileNo), HttpStatus.OK);
    }
}
