package com.flipkartservice.cartservice.cart_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_USER_ID_IN_CART = "SELECT user_id from user_cart_by_user_id where user_id=?";

    private static final String INSERT_CART_ITEM_BY_USER_ID = "INSERT INTO cart_details_by_cart_id (cart_id, product_id, quantity, user_id) values(?,?,?,?)";
    private static final String INSERT_USER_ID_IN_CART = "INSERT INTO user_cart_by_user_id(user_id) values(?)";
    private static final String REMOVE_CART_ITEM_BY_CART_ID = "DELETE from cart_db.cart_details_by_cart_id WHERE cart_details_by_cart_id.cart_id = ?";
    private static final String GET_CART_ITEMS_BY_USER_ID = "SELECT user_cart_by_user_id.user_id, cart_details_by_cart_id.cart_id, product_id, quantity from user_cart_by_user_id JOIN cart_details_by_cart_id on user_cart_by_user_id.user_id = cart_details_by_cart_id.user_id and cart_details_by_cart_id.order_id is null where user_cart_by_user_id.user_id=?";
    private static final String UPDATE_QUANTITY_BY_CART_ID = "update cart_details_by_cart_id set quantity = (?) where cart_id=?";


    public CartUser getUserIdIfPresent(UUID userId) {
      return  jdbcTemplate.queryForObject(SELECT_USER_ID_IN_CART, new CartUserRowMapper(), userId.toString());
    }

    public void setUserIdInCart(UUID userId) {
        jdbcTemplate.update(INSERT_USER_ID_IN_CART, userId.toString());
    }
    public void setCartItem(CartDetails cartDetails, UUID userId) {
            jdbcTemplate.update(INSERT_CART_ITEM_BY_USER_ID,
                    cartDetails.getCartId().toString(),
                    cartDetails.getProductId().toString(),
                    cartDetails.getQuantity(),
                    userId.toString());
    }

    public List<CartDetails> getCartItemsByUserId(UUID userId) {
        return jdbcTemplate.query(GET_CART_ITEMS_BY_USER_ID, new CartDetailsRowMapper(), userId.toString());
    }

    public void removeCartItem(UUID cartId) {
        jdbcTemplate.update(REMOVE_CART_ITEM_BY_CART_ID, cartId.toString());
    }

    public void updateQuantity(UUID cartId, Integer quantity) {
        jdbcTemplate.update(UPDATE_QUANTITY_BY_CART_ID, quantity, cartId.toString());
    }
}
