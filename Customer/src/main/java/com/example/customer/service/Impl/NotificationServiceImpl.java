package com.example.customer.service.Impl;

import com.example.customer.converter.NotificationConverter;
import com.example.customer.converter.OrderConverter;
import com.example.customer.domain.Notification;
import com.example.customer.domain.NotificationMessaging;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.entity.NotificationEntity;
import com.example.customer.entity.OrderHistoryEntity;
import com.example.customer.enums.TitleType;
import com.example.customer.remote.NotificationRemote;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.NotificationRepository;
import com.example.customer.repository.OrderHistoryRepository;
import com.example.customer.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private NotificationRemote notificationRemote;

    @Override
    public List<Notification> getAllNotification(String name) {
        return notificationRepository.findAllByCustomerEntity(customerRepository.findByUsername(name).orElseThrow())
                .stream()
                .map(NotificationConverter::toModel)
                .toList();
    }

    @Override
    public Long addNotifyOrder(String name, Long id) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        OrderHistoryEntity orderEntity = orderHistoryRepository.findById(id).orElseThrow();
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setCustomerEntity(customerEntity);
        notificationEntity.setDatetime(LocalDateTime.now());
        notificationEntity.setType(OrderConverter.toTitleType(orderEntity.getOrderStatus()));
        notificationEntity.setTitle("Đơn hàng của bạn " + OrderConverter.orderStatusToString(orderEntity.getOrderStatus()));
        notificationEntity.setContent("Đơn hàng số " + orderEntity.getId() + " " + notificationEntity.getTitle());
        notificationEntity.setHaveRead(false);
        return notificationRepository.save(notificationEntity).getId();
    }

    @Override
    public void sendNotifyOrder(HttpHeaders headers, Long notifyId, Long orderId) {
        NotificationEntity notificationEntity = notificationRepository.findById(notifyId).orElseThrow();
        OrderHistoryEntity orderEntity = orderHistoryRepository.findById(orderId).orElseThrow();
        NotificationMessaging notificationMessaging = new NotificationMessaging();
        notificationMessaging.setTitle(OrderConverter.orderStatusToTitle(orderEntity.getOrderStatus()));
        notificationMessaging.setBody("Đơn hàng số " + orderEntity.getId() +  " của bạn " + OrderConverter.orderStatusToTitle(orderEntity.getOrderStatus()));
        notificationMessaging.setImage("lỗi");
        notificationMessaging.setData(NotificationConverter.toModel(notificationEntity));
        notificationRemote.sendNotification(notificationMessaging, headers);

    }

    @Override
    public Long addNotifyPayment(String name, boolean success) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setCustomerEntity(customerEntity);
        notificationEntity.setDatetime(LocalDateTime.now());
        notificationEntity.setType(TitleType.PAYMENT);
        if (success) {
            notificationEntity.setTitle("Thanh toán thành công");
            notificationEntity.setContent("Đơn hàng của bạn đã được thanh toán thành công, vui lòng chờ shop xác nhận");
        } else {
            notificationEntity.setTitle("Thanh toán thất bại");
            notificationEntity.setContent("Đơn hàng của bạn không được thanh toán, vui lòng chọn lại phương thức thanh toán và đặt hàng");
        }
        notificationEntity.setHaveRead(false);
        return notificationRepository.save(notificationEntity).getId();
    }

    @Override
    public void sendNotifyPayment(HttpHeaders headers, Long notifyId, boolean success) {
        NotificationEntity notificationEntity = notificationRepository.findById(notifyId).orElseThrow();
        NotificationMessaging notificationMessaging = new NotificationMessaging();
        if (success) {
            notificationMessaging.setTitle("Thanh toán thành công");
            notificationMessaging.setBody("Đơn hàng của bạn đã được thanh toán thành công, vui lòng chờ shop xác nhận");
            notificationMessaging.setImage("lỗi");
        } else {
            notificationMessaging.setTitle("Thanh toán thất bại");
            notificationMessaging.setBody("Đơn hàng của bạn không được thanh toán, vui lòng chọn lại phương thức thanh toán và đặt hàng");
            notificationMessaging.setImage("lỗi");
        }
        notificationMessaging.setData(NotificationConverter.toModel(notificationEntity));
        notificationRemote.sendNotification(notificationMessaging, headers);
    }

    @Override
    public void sendNotifyPaymentApp(Long notifyId, boolean success) {
        NotificationEntity notificationEntity = notificationRepository.findById(notifyId).orElseThrow();
        NotificationMessaging notificationMessaging = new NotificationMessaging();
        if (success) {
            notificationMessaging.setTitle("Thanh toán thành công");
            notificationMessaging.setBody("Đơn hàng của bạn đã được thanh toán thành công, vui lòng chờ shop xác nhận");
            notificationMessaging.setImage("lỗi");
        } else {
            notificationMessaging.setTitle("Thanh toán thất bại");
            notificationMessaging.setBody("Đơn hàng của bạn không được thanh toán, vui lòng chọn lại phương thức thanh toán và đặt hàng");
            notificationMessaging.setImage("lỗi");
        }
        notificationMessaging.setData(NotificationConverter.toModel(notificationEntity));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", notificationEntity.getCustomerEntity().getCookie());
        notificationRemote.sendNotification(notificationMessaging, headers);
    }
}
