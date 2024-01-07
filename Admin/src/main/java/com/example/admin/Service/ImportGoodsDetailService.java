package com.example.admin.Service;

import com.example.admin.Domain.ImportGoodsDetail;
import com.example.admin.Entity.ImportGoodsDetailEntity;

import java.util.List;

public interface ImportGoodsDetailService {
    List<ImportGoodsDetail> getImportGoodsDetailByImportGoodsId(Long importGoodsId);

    List<ImportGoodsDetailEntity> findByImportGood(Long importGoodId);
}
