package com.example.customer.service.Impl;

import com.example.customer.domain.NotificationMessaging;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.enums.TitleType;
import com.example.customer.repository.CustomerRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FCMService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public String pushNotification(String name, String content) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        Message message = Message.builder()
                .putData("content", content)
                .setToken(customerEntity.getToken())
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String sendNotificationByToken(NotificationMessaging notificationMessaging, String name) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationMessaging.getTitle())
                .setImage(notificationMessaging.getImage())
                .setBody(notificationMessaging.getBody())
                .build();

        CustomerEntity customer = customerRepository.findByUsername(name).orElseThrow();
        Map<String, String> data = new HashMap<>();
        data.put("id", notificationMessaging.getData().getId().toString());
        data.put("type", notificationMessaging.getData().getType().name());
        data.put("content", notificationMessaging.getData().getContent());
        data.put("datetime", notificationMessaging.getData().getDatetime().toString());
        if (notificationMessaging.getData().isHaveRead()) {
            data.put("haveRead", "1");
        } else {
            data.put("haveRead", "0");
        }
        Message message = Message
                .builder()
                .setToken(customer.getToken())
                .setNotification(notification)
                .putAllData(data)
                .build();
        try {
            System.out.println("da vao");
            firebaseMessaging.send(message);
            return "send success";
        } catch(FirebaseMessagingException e) {
            System.out.println("lỗi: " + e);
            return "error";
        }
    }
}