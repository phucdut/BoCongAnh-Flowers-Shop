package com.example.customer.service;

import com.example.customer.domain.Notification;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotification(String name);

    Long addNotifyOrder(String name, Long id);

    void sendNotifyOrder(HttpHeaders headers, Long notifyId, Long orderId);

    Long addNotifyPayment(String name, boolean success);

    void sendNotifyPayment(HttpHeaders headers, Long notifyId, boolean success);

    void sendNotifyPaymentApp(Long notifyId, boolean success);
}
