package com.flipkartservice.cartservice.order_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_ORDER_WITH_ORDER_DETAILS = "insert into order_details_by_order_id(order_id, transaction_id, payment_status, order_status, expected_delivery, delivery_status, user_id) values(?,?,?,?,?,?,?)";
    private static final String UPDATE_CART_WITH_ORDER_ID = "update cart_details_by_cart_id set order_id=? where cart_id IN (?)";
    public void createNewOrder(Order newOrder, String cartIds) {
        jdbcTemplate.update(CREATE_ORDER_WITH_ORDER_DETAILS,
                newOrder.getOrderId().toString(),
                newOrder.getTransactionId(),
                newOrder.getPaymentStatus().getStatus(),
                newOrder.getOrderStatus().getStatus(),
                newOrder.getExpectedDelivery(),
                newOrder.getDeliveryStatus().getStatus(),
                newOrder.getUserId().toString());
        jdbcTemplate.update(UPDATE_CART_WITH_ORDER_ID, newOrder.getOrderId().toString(), cartIds);
    }
}
