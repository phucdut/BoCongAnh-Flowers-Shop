package com.example.admin.Service.Impl;

import com.example.admin.Converter.NotificationConverter;
import com.example.admin.Domain.Notification;
import com.example.admin.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll().stream().map(NotificationConverter::toModel).toList();
    }
}
