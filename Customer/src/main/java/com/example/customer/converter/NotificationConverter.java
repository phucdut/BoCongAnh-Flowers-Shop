package com.example.customer.converter;

import com.example.customer.domain.Notification;
import com.example.customer.entity.NotificationEntity;

public class NotificationConverter {

    public static Notification toModel(NotificationEntity entity) {
        Notification notification = new Notification();
        notification.setId(entity.getId());
        notification.setType(entity.getType());
        notification.setTitle(entity.getTitle());
        notification.setContent(entity.getContent());
        notification.setImage(entity.getImage());
        notification.setDatetime(entity.getDatetime());
        notification.setHaveRead(entity.isHaveRead());
        return notification;
    }
}
