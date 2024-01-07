package com.example.customer.domain;

import com.example.customer.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;
    private LocalDateTime orderDateTime;
    private Long totalPrice;
    private Long discount;
    private Long amount;
    private Boolean confirmed;
    private Boolean status;
    private String note;
    private String informationRelated;
    private OrderStatus orderStatus;
    private Long shipPrice;
    private boolean paymentOnline;
    private Long addressId;
    private Long voucherId;
    private List<Voucher> vouchers;
    private List<CartItem> cartItems;
}
