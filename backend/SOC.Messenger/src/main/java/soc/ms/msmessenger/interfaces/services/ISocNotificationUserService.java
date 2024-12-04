package soc.ms.msmessenger.interfaces.services;

import soc.ms.msmessenger.payload.requests.NotificationUserRequest;

import java.util.stream.Stream;

public interface ISocNotificationUserService {

    Stream<String> getAllUsers();

    Stream<String> getAllForUser(NotificationUserRequest userEmail);
}
