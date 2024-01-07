package com.example.admin.Converter;

import com.example.admin.Domain.OrderDetail;
import com.example.admin.Domain.OrderDetailHistory;
import com.example.admin.Entity.OrderDetailEntity;
import com.example.admin.Entity.OrderDetailHistoryEntity;

public class OrderDetailConverter {
    public static OrderDetail toModel (OrderDetailEntity orderDetailEntity){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailEntity.getId());
        orderDetail.setOrder(OrderConverter.toModel(orderDetailEntity.getOrderEntity()));
        orderDetail.setProduct(ProductConverter.toModel(orderDetailEntity.getProductEntity()));
        orderDetail.setQuantity(orderDetailEntity.getQuantity());
        return orderDetail;
    }

    public static OrderDetailHistory toOrderDetailHistory(OrderDetailHistoryEntity entity) {
        OrderDetailHistory orderDetailHistory = new OrderDetailHistory();
        orderDetailHistory.setId(entity.getId());
        orderDetailHistory.setOrderHistory(OrderConverter.toModelHistory(entity.getOrderHistoryEntity()));
        orderDetailHistory.setProductId(entity.getProductId());
        orderDetailHistory.setNameProduct(entity.getNameProduct());
        orderDetailHistory.setPriceProduct(entity.getPriceProduct());
        orderDetailHistory.setQuantity(entity.getQuantity());
        orderDetailHistory.setImage(entity.getImage());
        return orderDetailHistory;
    }
}
