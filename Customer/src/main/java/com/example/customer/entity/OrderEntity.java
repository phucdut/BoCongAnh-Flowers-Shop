package com.example.customer.entity;


import com.example.customer.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private boolean shipping;
    private Long shipPrice;
    private boolean paymentOnline;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private VoucherEntity voucherEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderDetailEntity> orderDetails;
}
