package soc.ms.mssnaps.payload.requests;

import org.springframework.web.multipart.MultipartFile;

public record UploadImageRequest(
        String userEmail,
        String communityName,
        MultipartFile imageFile) {
}
