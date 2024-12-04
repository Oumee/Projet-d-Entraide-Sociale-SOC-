package soc.ms.mssnaps.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import soc.ms.msdatamongodb.data.SocImage;
import soc.ms.msdatamongodb.repository.ISocCommunityRepository;
import soc.ms.msdatamongodb.repository.ISocSnapsRepository;
import soc.ms.mssnaps.interfaces.services.ISnapsServices;
import soc.ms.mssnaps.payload.respones.ImageDataResponse;
import soc.ms.mssnaps.payload.respones.ImageInfoResponse;

import java.util.*;
import java.util.stream.Stream;

@Service
public class SnapsServiceImpl implements ISnapsServices {

    @Autowired
    private ISocSnapsRepository _snapsRepository;

    @Autowired
    private ISocCommunityRepository _communityRepository;

    public void uploadSnap(String userEmail, String communityName, MultipartFile file) {
        var community = _communityRepository.findById(communityName).orElseThrow();

        if (file == null || file.isEmpty() || file.getContentType() == null) {
            throw new IllegalArgumentException("File is Empty");
        }

        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
            throw new IllegalArgumentException("Only PNG and JPEG images are supported");
        }

        var image = _snapsRepository.save(new SocImage(userEmail, communityName, file));
        community.getImageIds().add(image.getId());

        _communityRepository.save(community);
    }

    public ImageDataResponse downloadImage(String id) {
        SocImage image = _snapsRepository.findById(id).orElseThrow();
        return new ImageDataResponse(
                Base64.getDecoder().decode(image.getData()),
                getImageInfo(image));
    }

    public ImageInfoResponse getImageInfo(String id) {
        SocImage image = _snapsRepository.findById(id).orElseThrow();
        return getImageInfo(image);
    }

    @Override
    public Stream<ImageInfoResponse> getAllImages() {
        return _snapsRepository.findAll().stream()
                .map(this::getImageInfo);
    }

    @Override
    public Stream<ImageInfoResponse> getAllImagesUploadedByUser(String userEmail) {
        return _snapsRepository.findAllByUploaderUserEmail(userEmail).stream()
                .map(this::getImageInfo);
    }

    @Override
    public Stream<ImageInfoResponse> getAllImagesForCommunity(String communityName) {
        var community = _communityRepository.findById(communityName).orElseThrow();
        var ids = community.getImageIds();
        if (ids == null) {
            return Stream.empty();
        }
        return community.getImageIds().stream()
                .map(this::getImageInfo);
    }

    private ImageInfoResponse getImageInfo(SocImage image) {
        return new ImageInfoResponse(
                image.getId(),
                image.getLabel(),
                image.getUploaderUserEmail(),
                image.getCommunityName(),
                image.getSize(),
                image.getPath(),
                image.getType(),
                image.getUploadDate(),
                image.getLastUpdatedDate());
    }
}
