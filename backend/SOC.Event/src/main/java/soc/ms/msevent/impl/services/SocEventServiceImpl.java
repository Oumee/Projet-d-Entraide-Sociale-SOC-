package soc.ms.msevent.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatamongodb.repository.ISocUserRepository;
import soc.ms.msdatapostgis.data.SocEvent;
import soc.ms.msdatapostgis.repository.ISocEventRepository;
import soc.ms.msevent.interfaces.services.ISocEventService;
import soc.ms.msevent.payload.requests.CreateEventRequest;
import soc.ms.msevent.payload.responses.EventInfoResponse;

import java.util.stream.Stream;

@Service
public class SocEventServiceImpl implements ISocEventService {

    @Autowired
    private ISocEventRepository _eventRepository;

    @Autowired
    private ISocCommunityRepository _communityRepository;

    @Autowired
    private ISocUserRepository _userRepository;

    @Override
    public long createEvent(CreateEventRequest request) {
        var event = SocEvent
                .builder()
                .location(request.location())
                .communityName(request.communityName())
                .name(request.name())
                .message(request.message())
                .description(request.description())
                .sendDate(request.sendDate())
                .scheduleDate(request.scheduleDate())
                .build();

        return _eventRepository.save(event).id;
    }

    @Override
    public EventInfoResponse getEventInfo(long eventId) {
        var event = _eventRepository.findById(eventId).orElseThrow();
        return new EventInfoResponse(
                event.id,
                event.location,
                event.communityName,
                event.name,
                event.message,
                event.description,
                event.sendDate,
                event.scheduleDate,
                event.sendDate != null && event.sendDate.isBefore(event.scheduleDate)
        );
    }

    @Override
    public Stream<Long> getAllEvents(String communityName) {
        var x = _eventRepository.findAll();
        var y = _eventRepository.findEventsByCommunity(communityName);
        return _eventRepository.findEventsByCommunity(communityName).stream().map(event -> event.id);
    }

    @Override
    public Stream<Long> getAllPendingEvents(String communityName) {
        return _eventRepository.findEventsByCommunity(communityName)
                .stream()
                .filter(event -> event.sendDate != null && event.sendDate.isBefore(event.scheduleDate))
                .map(event -> event.id);
    }

    @Override
    public void subscribeToEvents(String userEmail, String communityName) {
        var community = _communityRepository.findById(communityName).orElseThrow();
        var user = _userRepository.findById(userEmail).orElseThrow();

        community.getSubscribedUsers().add(user);
        _communityRepository.save(community);
    }

    @Override
    public Stream<String> getAllSubscribers(String communityName) {
        return _communityRepository.findById(communityName).orElseThrow()
                .getSubscribedUsers()
                .stream()
                .map(user -> user.email);
    }


    @Override
    public void unsubscribeFromEvents(String userEmail, String communityName) {
        var community = _communityRepository.findById(communityName).orElseThrow();
        var user = _userRepository.findById(userEmail).orElseThrow();

        community.getSubscribedUsers().removeIf(u -> u.email.equals(userEmail));
        _communityRepository.save(community);
    }
}
