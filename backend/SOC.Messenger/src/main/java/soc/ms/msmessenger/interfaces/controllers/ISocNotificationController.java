package soc.ms.msmessenger.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import soc.ms.msmessenger.interfaces.Constants;
import soc.ms.msmessenger.payload.requests.*;
import soc.ms.msmessenger.payload.responses.*;

import java.util.List;

@RequestMapping(Constants.NotificationControllerHeader)
public interface ISocNotificationController {

    @GetMapping(Constants.NotificationGetAll)
    ResponseEntity<List<String>> getAll();

    @GetMapping(Constants.NotificationGetAllForCommunity)
    ResponseEntity<List<String>> getAllForCommunity(@RequestBody NotificationCommunityRequest userEmail);

    @GetMapping(Constants.NotificationGetInfo)
    ResponseEntity<NotificationInfoResponse> getInfo(@RequestBody NotificationGetInfoRequest userEmail);

    @PostMapping(Constants.NotificationSetInfo)
    ResponseEntity<Void> setInfo(@RequestBody NotificationSetInfoRequest userEmail);

    @PostMapping(Constants.NotificationSend)
    ResponseEntity<String> send(@RequestBody NotificationSendRequest userEmail);
}
