package com.example.admin.Service.Impl;

import org.springframework.context.ApplicationEvent;

public class OrderHistoryCreatedEvent extends ApplicationEvent {

    public OrderHistoryCreatedEvent(Object source) {
        super(source);
    }
}