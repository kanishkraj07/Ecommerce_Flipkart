package com.flipkartservice.cartservice.cart_management;


import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartResource {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDetails>> getCartItems(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(cartService.getCartItemsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/addItem/{userId}")
    public ResponseEntity<CartDetails> setCartItemByUserId(@PathVariable("userId") UUID userId, @RequestBody CartDetails cartDetails) {
        return new ResponseEntity<>(cartService.setCartItem(userId, cartDetails), HttpStatus.OK);
    }

    @PostMapping("/remove/{cartId}")
    public ResponseEntity<HttpStatus> removeCartItem(@PathVariable("cartId") UUID cartId) {
        cartService.removeCartItemForUser(cartId);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateQuantity/{cartId}")
    public ResponseEntity<HttpStatus> updateQuantity(@PathVariable("cartId") UUID cartId, @QueryParam("quantity") Integer quantity) {
        cartService.updateCartQuantity(cartId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
