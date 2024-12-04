package soc.ms.msevent.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import soc.ms.msevent.interfaces.controllers.ISocEventController;
import soc.ms.msevent.interfaces.services.ISocEventService;
import soc.ms.msevent.payload.requests.CommunityRequest;
import soc.ms.msevent.payload.requests.CreateEventRequest;
import soc.ms.msevent.payload.requests.EventInfoRequest;
import soc.ms.msevent.payload.requests.EventSubscriptionRequest;
import soc.ms.msevent.payload.responses.EventInfoResponse;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SocEventControllerImpl implements ISocEventController {
    @Autowired
    ISocEventService _eventService;

    @Override
    public ResponseEntity<Long> createEvent(CreateEventRequest request) {
        return ResponseEntity.ok(_eventService.createEvent(request));
    }

    @Override
    public ResponseEntity<EventInfoResponse> getEventInfo(EventInfoRequest request) {
        return ResponseEntity.ok(_eventService.getEventInfo(request.eventId()));
    }

    @Override
    public ResponseEntity<List<Long>> getAllEvents(CommunityRequest request) {
        return ResponseEntity.ok(_eventService.getAllEvents(request.communityName()).toList());
    }

    @Override
    public ResponseEntity<List<Long>> getAllPendingEvents(CommunityRequest request) {
        return ResponseEntity.ok(_eventService.getAllPendingEvents(request.communityName()).toList());
    }

    @Override
    public ResponseEntity<Void> subscribeToEvents(EventSubscriptionRequest request) {
        _eventService.subscribeToEvents(request.userEmail(), request.communityName());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<String>> subscribeToEvents(CommunityRequest request) {
        return ResponseEntity.ok(_eventService.getAllSubscribers(request.communityName()).toList());
    }

    @Override
    public ResponseEntity<Void> unsubscribeFromEvents(EventSubscriptionRequest request) {
        _eventService.unsubscribeFromEvents(request.userEmail(), request.communityName());
        return ResponseEntity.ok().build();
    }
}
