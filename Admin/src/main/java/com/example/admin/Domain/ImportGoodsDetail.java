package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImportGoodsDetail {
    private Long id;

    private Item item;

    private ImportGood importGood;

    private int quantity;

    private Long price_Item;
}
