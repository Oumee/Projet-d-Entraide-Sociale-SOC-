package soc.ms.msmessenger.payload.requests;

import soc.ms.msdatamongodb.data.SocNotificationType;

import java.time.LocalDateTime;

public record NotificationSetInfoRequest(
        String notificationId,
        String title,
        String message,
        SocNotificationType type,
        LocalDateTime finalDate) {
}
