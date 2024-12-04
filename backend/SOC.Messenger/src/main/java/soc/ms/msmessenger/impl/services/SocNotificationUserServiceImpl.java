package soc.ms.msmessenger.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.msdatamongodb.data.SocNotificationUser;
import soc.ms.msdatamongodb.repository.ISocNotificationUserRepository;
import soc.ms.msmessenger.interfaces.services.ISocNotificationUserService;
import soc.ms.msmessenger.payload.requests.NotificationUserRequest;

import java.util.stream.Stream;

@Service
public class SocNotificationUserServiceImpl implements ISocNotificationUserService {
    @Autowired
    private ISocNotificationUserRepository socNotificationUserRepository;

    @Override
    public Stream<String> getAllUsers() {
        return socNotificationUserRepository.findAll()
                .stream()
                .map(SocNotificationUser::getId);
    }

    @Override
    public Stream<String> getAllForUser(NotificationUserRequest userEmail) {
        return socNotificationUserRepository.findById(userEmail.userEmail())
                .orElseThrow()
                .getNotificationIds().stream();
    }
}
