package com.example.customer.converter;

import com.example.customer.domain.CartItem;
import com.example.customer.domain.Order;
import com.example.customer.domain.OrderDetail;
import com.example.customer.domain.OrderHistory;
import com.example.customer.entity.CartItemEntity;
import com.example.customer.entity.OrderDetailEntity;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;
import com.example.customer.enums.OrderStatus;
import com.example.customer.enums.TitleType;

public class OrderConverter {
    public static Order toModel(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setOrderDateTime(orderEntity.getOrderDateTime());
        order.setTotalPrice(orderEntity.getTotalPrice());
        order.setDiscount(orderEntity.getDiscount());
        order.setAmount(orderEntity.getAmount());
        order.setNote(orderEntity.getNote());
        order.setShipPrice(orderEntity.getShipPrice());
        order.setPaymentOnline(orderEntity.isPaymentOnline());
        order.setAddressId(orderEntity.getAddressEntity().getId());
        if (orderEntity.getVoucherEntity() != null) {
            order.setVoucherId(orderEntity.getVoucherEntity().getId());
        }

        order.setOrderStatus(orderEntity.getOrderStatus());
        order.setStatus(orderEntity.getStatus());
        order.setConfirmed(orderEntity.getConfirmed());
        return order;
    }

    public static OrderHistory toModelOrderHistory(OrderHistoryEntity entity) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(entity.getId());
        orderHistory.setOrderDateTime(entity.getOrderDateTime());
        orderHistory.setTotalPrice(entity.getTotalPrice());
        orderHistory.setDiscount(entity.getDiscount());
        orderHistory.setAmount(entity.getAmount());
        orderHistory.setCustomerId(entity.getCustomerId());
        orderHistory.setFullNameCustomer(entity.getFullNameCustomer());
        orderHistory.setPhoneCustomer(entity.getPhoneCustomer());
        orderHistory.setEmailCustomer(entity.getEmailCustomer());
        orderHistory.setOrderStatus(entity.getOrderStatus());
        orderHistory.setNameCustomerReceive(entity.getNameCustomerReceive());
        orderHistory.setPhoneCustomerReceive(entity.getPhoneCustomerReceive());
        orderHistory.setAddress(entity.getAddress());
        orderHistory.setPaymentOnline(entity.isPaymentOnline());
        orderHistory.setShipPrice(entity.getShipPrice());
        orderHistory.setReviewed(entity.isReviewed());
        orderHistory.setOrderDetailHistories(entity.getOrderDetailHistoryEntities().stream().map(OrderDetailConverter::toOrderDetailHistory).toList());
        return orderHistory;
    }

    public static CartItem orderDetailToCartItem(OrderDetailEntity entity) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(ProductConverter.toModel(entity.getProductEntity()));
        cartItem.setQuantity(entity.getQuantity());
        return cartItem;
    }

    public static TitleType toTitleType(OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.WAITING) {
            return TitleType.WAITING;
        }
        if (orderStatus == OrderStatus.CONFIRMED) {
            return TitleType.CONFIRMED;
        }
        if (orderStatus == OrderStatus.SENT) {
            return TitleType.SENT;
        }
        if (orderStatus == OrderStatus.RECEIVED) {
            return TitleType.RECEIVED;
        }
        if (orderStatus == OrderStatus.CANCELLED) {
            return TitleType.CANCELLED;
        }
        return TitleType.REJECT;
    }

    public static String orderStatusToString(OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.WAITING) {
            return "đang chờ xác nhận";
        }
        if (orderStatus == OrderStatus.CONFIRMED) {
            return "đã được xác nhận";
        }
        if (orderStatus == OrderStatus.SENT) {
            return "đang được gửi đi";
        }
        if (orderStatus == OrderStatus.RECEIVED) {
            return "đã được giao thành công";
        }
        if (orderStatus == OrderStatus.CANCELLED) {
            return "đã bị hủy";
        }
        return "không được xác nhận";
    }

    public static String orderStatusToTitle(OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.WAITING) {
            return "Đặt hàng thành công";
        }
        if (orderStatus == OrderStatus.CONFIRMED) {
            return "Đã được xác nhận";
        }
        if (orderStatus == OrderStatus.SENT) {
            return "Vận chuyển đơn hàng";
        }
        if (orderStatus == OrderStatus.RECEIVED) {
            return "Đã giao thành công";
        }
        if (orderStatus == OrderStatus.CANCELLED) {
            return "Đã bị hủy";
        }
        return "Không được xác nhận";
    }
}
