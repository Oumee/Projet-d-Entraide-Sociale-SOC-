package soc.ms.msclientapi.user.payload.requests;

import org.springframework.web.multipart.MultipartFile;
import soc.ms.msdatamongodb.data.SocCommunityType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserResponsableDemandeAddRequest(
        String userEmail,
        String text,
        List<MultipartFile> files,
        String name,
        String description,
        String contactInfo,
        String website,
        LocalDate creationDate,
        SocCommunityType type,
        String location) {
}
