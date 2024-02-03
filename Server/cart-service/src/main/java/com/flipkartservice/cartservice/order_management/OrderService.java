package com.flipkartservice.cartservice.order_management;

import com.flipkartservice.cartservice.order_management.model.CreateOrderWithDetails;
import com.flipkartservice.cartservice.order_management.status_enum.DeliveryStatus;
import com.flipkartservice.cartservice.order_management.status_enum.OrderStatus;
import com.flipkartservice.cartservice.order_management.status_enum.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    public UUID createOrder(CreateOrderWithDetails order) {

        Order newOrder = Order.builder()
                .setOrderId(UUID.randomUUID())
                .setUserId(order.getUserId())
                .setPaymentStatus(order.getPaymentStatus())
                .setTransactionId(order.getTransactionId())
                .setExpectedDelivery(calExpectedDelivery())
                .setOrderStatus(order.getPaymentStatus() == PaymentStatus.SUCCESS ? OrderStatus.CONFIRMED : OrderStatus.NOT_CONFIRMED)
                .setDeliveryStatus(DeliveryStatus.SHIPPED)
                .build();

        List<String> cartIds = order.getCartIds().stream()
                .map(UUID::toString)
                .collect(Collectors.toList());

        orderDao.createNewOrder(newOrder, String.join(",", cartIds));
        return newOrder.getOrderId();
    }

    private Date calExpectedDelivery() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 10);
       return new Date(calendar.getTime().getTime());
    }
}
