package com.example.admin.Service;
import com.example.admin.Domain.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    private FCMService fcmService;

    @Autowired
    private UserService userService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }


    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
//            Optional<UserEntity> user = userService.getUserWithAuthority();
//            if(user.isPresent()){
//                if(user.get().getTokenDevice().equals(request.getToken())){
//                    fcmService.sendMessageToToken(request);
//                }
//            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}