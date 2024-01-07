package com.example.admin.Service.Impl;


import com.example.admin.Converter.ImportGoodsDetailConverter;
import com.example.admin.Domain.ImportGoodsDetail;
import com.example.admin.Entity.ImportGoodsDetailEntity;
import com.example.admin.Repository.*;
import com.example.admin.Service.ImportGoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImportGoodsDetailServiceImpl implements ImportGoodsDetailService {
    @Autowired
    private ImportGoodsDetailRepository importGoodsDetailRepository;

    @Autowired
    private ImportGoodsRepository importGoodsRepository;

    @Autowired
    private ItemRepository itemRepository;
    public List<ImportGoodsDetail> getImportGoodsDetailByImportGoodsId(Long importGoodsId) {
        return importGoodsDetailRepository.findAllByImportGoodsEntity(importGoodsRepository.findById(importGoodsId).orElseThrow()).stream().map(ImportGoodsDetailConverter::toModel).toList();
    }

    @Override
    public List<ImportGoodsDetailEntity> findByImportGood(Long importGoodId) {
        List<ImportGoodsDetailEntity> list = importGoodsDetailRepository.findByImportGood(importGoodId);
        return list;
    }
}
