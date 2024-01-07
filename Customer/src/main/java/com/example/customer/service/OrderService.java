package com.example.customer.service;

import com.example.customer.domain.Order;
import com.example.customer.responseBody.ResponsePayment;

public interface OrderService {
    Long createOrder(Order order, String name);

    String createQrPayment(Long orderId);

    Order checkoutOrder(Order orderRequest, String name);

    ResponsePayment createResponsePayment(Long orderId);

    String createUrlPayment(Long orderId);
}
