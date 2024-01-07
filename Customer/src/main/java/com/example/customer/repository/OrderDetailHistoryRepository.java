package com.example.customer.repository;

import com.example.customer.entity.OrderDetailHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailHistoryRepository extends JpaRepository<OrderDetailHistoryEntity, Long> {
    @Query("SELECT od.productId, SUM(od.quantity) AS totalQuantity " +
            "FROM OrderDetailHistoryEntity od " +
            "GROUP BY od.productId " +
            "ORDER BY totalQuantity DESC ")
    List<Object[]> findTopSellingProducts();

    default List<Object[]> findTop10SellingProductsLimited() {
        return findTopSellingProducts().subList(0, 10);
    }
}
