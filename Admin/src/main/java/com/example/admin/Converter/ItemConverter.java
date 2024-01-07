package com.example.admin.Converter;

import com.example.admin.Domain.Item;
import com.example.admin.Entity.ItemEntity;

public class ItemConverter {
    public static Item toModel(ItemEntity itemEntity) {
        Item item = new Item();
        item.setId(itemEntity.getId());
        item.setName(itemEntity.getName());
        item.setDeleted(itemEntity.isDeleted());

        return item;
    }
    public static ItemEntity toEntity(Item item){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setDeleted(false);

        return itemEntity;
    }
}
