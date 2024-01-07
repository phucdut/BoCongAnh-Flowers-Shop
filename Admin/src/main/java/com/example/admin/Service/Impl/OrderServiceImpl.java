package com.example.admin.Service.Impl;


import com.example.admin.Converter.OrderConverter;
import com.example.admin.Domain.AmountData;
import com.example.admin.Domain.Order;
import com.example.admin.Domain.OrderHistory;
import com.example.admin.Domain.OrderNote;
import com.example.admin.Entity.OrderEntity;
import com.example.admin.Entity.OrderHistoryEntity;
import com.example.admin.Repository.OrderHistoryRepository;
import com.example.admin.Repository.OrderRepository;
import com.example.admin.Service.OrderService;
import com.example.admin.Service.RevenueService;
import com.example.admin.config.MailService;
import com.example.admin.enums.OrderStatus;
import com.example.admin.exception.MessageException;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private RevenueService revenueService;
    @Override
    public List<OrderHistory> getAllOrderHistory() {
        return orderHistoryRepository.findAll().stream().map(OrderConverter::toModelHistory).toList();
    }
    @Override
    public List<OrderHistory> getOrderByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return orderHistoryRepository.findOrderHistoryEntitiesByOrderDateTimeBetween(startTime, endTime).stream().map(OrderConverter::toModelHistory).toList();
    }
    @Override
    public double getAllTotalByTime(LocalDateTime start, LocalDateTime end) {
        List<OrderHistory> orderHistories = orderHistoryRepository.findOrderHistoryEntitiesByOrderDateTimeBetween(start, end).stream().map(OrderConverter::toModelHistory).toList();
        Long totalAmount = 0L;
        for (OrderHistory orderHistory : orderHistories){
            totalAmount += orderHistory.getAmount();
        }
        return totalAmount;
    }
    @Override
    public List<OrderEntity> findAllList(Date from, Date to) {
        List<OrderEntity> list = null;
        if(from == null || to == null){
            list = orderRepository.findAll();
        }
        else{
            LocalDateTime datef = LocalDateTime.of(from.toLocalDate(), LocalTime.now());
            LocalDateTime datet = LocalDateTime.of(to.toLocalDate(), LocalTime.now());
            list = orderRepository.findByDate(datef, datet);
        }
        return list;
    }

    @Override
    public List<OrderHistory> findAllListByStatus(Date from, Date to, String trangthai) {
        List<OrderHistoryEntity> list = null;
        if(from == null || to == null){
            from = Date.valueOf("2000-01-01");
            to = Date.valueOf("2100-01-01");
        }
        LocalDateTime datef = LocalDateTime.of(from.toLocalDate(), LocalTime.now());
        LocalDateTime datet = LocalDateTime.of(to.toLocalDate(), LocalTime.now());
        if(trangthai == null){
            list = orderHistoryRepository.findByDate(datef, datet);
        }
        else{
            OrderStatus orderStatus = null;
            for (OrderStatus o : OrderStatus.values()) {
                if(o.name().equals(trangthai)){
                    orderStatus = o;
                }
            }
            list = orderHistoryRepository.findByDateAndStatus(datef, datet, orderStatus);
        }
        List<OrderHistory> result = new ArrayList<>();
        for(OrderHistoryEntity o : list){
            result.add(OrderConverter.toModelHistory(o));
        }
        return result;
    }

    @Override
    public OrderHistoryEntity addNote(OrderNote orderNote) {
        Optional<OrderHistoryEntity> order = orderHistoryRepository.findById(orderNote.getOrderId());
        if (order.isEmpty()){
            throw new MessageException("order not found");
        }
        order.get().setNote(orderNote.getNote());
        order.get().setInformationRelated(orderNote.getInforRelated());
        OrderHistoryEntity result = orderHistoryRepository.save(order.get());
        return result;
    }

    @Override
    public OrderHistory findById(Long id) {
        Optional<OrderHistoryEntity> order = orderHistoryRepository.findById(id);
        if (order.isEmpty()){
            throw new MessageException("order not found");
        }
        return OrderConverter.toModelHistory(order.get());
    }

    @Override
    public void updateStatusOrder(OrderStatus orderStatus, Long orderId) {
        String sttname = orderStatus.name();
        Optional<OrderHistoryEntity> order = orderHistoryRepository.findById(orderId);
        if (order.isEmpty()){
            throw new MessageException("order not found");
        }
        order.get().setOrderStatus(orderStatus);
        orderHistoryRepository.save(order.get());
        String mail = order.get().getEmailCustomer();
        try {
            mailService.sendEmail(mail,"Update status order flower web","Your order"+order.get().getId()+" has been updated to "+order.get().getOrderStatus().name()+" status",
                    false, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<AmountData> getAmountByMonth() {
        List<AmountData> amountDataList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            AmountData amountData = new AmountData();
            List<OrderHistoryEntity> orderHistoryEntities = orderHistoryRepository.findOrdersByMonthAndYear(i, LocalDateTime.now().getYear());
            Long amountMonth = 0L;
            for (OrderHistoryEntity orderHistoryEntity : orderHistoryEntities) {
                amountMonth += orderHistoryEntity.getAmount();
            }
            amountData.setAmount(amountMonth);
            amountData.setMonth(String.valueOf(i));
            amountDataList.add(amountData);
        }
        return amountDataList;
    }
    @Override
    public List<OrderHistory> getOrderByMonth(int month, int year) {
        if (month == 0) {
            month = 12;
            year -= 1;
        }
        System.out.println(month+ " " + year);
        return orderHistoryRepository.findOrdersByMonthAndYear(month, year).stream().map(OrderConverter::toModelHistory).toList();
    }
    @Override
    public double getPercentCompare(double totalThisMonth, double totalLastMonth) {
        double percentage = totalThisMonth / totalLastMonth * 100;
        percentage -= 100;
        String formattedPercentage = String.format("%.2f", percentage); // Định dạng số với tối đa 2 chữ số sau dấu phẩy
        return Double.parseDouble(formattedPercentage);
    }
    @Override
    public Long getTotalAmountByOrder(List<OrderHistory> orderHistories) {
        Long totalAmount = 0L;
        for (OrderHistory orderHistory : orderHistories){
            totalAmount += orderHistory.getAmount();
        }
        return totalAmount;
    }
    @Transactional
    public void createOrderHistory(OrderHistoryEntity orderHistory) {
        // Lưu OrderHistoryEntity vào database
        orderHistoryRepository.save(orderHistory);

        // Cập nhật hoặc thêm mới bản ghi trong bảng RevenueEntity
        revenueService.updateTotalRevenue(orderHistory.getOrderDateTime().toLocalDate());
    }

    public Long getTotalRevenueByTime(LocalDateTime startTime, LocalDateTime endTime) {
        try {
            Long totalRevenue = orderHistoryRepository.getTotalRevenueByTime(startTime, endTime);
            return totalRevenue != null ? totalRevenue.longValue() : 0L;
        } catch (Exception ex) {
            // Handle the exception or log it
            return 0L;
        }
    }

}
