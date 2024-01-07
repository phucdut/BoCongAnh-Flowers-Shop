package com.example.admin.Repository;

import com.example.admin.Entity.ImportGoodsEntity;
import com.example.admin.Entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ImportGoodsRepository extends JpaRepository<ImportGoodsEntity, Long> {
//    List<String> findItemNamesByTerm(String term);

    List<ImportGoodsEntity> findImportGoodsEntitiesByTimeImportBetween(LocalDateTime startTime, LocalDateTime endTime);
    @Query("SELECT SUM(i.totalPrice) FROM ImportGoodsEntity i WHERE i.timeImport BETWEEN :startTime AND :endTime")
    Long getTotalImportAmountByTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("select i from ImportGoodsEntity i where i.timeImport >= ?1 and i.timeImport <= ?2")
    public List<ImportGoodsEntity> findByDate(LocalDateTime from, LocalDateTime to);
}
