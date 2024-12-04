package soc.ms.msevent.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import soc.ms.msevent.interfaces.Constants;
import soc.ms.msevent.payload.requests.CommunityRequest;
import soc.ms.msevent.payload.requests.CreateEventRequest;
import soc.ms.msevent.payload.requests.EventInfoRequest;
import soc.ms.msevent.payload.requests.EventSubscriptionRequest;
import soc.ms.msevent.payload.responses.EventInfoResponse;

import java.util.List;

@RequestMapping(Constants.EventControllerHeader)
public interface ISocEventController {
    @PostMapping(Constants.EventCreate)
    ResponseEntity<Long> createEvent(@RequestBody CreateEventRequest request);

    @GetMapping(Constants.EventGetInfo)
    ResponseEntity<EventInfoResponse> getEventInfo(@RequestBody EventInfoRequest request);

    @GetMapping(Constants.EventGetAll)
    ResponseEntity<List<Long>> getAllEvents(@RequestBody CommunityRequest request);

    @GetMapping(Constants.EventGetAllPending)
    ResponseEntity<List<Long>> getAllPendingEvents(@RequestBody CommunityRequest request);

    @PostMapping(Constants.EventSubEvent)
    ResponseEntity<Void> subscribeToEvents(@RequestBody EventSubscriptionRequest request);

    @GetMapping(Constants.EventGetSubs)
    ResponseEntity<List<String>> subscribeToEvents(@RequestBody CommunityRequest request);

    @PostMapping(Constants.EventUnsubEvent)
    ResponseEntity<Void> unsubscribeFromEvents(@RequestBody EventSubscriptionRequest request);
}
