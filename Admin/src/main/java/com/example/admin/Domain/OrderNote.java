package com.example.admin.Domain;

import lombok.Data;

@Data
public class OrderNote {

    private Long orderId;

    private String note;

    private String inforRelated;
}
