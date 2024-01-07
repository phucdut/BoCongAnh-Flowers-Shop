package com.example.admin.Domain;

import com.example.admin.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class OrderHistory {
    private Long id;

    private LocalDateTime orderDateTime;

    private Long totalPrice;

    private Long discount;

    private Long amount;

    private Long userId;

    private String fullNameStaff;

    private Long customerId;

    private String fullNameCustomer;

    private String phoneCustomer;

    private String emailCustomer;

    private OrderStatus orderStatus;

    private String nameCustomerReceive;

    private String phoneCustomerReceive;

    private String address;

    private boolean paymentOnline;

    private Long shipPrice;

    private boolean reviewed;

    private List<OrderDetailHistory> orderDetailHistories;
}
