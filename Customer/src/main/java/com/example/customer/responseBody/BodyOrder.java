package com.example.customer.responseBody;

import com.example.customer.domain.CartItem;
import com.example.customer.domain.Voucher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BodyOrder {
    private Long addressId;
    private Long voucherId;
    private boolean paymentOnline;
    private String note;
    private double shipPrice;
    private double totalPrice;
    private double discount;
    private double amount;
    private List<Voucher> vouchers;
    private List<CartItem> cartItems;
}
