package com.example.customer.domain;

import com.example.customer.enums.VoucherType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Voucher {
    private Long id;
    private String title;
    private String icon;
    private String code;
    private double percentage;
    private int usageLimit;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean conditionsPaymentOnline;
    private double conditionPrice;
    private boolean expired;
    private VoucherType type;
}
