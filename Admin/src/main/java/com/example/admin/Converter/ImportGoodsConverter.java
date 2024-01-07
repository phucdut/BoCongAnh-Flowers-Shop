package com.example.admin.Converter;

import com.example.admin.Domain.ImportGood;
import com.example.admin.Entity.ImportGoodsEntity;

public class ImportGoodsConverter {
    public static ImportGood toModel(ImportGoodsEntity importGoodsEntity) {
        ImportGood importGood = new ImportGood();
        importGood.setId(importGoodsEntity.getId());
        importGood.setTimeImport(importGoodsEntity.getTimeImport());
        importGood.setStaff_id(importGoodsEntity.getStaff_id());

        importGood.setTotalPrice(importGoodsEntity.getTotalPrice());

        return importGood;
    }
    public static ImportGoodsEntity toEntity(ImportGood importGood) {
        ImportGoodsEntity importGoodsEntity = new ImportGoodsEntity();
        importGoodsEntity.setId(importGood.getId());
        importGoodsEntity.setTimeImport(importGood.getTimeImport());
        importGoodsEntity.setStaff_id(importGood.getStaff_id());

        importGoodsEntity.setTotalPrice(importGood.getTotalPrice());

        return importGoodsEntity;
    }
}
