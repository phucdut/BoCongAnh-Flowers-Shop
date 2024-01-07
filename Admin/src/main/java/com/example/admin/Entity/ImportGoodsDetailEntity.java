package com.example.admin.Entity;

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
    @JoinColumn(name = "imPortGood_id")
    private ImportGoodsEntity importGoodsEntity;

    private Long itemPrice;

    private int quantity;
}
