package com.example.customer.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataRequest {
    private String name;
    private long price;
    private int quantity;
}
