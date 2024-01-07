package com.example.customer.controller.RESTfulAPI;

import com.example.customer.domain.Order;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.responseBody.ResponsePayment;
import com.example.customer.service.CustomerService;
import com.example.customer.service.Impl.FCMService;
import com.example.customer.service.NotificationService;
import com.example.customer.service.OrderHistoryService;
import com.example.customer.service.OrderService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/payment")
public class PaymentAPIController {

    @Autowired
    private CustomerValidate customerValidate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FCMService fcmService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("app")
    public ResponseEntity<ResponsePayment> paymentApp(@RequestBody Order order, @RequestHeader HttpHeaders headers) {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long orderId = orderService.createOrder(order, name);
        ResponsePayment payment = orderService.createResponsePayment(orderId);
        if (payment.getOrder().isPaymentOnline()) {
            payment.setUrlQR(orderService.createQrPayment(orderId));
        }
        Long id = orderHistoryService.addOrder(name, orderId);
        Long notifyId = notificationService.addNotifyOrder(name, id);
        notificationService.sendNotifyOrder(headers, notifyId, id);
        return ResponseEntity.ok(payment);
    }

    @Transactional
    @GetMapping("success-app")
    public ResponseEntity<BodyResponse> success(@RequestParam("id") Long userId) {
        String name = customerService.getCustomerByUserId(userId);
        Long notifyId = notificationService.addNotifyPayment(name, true);
        notificationService.sendNotifyPaymentApp(notifyId, true);
        BodyResponse bodyResponse = new BodyResponse();
        bodyResponse.setSuccess(true);
        bodyResponse.setMessage("success");
        return ResponseEntity.ok(bodyResponse);
    }

    @Transactional
    @GetMapping("failed-app")
    public ResponseEntity<BodyResponse> failed(@RequestParam("id") Long userId) {
        String name = customerService.getCustomerByUserId(userId);
        Long notifyId = notificationService.addNotifyPayment(name, false);
        notificationService.sendNotifyPaymentApp(notifyId, false);
        BodyResponse bodyResponse = new BodyResponse();
        bodyResponse.setSuccess(false);
        bodyResponse.setMessage("error");
        return ResponseEntity.ok(bodyResponse);
    }

    @PostMapping("web")
    public ResponseEntity<String> paymentWeb(@RequestBody Order order) {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long orderId = orderService.createOrder(order, name);
        ResponsePayment payment = orderService.createResponsePayment(orderId);
        String url = "http://boconganhflowers.click/payment/success-web";
        if (payment.getOrder().isPaymentOnline()) {
            url = orderService.createUrlPayment(orderId);
        }
        System.out.println("vào đc ");
        orderHistoryService.addOrder(name, orderId);
        return ResponseEntity.ok(url);
    }


}
