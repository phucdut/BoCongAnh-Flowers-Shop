package com.example.customer.responseBody;

import com.example.customer.domain.Address;
import com.example.customer.domain.Order;
import com.example.customer.domain.Voucher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePayment {
    private Order order;
    private Voucher voucher;
    private Address address;
    private String methodPayment;
    private String urlQR;
}
