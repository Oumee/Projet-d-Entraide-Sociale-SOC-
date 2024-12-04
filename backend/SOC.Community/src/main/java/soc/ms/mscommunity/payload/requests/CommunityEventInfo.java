package soc.ms.mscommunity.payload.requests;

import java.util.Date;

public record CommunityEventInfo(
        String communityName,
        String eventName,
        String eventMessage,
        Date eventDate,
        String eventLocation,
        String eventDescription) {
}
