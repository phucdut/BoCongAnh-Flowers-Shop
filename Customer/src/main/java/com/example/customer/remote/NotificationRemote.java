package com.example.customer.remote;

import com.example.customer.domain.NotificationMessaging;
import com.example.customer.responseBody.CurrencyResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationRemote {
    public String sendNotification(NotificationMessaging notificationMessaging, HttpHeaders headers) {
        String url = "http://localhost:80/api/notification";

        HttpEntity<NotificationMessaging> requestEntity = new HttpEntity<>(notificationMessaging, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        return responseEntity.getBody();
    }
}
