package com.example.admin.Domain;

import com.example.admin.enums.VoucherType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class VoucherDTO {
    private Long id;

    private String title;

    private MultipartFile icon;

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
