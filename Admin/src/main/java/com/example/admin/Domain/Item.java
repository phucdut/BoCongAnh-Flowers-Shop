package com.example.admin.Domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;

    private String name;

    private boolean deleted;
}
