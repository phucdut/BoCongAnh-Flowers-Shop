package com.example.admin.Repository;

import com.example.admin.Entity.OrderDetailHistoryEntity;
import com.example.admin.Entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailHistoryRepository extends JpaRepository<OrderDetailHistoryEntity, Long> {
    @Query("SELECT od.productId, SUM(od.quantity) AS totalQuantity " +
            "FROM OrderDetailHistoryEntity od " +
            "GROUP BY od.productId " +
            "ORDER BY totalQuantity DESC ")
    List<Object[]> findTop4SellingProducts();

    default List<Object[]> findTop4SellingProductsLimited() {
        return findTop4SellingProducts().subList(0, 4);
    }
    List<OrderDetailHistoryEntity> findAllByOrderHistoryEntity(OrderHistoryEntity orderHistoryEntity);

    @Query("select o from OrderDetailHistoryEntity o where o.orderHistoryEntity.id = ?1")
    public List<OrderDetailHistoryEntity> findByOrder(Long orderId);
}
