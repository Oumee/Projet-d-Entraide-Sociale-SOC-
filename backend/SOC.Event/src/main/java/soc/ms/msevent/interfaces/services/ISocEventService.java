package soc.ms.msevent.interfaces.services;

import soc.ms.msevent.payload.requests.CreateEventRequest;
import soc.ms.msevent.payload.responses.EventInfoResponse;

import java.util.stream.Stream;

public interface ISocEventService {

    long createEvent(CreateEventRequest request);

    EventInfoResponse getEventInfo(long eventId);

    Stream<Long> getAllEvents(String communityName);

    Stream<Long> getAllPendingEvents(String communityName);

    void subscribeToEvents(String userEmail, String communityName);

    Stream<String> getAllSubscribers(String communityName);

    void unsubscribeFromEvents(String userEmail, String communityName);
}
