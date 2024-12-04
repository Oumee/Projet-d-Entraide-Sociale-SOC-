package soc.ms.mscommunity.payload.requests;

import org.locationtech.jts.geom.Coordinate;

import java.time.LocalDate;

public record CommunityInfoRequest(
        String communityName,
        String communityDescription,
        String contactInfo,
        String website,
        String communityType,
        LocalDate creationDate,
        Coordinate location) {
}
