package soc.ms.msmessenger.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.msdatamongodb.data.SocNotification;
import soc.ms.msdatamongodb.data.SocNotificationUser;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatamongodb.repository.ISocNotificationRepository;
import soc.ms.msdatamongodb.repository.ISocNotificationUserRepository;
import soc.ms.msdatamongodb.repository.ISocUserRepository;
import soc.ms.msmessenger.interfaces.services.ISocNotificationService;
import soc.ms.msmessenger.payload.requests.NotificationSendRequest;
import soc.ms.msmessenger.payload.requests.NotificationSetInfoRequest;
import soc.ms.msmessenger.payload.responses.NotificationInfoResponse;

import java.time.LocalDateTime;
import java.util.stream.Stream;


@Service
public class SocNotificationServiceImpl implements ISocNotificationService {
    @Autowired
    private ISocNotificationRepository socNotificationRepository;

    @Autowired
    private ISocNotificationUserRepository socNotificationUserRepository;

    @Autowired
    private ISocCommunityRepository socCommunityRepository;

    @Autowired
    private ISocUserRepository socUserRepository;

    @Override
    public Stream<String> getAll() {
        return socNotificationRepository
                .findAll()
                .stream()
                .map(SocNotification::getId);
    }

    @Override
    public Stream<String> getAllForCommunity(String communityName) {
        return socNotificationRepository.findByCommunityName(communityName)
                .stream()
                .map(SocNotification::getId);
    }

    @Override
    public NotificationInfoResponse getInfo(String notificationId) {
        var notification = socNotificationRepository.findById(notificationId).orElseThrow();
        return new NotificationInfoResponse(
                notification.getId(),
                notification.getCommunityName(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getType(),
                notification.getCreatedDate(),
                notification.getUpdatedDate(),
                notification.getFinalDate());
    }

    @Override
    public void setInfo(NotificationSetInfoRequest userEmail) {
        var notification = socNotificationRepository.findById(userEmail.notificationId()).orElseThrow();
        notification.setTitle(userEmail.title());
        notification.setMessage(userEmail.message());
        notification.setType(userEmail.type());
        notification.setUpdatedDate(LocalDateTime.now());
        socNotificationRepository.save(notification);
    }

    @Override
    public String send(NotificationSendRequest request) {
        var notification = SocNotification.builder()
                .communityName(request.communityName())
                .title(request.title())
                .message(request.message())
                .type(request.type())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .finalDate(request.finalDate())
                .build();

        var id = socNotificationRepository.save(notification).getId();

        if (request.communityName() != null && !request.communityName().isBlank()) {
            var community = socCommunityRepository.findById(request.communityName()).orElseThrow();

            for (var user : community.getUsers()) {
                var userNotifications = socNotificationUserRepository
                        .findById(user.getEmail())
                        .orElse(SocNotificationUser.builder().id(user.getEmail()).build());

                userNotifications.getNotificationIds().add(id);
                socNotificationUserRepository.save(userNotifications);

                userNotifications = socNotificationUserRepository
                        .findById(user.getEmail())
                        .orElseThrow();
                userNotifications = socNotificationUserRepository
                        .findById(user.getEmail())
                        .orElseThrow();
            }
        }

        return id;
    }
}
