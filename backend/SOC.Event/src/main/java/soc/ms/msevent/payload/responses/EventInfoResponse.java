package soc.ms.msevent.payload.responses;

import org.locationtech.jts.geom.Coordinate;

import java.time.LocalDateTime;

public record EventInfoResponse(
        long id,
        Coordinate location,
        String communityName,
        String name,
        String message,
        String description,
        LocalDateTime sendDate,
        LocalDateTime scheduleDate,
        boolean hasExpired) {
}
