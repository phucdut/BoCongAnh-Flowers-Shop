package com.example.admin.Repository;

import com.example.admin.Entity.OrderEntity;
import com.example.admin.Entity.OrderHistoryEntity;
import com.example.admin.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {
    List<OrderHistoryEntity> findOrderHistoryEntitiesByOrderDateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    @Query("SELECT o FROM OrderHistoryEntity o WHERE MONTH(o.orderDateTime) = :month AND YEAR(o.orderDateTime) = :year")
    List<OrderHistoryEntity> findOrdersByMonthAndYear(int month, int year);
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderHistoryEntity o WHERE o.orderDateTime BETWEEN :startTime AND :endTime")
    Long getTotalRevenueByTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    List<OrderHistoryEntity> findOrderHistoryEntitiesByUserIdAndOrderDateTimeBetween(Long id, LocalDateTime start, LocalDateTime end);

    @Query("SELECT o FROM OrderHistoryEntity o WHERE DATE(o.orderDateTime) = :date")
    List<OrderHistoryEntity> findByDate(@Param("date") LocalDate date);

    @Query("select o from OrderHistoryEntity o where o.orderDateTime > ?1 and o.orderDateTime <= ?2")
    public List<OrderHistoryEntity> findByDate(LocalDateTime from, LocalDateTime to);

    @Query("select o from OrderHistoryEntity o where o.orderDateTime > ?1 and o.orderDateTime <= ?2 and o.orderStatus = ?3")
    public List<OrderHistoryEntity> findByDateAndStatus(LocalDateTime from, LocalDateTime to, OrderStatus orderStatus);
}
