package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response {
    private List<OrderHistory> orderHistories;
    private String url;
}
