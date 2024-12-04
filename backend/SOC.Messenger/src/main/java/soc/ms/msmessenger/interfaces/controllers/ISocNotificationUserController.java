package soc.ms.msmessenger.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import soc.ms.msmessenger.interfaces.Constants;
import soc.ms.msmessenger.payload.requests.*;

import java.util.List;

@RequestMapping(Constants.NotificationUserControllerHeader)
public interface ISocNotificationUserController {

    @GetMapping(Constants.NotificationUserGetAllUsers)
    ResponseEntity<List<String>> getAllUsers();

    @GetMapping(Constants.NotificationUserGetAllForUser)
    ResponseEntity<List<String>> getAllForUser(@RequestBody NotificationUserRequest userEmail);
}
