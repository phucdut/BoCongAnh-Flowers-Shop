package com.example.customer.controller.RESTfulAPI;

import com.example.customer.domain.Notification;
import com.example.customer.domain.NotificationMessaging;
import com.example.customer.entity.NotificationEntity;
import com.example.customer.service.CustomerService;
import com.example.customer.service.Impl.FCMService;
import com.example.customer.service.NotificationService;
import com.example.customer.validator.CustomerValidate;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notification")
public class NotificationAPIController {

    @Autowired
    private CustomerValidate customerValidate;

    @Autowired
    private FCMService firebaseMessaging;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<Notification>> getAllNotification() {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(notificationService.getAllNotification(name));
    }

    @PostMapping()
    public ResponseEntity<String> sendNotificationByToken(@RequestBody NotificationMessaging notificationMessaging) {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(firebaseMessaging.sendNotificationByToken(notificationMessaging, name));
    }

    @PostMapping("update-token")
    public void updateTokenCustomer(@RequestParam("token") String token){
        String name = customerValidate.validateCustomer();
        if (name == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println("đã vòa");
        customerService.saveToken(name, token);
    }
}
