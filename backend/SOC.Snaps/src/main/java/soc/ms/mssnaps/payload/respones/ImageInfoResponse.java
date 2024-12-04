package soc.ms.mssnaps.payload.respones;

import java.time.LocalDateTime;

public record ImageInfoResponse(
        String id,
        String label,
        String uploaderEmail,
        String communityName,
        Long size,
        String path,
        String type,
        LocalDateTime uploadDate,
        LocalDateTime lastUpdateDate) {
}
