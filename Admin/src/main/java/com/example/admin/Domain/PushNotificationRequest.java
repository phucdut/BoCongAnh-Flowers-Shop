package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String image;
    private String token;
}
