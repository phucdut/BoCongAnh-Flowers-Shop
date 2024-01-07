package com.example.customer.service.Impl;

import com.example.customer.payment.BodyRequest;
import com.example.customer.payment.DataRequest;
import com.example.customer.converter.*;
import com.example.customer.domain.Order;
import com.example.customer.entity.*;
import com.example.customer.enums.OrderStatus;
import com.example.customer.enums.VoucherType;
import com.example.customer.remote.PaymentRemote;
import com.example.customer.repository.*;
import com.example.customer.responseBody.ResponsePayment;
import com.example.customer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRemote paymentRemote;

    @Override
    public Long createOrder(Order order, String name) {
        OrderEntity orderEntity = saveOrder(order, name);
        saveOrderDetail(orderEntity);
        return orderEntity.getId();
    }

    @Override
    public String createQrPayment(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        BodyRequest bodyRequest = setOrder(orderEntity);
        return paymentRemote.getQrFromOtherClient(bodyRequest).getCheckoutUrl();
    }

    @Override
    public Order checkoutOrder(Order orderRequest, String name) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        orderRequest.setCartItems(CartConverter.toModel(customerEntity.getCartEntity()).getCartItems());
        Long totalPrice = getTotalPrice(customerEntity);
        orderRequest.setTotalPrice(totalPrice);

        if (!orderRequest.isPaymentOnline()) {
            orderRequest.setVouchers(voucherRepository.findAllByExpiredFalseAndConditionsPaymentOnlineFalseAndConditionPriceLessThanEqual(totalPrice).stream().map(VoucherConverter::toModel).toList());
        } else {
            orderRequest.setVouchers(voucherRepository.findAllByExpiredFalseAndConditionPriceLessThanEqual(totalPrice).stream().map(VoucherConverter::toModel).toList());
        }
        Long discount = 0L;
        orderRequest.setShipPrice(30000L);
        if (orderRequest.getVoucherId() != null) {
            if (voucherRepository.findById(orderRequest.getVoucherId()).orElseThrow().getType() == VoucherType.FREESHIP) {
                discount = 30000L;
            } else {
                discount = getMoneyByDiscount(totalPrice, voucherRepository.findById(orderRequest.getVoucherId()).orElseThrow());
            }
        }
        orderRequest.setDiscount(discount);
        orderRequest.setAmount(totalPrice - discount + orderRequest.getShipPrice());
        return orderRequest;
    }

    private double ceil(double d) {
        return Math.ceil(d * 100) / 100;
    }

    @Override
    public ResponsePayment createResponsePayment(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        ResponsePayment payment = new ResponsePayment();
        Order order = OrderConverter.toModel(orderEntity);
        order.setCartItems(orderDetailRepository.findAllByOrderEntity(orderEntity).stream().map(OrderConverter::orderDetailToCartItem).toList());
        payment.setOrder(order);
        if (orderEntity.getVoucherEntity() != null) {
            payment.setVoucher(VoucherConverter.toModel(orderEntity.getVoucherEntity()));
        }

        payment.setAddress(AddressConverter.toModel(orderEntity.getAddressEntity()));
        if (orderEntity.isPaymentOnline()) {
            payment.setMethodPayment("online");
        } else {
            payment.setMethodPayment("tiền mặt");
        }
        return payment;
    }

    @Override
    public String createUrlPayment(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        BodyRequest bodyRequest = setNewOrder(orderEntity);
        return paymentRemote.getQrFromOtherClient(bodyRequest).getCheckoutUrl();
    }

    private BodyRequest setNewOrder(OrderEntity orderEntity) {
        BodyRequest order = new BodyRequest();
        order.setOrderCode(10000 + orderEntity.getId());
        order.setAmount(orderEntity.getAmount());
        order.setDescription("");
        order.setCustomer_id(orderEntity.getCustomerEntity().getId());
        order.setBuyerName(orderEntity.getCustomerEntity().getFullName());
        order.setBuyerPhone(orderEntity.getCustomerEntity().getPhone());
        order.setReturnUrl("http://boconganhflowers.click/payment/success-web");
        order.setCancelUrl("http://boconganhflowers.click/payment/failed-web");
        order.setExpiredAt(getUnixTimestamp());
        order.setItems(setItems(orderEntity));
        Map<String, String> params = Map.of(
                "amount", String.valueOf(order.getAmount()),
                "cancelUrl", order.getCancelUrl(),
                "description", order.getDescription(),
                "orderCode", String.valueOf(order.getOrderCode()),
                "returnUrl", order.getReturnUrl()
        );

        String ChecksumKey = "22ee21ab306b80fac1782bb426e6140498bc4b5b9f483f30d4883f320731e29e";
        String signature = SignatureGenerator.generateSignature(params, ChecksumKey);
        order.setSignature(signature);
        return order;
    }

    private OrderEntity saveOrder(Order order, String name) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDateTime(LocalDateTime.now());
        Long totalPrice = getTotalPrice(customerEntity);
        orderEntity.setTotalPrice(totalPrice);
        Long discount = 0L;
        orderEntity.setShipPrice(30000L);
        if (order.getVoucherId() != null) {
            VoucherEntity voucherEntity = voucherRepository.findById(order.getVoucherId()).orElseThrow();
            orderEntity.setVoucherEntity(voucherEntity);
            if (voucherRepository.findById(order.getVoucherId()).orElseThrow().getType() == VoucherType.FREESHIP) {
                discount = 30000L;
            } else {
                discount = getMoneyByDiscount(totalPrice, voucherRepository.findById(order.getVoucherId()).orElseThrow());
            }
        }
        orderEntity.setDiscount(discount);
        orderEntity.setAmount(totalPrice - discount + orderEntity.getShipPrice());
        orderEntity.setConfirmed(false);
        orderEntity.setStatus(false);
        if (order.getNote() == null) {
            orderEntity.setNote("khách lẻ");
        } else {
            orderEntity.setNote(order.getNote());
        }
        orderEntity.setOrderStatus(OrderStatus.WAITING);
        orderEntity.setAddressEntity(addressRepository.findById(order.getAddressId()).orElseThrow());
        orderEntity.setPaymentOnline(order.isPaymentOnline());
        orderEntity.setCustomerEntity(customerEntity);
        return orderRepository.save(orderEntity);
    }

    private void saveOrderDetail(OrderEntity orderEntity) {
        for (CartItemEntity entity: orderEntity.getCustomerEntity().getCartEntity().getCartItemEntities() ) {
            if (entity.getQuantity() != 0) {
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setOrderEntity(orderEntity);
                orderDetailEntity.setProductEntity(entity.getProductEntity());
                orderDetailEntity.setQuantity(entity.getQuantity());
                orderDetailRepository.save(orderDetailEntity);
            }
        }
    }

    private BodyRequest setOrder(OrderEntity orderEntity) {
        BodyRequest order = new BodyRequest();
        order.setOrderCode(10000 + orderEntity.getId());
        order.setAmount(orderEntity.getAmount());
        order.setDescription("");
        order.setCustomer_id(orderEntity.getCustomerEntity().getId());
        order.setBuyerName(orderEntity.getCustomerEntity().getFullName());
        order.setBuyerPhone(orderEntity.getCustomerEntity().getPhone());
        order.setReturnUrl("http://boconganhflowers.click/api/payment/success-app?id=" + orderEntity.getCustomerEntity().getId());
        order.setCancelUrl("http://boconganhflowers.click/api/payment/failed-app?id=" + orderEntity.getCustomerEntity().getId());
        order.setExpiredAt(getUnixTimestamp());
        order.setItems(setItems(orderEntity));
        Map<String, String> params = Map.of(
                "amount", String.valueOf(order.getAmount()),
                "cancelUrl", order.getCancelUrl(),
                "description", order.getDescription(),
                "orderCode", String.valueOf(order.getOrderCode()),
                "returnUrl", order.getReturnUrl()
        );

        String ChecksumKey = "22ee21ab306b80fac1782bb426e6140498bc4b5b9f483f30d4883f320731e29e";
        String signature = SignatureGenerator.generateSignature(params, ChecksumKey);
        order.setSignature(signature);
        return order;
    }

    private List<DataRequest> setItems(OrderEntity orderEntity) {
        List<DataRequest> dataRequests = new ArrayList<>();
        for (OrderDetailEntity entity: orderDetailRepository.findAllByOrderEntity(orderEntity)) {
            DataRequest data = new DataRequest();
            data.setName(entity.getProductEntity().getName());
            data.setPrice(entity.getProductEntity().getPrice());
            data.setQuantity(entity.getQuantity());
            dataRequests.add(data);
        }
        return dataRequests;
    }

    private long getUnixTimestamp() {
        // Lấy thời điểm hiện tại
        Instant now = Instant.now();
        // Thêm 1 giờ (3600 giây) vào thời điểm hiện tại
        Instant expiredAt = now.plusSeconds(900);
        // Lấy Unix Timestamp
        return expiredAt.getEpochSecond();
    }

    private Long getTotalPrice(CustomerEntity customerEntity) {
        Long totalPrice = 0L;
        for (CartItemEntity entity: customerEntity.getCartEntity().getCartItemEntities()) {
            if (entity.getProductEntity().getFlashSaleEntity() != null && !entity.getProductEntity().getFlashSaleEntity().isExpired()) {
                totalPrice += entity.getProductEntity().getFlashSaleEntity().getPriceSale() * entity.getQuantity();
            } else {
                totalPrice += entity.getProductEntity().getPrice() * entity.getQuantity();
            }
        }
        return totalPrice;
    }

    private Long getMoneyByDiscount(Long totalPrice, VoucherEntity voucherEntity) {
        return (voucherEntity.getPercentage() * totalPrice) / 100;
    }

}
