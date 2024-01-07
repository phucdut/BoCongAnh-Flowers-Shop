package com.example.customer.entity;

import com.example.customer.domain.Address;
import com.example.customer.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_histories")
@Getter
@Setter
public class OrderHistoryEntity {
    @Id
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

    private Long addressId;

    private String address;

    private String nameCustomerReceive;

    private String phoneCustomerReceive;

    private OrderStatus orderStatus;

    private boolean paymentOnline;

    private Long shipPrice;

    private boolean reviewed;

    @OneToMany(mappedBy = "orderHistoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailHistoryEntity> orderDetailHistoryEntities;
}
