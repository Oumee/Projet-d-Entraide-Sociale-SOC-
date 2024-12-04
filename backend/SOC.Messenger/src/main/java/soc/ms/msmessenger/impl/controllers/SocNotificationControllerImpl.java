package soc.ms.msmessenger.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import soc.ms.msmessenger.interfaces.controllers.ISocNotificationController;
import soc.ms.msmessenger.interfaces.services.ISocNotificationService;
import soc.ms.msmessenger.payload.requests.NotificationCommunityRequest;
import soc.ms.msmessenger.payload.requests.NotificationGetInfoRequest;
import soc.ms.msmessenger.payload.requests.NotificationSendRequest;
import soc.ms.msmessenger.payload.requests.NotificationSetInfoRequest;
import soc.ms.msmessenger.payload.responses.NotificationInfoResponse;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SocNotificationControllerImpl implements ISocNotificationController {

    @Autowired
    private ISocNotificationService socNotificationService;

    @Override
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(socNotificationService.getAll().toList());
    }

    @Override
    public ResponseEntity<List<String>> getAllForCommunity(NotificationCommunityRequest request) {
        return ResponseEntity.ok(socNotificationService.getAllForCommunity(request.communityName()).toList());
    }

    @Override
    public ResponseEntity<NotificationInfoResponse> getInfo(NotificationGetInfoRequest request) {
        return ResponseEntity.ok(socNotificationService.getInfo(request.notificiationId()));
    }

    @Override
    public ResponseEntity<Void> setInfo(NotificationSetInfoRequest request) {
        socNotificationService.setInfo(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> send(NotificationSendRequest userEmail) {
        return ResponseEntity.ok(socNotificationService.send(userEmail));
    }
}
