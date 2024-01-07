package com.example.admin.Converter;

import com.example.admin.Domain.ImportGoodsDetail;
import com.example.admin.Entity.ImportGoodsDetailEntity;

public class ImportGoodsDetailConverter {
    public static ImportGoodsDetail toModel(ImportGoodsDetailEntity importGoodsDetailEntity) {
        ImportGoodsDetail importGoodsDetail = new ImportGoodsDetail();
        importGoodsDetail.setId(importGoodsDetailEntity.getId());
        importGoodsDetail.setQuantity(importGoodsDetailEntity.getQuantity());
        importGoodsDetail.setPrice_Item(importGoodsDetailEntity.getItemPrice());

        importGoodsDetail.setImportGood(ImportGoodsConverter.toModel(importGoodsDetailEntity.getImportGoodsEntity()));
        importGoodsDetail.setItem(ItemConverter.toModel(importGoodsDetailEntity.getItemEntity()));

        return importGoodsDetail;
    }
}
