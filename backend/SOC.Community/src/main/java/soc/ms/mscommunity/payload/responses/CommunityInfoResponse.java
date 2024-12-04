package soc.ms.mscommunity.payload.responses;

import org.locationtech.jts.geom.Coordinate;

import java.time.LocalDate;

public record CommunityInfoResponse(
    String communityName,
    String communityDescription,
    String communityType,
    String contactInfo,
    String website,
    LocalDate creationDate,
    Coordinate location,
    long memberCount) {
}
