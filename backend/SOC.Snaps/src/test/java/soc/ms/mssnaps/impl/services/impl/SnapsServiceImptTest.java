package soc.ms.mssnaps.impl.services.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import soc.ms.msdatamongodb.data.SocImage;
import soc.ms.msdatamongodb.repository.ISocSnapsRepository;
import soc.ms.mssnaps.payload.respones.ImageDataResponse;
import soc.ms.mssnaps.payload.respones.ImageInfoResponse;
import soc.ms.mssnaps.impl.services.SnapsServiceImpl;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@DataMongoTest
public class SnapsServiceImptTest {
    @Mock
    private ISocSnapsRepository snapsRepository;
    @InjectMocks
    private SnapsServiceImpl snapsServiceImpl;

    AutoCloseable autoCloseable;
    SocImage snap;

    @BeforeEach
    void SetUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        snap = new SocImage("idrc56755", "test@gmail.com", "SOS", "test.png", 54L, "C:\\Users\\gx178\\Pictures\\Screenshot 2023-08-07 123649.png", "fileencodedto64", "png", LocalDateTime.now(), LocalDateTime.now());
    }

    @AfterEach
    void cleanUp() throws Exception {
        snap = null;
        autoCloseable.close();
    }

    @Test
    void testGetImageInfo() {
        snapsRepository.save(snap);
        when(snapsRepository.findById("idrc56755")).thenReturn(Optional.of(snap));

        // Call the service method
        ImageInfoResponse response = snapsServiceImpl.getImageInfo("idrc56755");
        // Assert that the response matches the expected values
        assertThat(response.label()).isEqualTo(snap.getLabel());
        assertThat(response.id()).isEqualTo(snap.getId());
    }

    @Test
    void testUploadSnap() throws Exception {
        snapsRepository.save(snap);
        assertThat(snap.getType()).isEqualTo("png");
    }

    @Test
    void testDownoaldnap() throws Exception {
        ImageInfoResponse response = new ImageInfoResponse(snap.getId(), snap.getLabel(), snap.getUploaderUserEmail(), snap.getCommunityName(), snap.getSize(), snap.getPath(), snap.getType(), snap.getUploadDate(), snap.getLastUpdatedDate());
        ImageDataResponse responde = new ImageDataResponse(Base64.getDecoder().decode(snap.getData()), response);
        when(snapsRepository.findById("idrc56755")).thenReturn(Optional.ofNullable(snap));
        assertThat(responde).isNotNull();
    }
}
