package com.flipkartservice.cartservice.payment_gateway;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private static final String RAZOR_PAY_KEY = "rzp_test_0VM4zQLgCr9QYy";
    private static final String RAZOR_PAY_SECRET = "GGq4HZOftGMDw7VHVSbaBufn";
    private static final String CURRENCY = "INR";

    Logger logger = LoggerFactory.getLogger(PaymentService.class);
    public TransactionOrderDetails createTransaction(Integer amount) {
        try{
            RazorpayClient razorpayClient = new RazorpayClient(RAZOR_PAY_KEY, RAZOR_PAY_SECRET);
            JSONObject newOrderRequest = new JSONObject();
            newOrderRequest.put("amount", amount * 100);
            newOrderRequest.put("currency", CURRENCY);
           Order order = razorpayClient.orders.create(newOrderRequest);
           JSONObject orderDetails = order.toJson();
           TransactionOrderDetails transactionOrderDetails = new TransactionOrderDetails(orderDetails.get("id").toString(), RAZOR_PAY_KEY, RAZOR_PAY_SECRET, Integer.parseInt(orderDetails.get("amount").toString()) , orderDetails.get("currency").toString());
           return transactionOrderDetails;
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }
}
