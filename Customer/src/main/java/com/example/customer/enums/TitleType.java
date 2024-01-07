package com.example.customer.enums;

import lombok.Getter;

@Getter
public enum TitleType {
    SALE,
    PAYMENT,
    WAITING,
    CONFIRMED,
    SENT,
    RECEIVED,
    CANCELLED,
    REJECT;
}
