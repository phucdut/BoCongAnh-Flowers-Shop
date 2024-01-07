package com.example.admin.Controller;

import com.example.admin.Domain.PushNotificationRequest;
import com.example.admin.Entity.UserEntity;
import com.example.admin.Repository.UserRepository;
import com.example.admin.Service.PushNotificationService;
import com.example.admin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin
public class NotificationApi {

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send")
    public void send(@RequestBody PushNotificationRequest request){
        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("success");
    }

    @PostMapping("/send-by-userId")
    public void sendByUser(@RequestParam("id") Long userId, @RequestBody PushNotificationRequest request){
        UserEntity userEntity = userRepository.findById(userId).get();
        request.setToken(userEntity.getTokenDevice());
        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("success");
    }

    @PostMapping("/update-tokendevice")
    public void updateTokenDevice(@RequestParam("token") String token){
        Optional<UserEntity> user = userService.getUserWithAuthority();
        if(user.isPresent()){
            user.get().setTokenDevice(token);
            userRepository.save(user.get());
        }
    }


}

