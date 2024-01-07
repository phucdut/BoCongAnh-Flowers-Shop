package com.example.admin.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categoryEntities", fetch = FetchType.EAGER)
    private List<ProductEntity> productEntities;

    private String image;

    private String detail;

    private boolean deleted;
}