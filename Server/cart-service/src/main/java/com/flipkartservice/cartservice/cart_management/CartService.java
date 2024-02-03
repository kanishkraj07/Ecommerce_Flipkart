package com.flipkartservice.cartservice.cart_management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;
    public CartDetails setCartItem(UUID userId, CartDetails cartDetails) {
        CartDetails updatedCartDetails = CartDetails.builder()
                .setCartId(UUID.randomUUID())
                .setProductId(cartDetails.getProductId())
                .setQuantity(cartDetails.getQuantity())
                .build();
        try{
            cartDao.getUserIdIfPresent(userId);
        } catch (EmptyResultDataAccessException e) {
            cartDao.setUserIdInCart(userId);
        }
        cartDao.setCartItem(updatedCartDetails, userId);

        return updatedCartDetails;
    }

    public List<CartDetails> getCartItemsByUserId(UUID userId) {
       return cartDao.getCartItemsByUserId(userId);

    }

    public void removeCartItemForUser(UUID cartId) {
        cartDao.removeCartItem(cartId);
    }

    public void updateCartQuantity(UUID cartId, Integer quantity) {
        cartDao.updateQuantity(cartId, quantity);
    }
}
