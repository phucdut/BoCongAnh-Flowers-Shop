package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Revenue {
    private Long id;

    private LocalDate date;

    private Long totalRevenue;
}
