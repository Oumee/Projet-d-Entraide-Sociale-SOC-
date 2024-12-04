package soc.ms.msmessenger.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import soc.ms.msmessenger.interfaces.controllers.ISocNotificationUserController;
import soc.ms.msmessenger.interfaces.services.ISocNotificationUserService;
import soc.ms.msmessenger.payload.requests.NotificationUserRequest;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SocNotificationUserControllerImpl implements ISocNotificationUserController {

    @Autowired
    private ISocNotificationUserService socNotificationUserService;

    @Override
    public ResponseEntity<List<String>> getAllUsers() {
        return ResponseEntity.ok(socNotificationUserService.getAllUsers().toList());
    }

    @Override
    public ResponseEntity<List<String>> getAllForUser(NotificationUserRequest userEmail) {
        return ResponseEntity.ok(socNotificationUserService.getAllForUser(userEmail).toList());
    }
}
