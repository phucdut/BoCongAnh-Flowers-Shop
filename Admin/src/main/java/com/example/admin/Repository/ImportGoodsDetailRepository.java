package com.example.admin.Repository;

import com.example.admin.Entity.ImportGoodsDetailEntity;
import com.example.admin.Entity.ImportGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImportGoodsDetailRepository extends JpaRepository<ImportGoodsDetailEntity, Long> {
    List<ImportGoodsDetailEntity> findAllByImportGoodsEntity(ImportGoodsEntity importGoodsEntity);

    @Query("select i from ImportGoodsDetailEntity i where i.importGoodsEntity.id = ?1")
    public List<ImportGoodsDetailEntity> findByImportGood(Long importGoodId);
}
