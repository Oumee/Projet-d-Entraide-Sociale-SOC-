package soc.ms.msmessenger.payload.requests;

import soc.ms.msdatamongodb.data.SocNotificationType;

import java.time.LocalDateTime;

public record NotificationSendRequest(
        String communityName,
        String title,
        String message,
        SocNotificationType type,
        LocalDateTime finalDate) {
}
