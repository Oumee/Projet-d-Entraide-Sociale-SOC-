package soc.ms.mssnaps.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soc.ms.mssnaps.interfaces.Constants;
import soc.ms.mssnaps.payload.requests.CommunityDownloadImageRequest;
import soc.ms.mssnaps.payload.requests.DownloadImageRequest;
import soc.ms.mssnaps.payload.requests.UploadImageRequest;
import soc.ms.mssnaps.payload.requests.UserDownloadImageRequest;
import soc.ms.mssnaps.payload.respones.ImageDataResponse;
import soc.ms.mssnaps.payload.respones.ImageInfoResponse;
import soc.ms.mssnaps.interfaces.services.ISnapsServices;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Constants.SnapsControllerHeader)
public class SnapsController {

    @Autowired
    private ISnapsServices snapsService;

    @PostMapping(Constants.SnapsUpload)
    ResponseEntity<Void> uploadSnap(@RequestParam("userEmail") String userEmail,
                                    @RequestParam("communityName") String communityName,
                                    @RequestParam("imageFile") MultipartFile imageFile) {
        snapsService.uploadSnap(userEmail, communityName, imageFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(Constants.SnapsDownload)
    ResponseEntity<ImageDataResponse> downloadImage(@RequestBody DownloadImageRequest request) {
        return ResponseEntity.ok(snapsService.downloadImage(request.imageId()));
    }

    @GetMapping(Constants.SnapsGetInfo)
    ResponseEntity<ImageInfoResponse> getImageInfo(@RequestBody DownloadImageRequest request) {
        return ResponseEntity.ok(snapsService.getImageInfo(request.imageId()));
    }

    @GetMapping(Constants.SnapsGetAll)
    ResponseEntity<List<ImageInfoResponse>> SnapsGetAll() {
        return ResponseEntity.ok(snapsService.getAllImages().toList());
    }

    @GetMapping(Constants.SnapsGetAllImagesForUser)
    ResponseEntity<List<ImageInfoResponse>> getAllImagesUploadedBy(@RequestBody UserDownloadImageRequest request) {
        return ResponseEntity.ok(snapsService.getAllImagesUploadedByUser(request.userEmail()).toList());
    }

    @GetMapping(Constants.SnapsGetAllImagesForCommunity)
    ResponseEntity<List<ImageInfoResponse>> getAllImagesForCommunity(@RequestBody CommunityDownloadImageRequest request) {
        return ResponseEntity.ok(snapsService.getAllImagesForCommunity(request.communityName()).toList());
    }
}
