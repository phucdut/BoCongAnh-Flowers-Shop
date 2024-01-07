package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ImportGood {
    private Long id;

    private LocalDateTime timeImport;

    private Long staff_id;

    private Long totalPrice;
}
