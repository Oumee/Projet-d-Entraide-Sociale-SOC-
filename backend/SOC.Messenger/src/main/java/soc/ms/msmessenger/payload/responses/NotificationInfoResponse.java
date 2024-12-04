package soc.ms.msmessenger.payload.responses;

import soc.ms.msdatamongodb.data.SocNotificationType;

import java.time.LocalDateTime;

public record NotificationInfoResponse(
        String notificationId,
        String communityName,
        String title,
        String message,
        SocNotificationType type,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        LocalDateTime finalDate) {
}
