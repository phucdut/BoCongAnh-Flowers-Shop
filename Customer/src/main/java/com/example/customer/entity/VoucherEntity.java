package com.example.customer.entity;

import com.example.customer.enums.VoucherType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
public class VoucherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String icon;
    private String code;
    private Long percentage;
    private int usageLimit;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean conditionsPaymentOnline;
    private double conditionPrice;
    private boolean expired;
    private VoucherType type;
}
