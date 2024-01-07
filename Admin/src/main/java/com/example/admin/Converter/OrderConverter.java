package com.example.admin.Converter;

import com.example.admin.Domain.CartItem;
import com.example.admin.Domain.Order;
import com.example.admin.Domain.OrderHistory;
import com.example.admin.Domain.Review;
import com.example.admin.Entity.OrderDetailEntity;
import com.example.admin.Entity.OrderEntity;
import com.example.admin.Entity.OrderHistoryEntity;
import com.example.admin.Entity.ReviewEntity;
import com.example.admin.enums.OrderStatus;
import com.example.admin.enums.TitleType;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderHistory toModelHistory(OrderHistoryEntity orderHistoryEntity) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(orderHistoryEntity.getId());
        orderHistory.setAmount(orderHistoryEntity.getAmount());
        orderHistory.setOrderDateTime(orderHistoryEntity.getOrderDateTime());
        orderHistory.setFullNameStaff(orderHistoryEntity.getFullNameStaff());
        orderHistory.setEmailCustomer(orderHistoryEntity.getEmailCustomer());
        orderHistory.setPhoneCustomer(orderHistoryEntity.getPhoneCustomer());
        orderHistory.setDiscount(orderHistoryEntity.getDiscount());
        orderHistory.setTotalPrice(orderHistoryEntity.getTotalPrice());
        orderHistory.setUserId(orderHistoryEntity.getUserId());
        orderHistory.setCustomerId(orderHistoryEntity.getCustomerId());
        orderHistory.setOrderStatus(orderHistoryEntity.getOrderStatus());
        orderHistory.setFullNameCustomer(orderHistoryEntity.getFullNameCustomer());
//        orderHistory.setOrderDetailHistories(orderHistoryEntity.getOrderDetailHistoryEntities().stream().map(OrderDetailConverter::toOrderDetailHistory).toList());

//        orderHistory.setUser(UserConverter.toModel(orderHistoryEntity.getUserEntity()));

        return orderHistory;
    }

    public static Order toModel(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setAmount(orderEntity.getAmount());
        order.setDiscount(orderEntity.getDiscount());
        order.setTotalPrice(orderEntity.getTotalPrice());
        order.setStatus(orderEntity.getStatus());
        order.setConfirmed(orderEntity.getConfirmed());
        order.setNote(orderEntity.getNote());
        order.setOrderDateTime(orderEntity.getOrderDateTime());
        order.setPaymentOnline(orderEntity.isPaymentOnline());
        order.setShipPrice(orderEntity.getShipPrice());
        order.setShipping(orderEntity.isShipping());
        order.setInformationRelated(orderEntity.getInformationRelated());
        order.setOrderStatus(orderEntity.getOrderStatus());

//        order.setAddress(AddressConverter.toModel(orderEntity.getAddressEntity()));
//        order.setUser(UserConverter.toModel(orderEntity.getUserEntity()));
//        order.setCustomer(CustomerConverter.toModel(orderEntity.getCustomerEntity()));
//        order.setVoucher(VoucherConverter.toModel(orderEntity.getVoucherEntity()));

        return order;
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
