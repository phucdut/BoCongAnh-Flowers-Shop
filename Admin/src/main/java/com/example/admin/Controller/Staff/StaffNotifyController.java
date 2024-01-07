package com.example.admin.Controller.Staff;

import com.example.admin.Domain.Notification;
import com.example.admin.Service.Impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("staff")
public class StaffNotifyController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notify")
    public String showStaffHome(Model model){
        return "Staff/StaffNotification";
    }

    @GetMapping("/all-notify")
    public ResponseEntity<?> findAll(){
        List<Notification> result = notificationService.findAll();
        for (Notification notification : result) {
            System.out.println(notification.getId());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
