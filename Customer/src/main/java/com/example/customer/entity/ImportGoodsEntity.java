package com.example.customer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "import_goods")
@Getter
@Setter
public class ImportGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timeImport;

    private Long staff_id;

    private double totalPrice;

    @OneToMany(mappedBy = "importGoodsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ImportGoodsDetailEntity> importGoodsDetailEntities;
}
