package com.example.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    WAITING,
    CONFIRMED,
    SENT,
    RECEIVED,
    CANCELLED,
    REJECT;
}
