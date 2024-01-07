package com.example.customer.payment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BodyResponse {
    private String code;
    private String desc;
    private DataResponse data;
    private String signature;
}
