package soc.ms.msevent.payload.requests;

import org.locationtech.jts.geom.Coordinate;

import java.time.LocalDateTime;

public record CreateEventRequest(
        Coordinate location,
        String communityName,
        String name,
        String message,
        String description,
        LocalDateTime sendDate,
        LocalDateTime scheduleDate) {
}
