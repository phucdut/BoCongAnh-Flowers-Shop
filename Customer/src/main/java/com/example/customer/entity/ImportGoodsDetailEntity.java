package com.example.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "import_goods_detail")
@Getter
@Setter
public class ImportGoodsDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name = "importGoods_id")
    private ImportGoodsEntity importGoodsEntity;

    private double itemPrice;

    private int quantity;
}
