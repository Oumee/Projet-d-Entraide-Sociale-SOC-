package soc.ms.mssnaps.interfaces.services;

import org.springframework.web.multipart.MultipartFile;
import soc.ms.mssnaps.payload.respones.ImageDataResponse;
import soc.ms.mssnaps.payload.respones.ImageInfoResponse;

import java.util.stream.Stream;

public interface ISnapsServices {

    void uploadSnap(String userEmail, String communityName, MultipartFile file);

    ImageDataResponse downloadImage(String id);

    ImageInfoResponse getImageInfo(String id);

    Stream<ImageInfoResponse> getAllImages();

    Stream<ImageInfoResponse> getAllImagesUploadedByUser(String userEmail);

    Stream<ImageInfoResponse> getAllImagesForCommunity(String communityName);
}
