package soc.ms.msmessenger.interfaces.services;

import soc.ms.msmessenger.payload.requests.NotificationSendRequest;
import soc.ms.msmessenger.payload.requests.NotificationSetInfoRequest;
import soc.ms.msmessenger.payload.responses.NotificationInfoResponse;

import java.util.stream.Stream;

public interface ISocNotificationService {

    Stream<String> getAll();

    Stream<String> getAllForCommunity(String communityName);

    NotificationInfoResponse getInfo(String notificationId);

    void setInfo(NotificationSetInfoRequest userEmail);

    String send(NotificationSendRequest userEmail);
}
