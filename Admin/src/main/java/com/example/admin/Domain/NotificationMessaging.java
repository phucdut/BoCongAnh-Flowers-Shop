package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationMessaging {
    private String title;

    private String body;

    private String image;

    private Notification data;
}