package soc.ms.mssnaps.impl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import soc.ms.msdatamongodb.data.SocImage;
import soc.ms.msdatamongodb.repository.*;
import soc.ms.mssnaps.interfaces.Constants;
import soc.ms.mssnaps.interfaces.services.ISnapsServices;
import soc.ms.mssnaps.payload.requests.DownloadImageRequest;
import soc.ms.mssnaps.payload.respones.ImageDataResponse;
import soc.ms.mssnaps.payload.respones.ImageInfoResponse;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SnapsController.class)
public class SnapsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISnapsServices snapsService;

    @MockBean
    private ISocSnapsRepository snapsRepository;

    @MockBean
    private ISocAdminRepository adminRepository;

    @MockBean
    private ISocCommunityRepository communityRepository;

    @MockBean
    private ISocCommunityCommentRepository communityCommentRepository;

    @MockBean
    private ISocUserRepository userRepository;

    @MockBean
    private ISocUserResponsableDemandeRepository responsableDemandeRepository;

    private ObjectMapper objectMapper;

    SocImage snap;
    ImageInfoResponse res, res2;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        snap = new SocImage("idrc56755", "test@gmail.com", "SOS", "test.png", 54L,
                "C:\\Users\\gx178\\Pictures\\Screenshot 2023-08-07 123649.png", "fileencodedto64", "png",
                LocalDateTime.now(), LocalDateTime.now());

        // Create a valid ImageInfoResponse
        res = new ImageInfoResponse("idrc56755", "test@gmail.com", "SOS", "test.png", 54L,
                "fileencodedto64", "png", LocalDateTime.now(), LocalDateTime.now());
        res2 = new ImageInfoResponse("idrc56785", "testing@gmail.com", "SOS", "test.png", 54L,
                "fileencodedto64", "png", LocalDateTime.now(), LocalDateTime.now());

        // with 3.0 (or with 2.10 as alternative)
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void testDownoaldSnap() throws Exception {
        String id = snap.getId();
        ImageDataResponse response = new ImageDataResponse(Base64.getDecoder().decode(snap.getData()), res);
        when(snapsService.downloadImage(id)).thenReturn(response);

        mockMvc.perform(get(Constants.SnapsControllerHeader + Constants.SnapsDownload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"imageId\":\"" + snap.getId() + "\"}"))
                .andExpect(status().isOk())
                .andDo(print());
        verify(snapsService).downloadImage(id);
    }

    @Test
    void testUploadImage() throws Exception {

        byte[] decodedBytes = Base64.getDecoder().decode(snap.getData());
        MockMultipartFile file = new MockMultipartFile(
                "imageFile",
                snap.getLabel(),
                snap.getType(),
                new ByteArrayInputStream(decodedBytes)
        );

        doNothing().when(snapsService).uploadSnap(snap.getUploaderUserEmail(), snap.getCommunityName(), file);
        mockMvc.perform(multipart(Constants.SnapsControllerHeader + Constants.SnapsUpload)
                        .file(file)
                        .param("userEmail", snap.getUploaderUserEmail())
                        .param("communityName", snap.getCommunityName()))
                .andExpect(status().isOk())
                .andDo(print());
        verify(snapsService, times(1)).uploadSnap("test@gmail.com", "SOS", file);

    }

    @Test
    void testGetImageInfo() throws Exception {
        String imageId = "idrc56755";
        DownloadImageRequest request = new DownloadImageRequest(imageId);
        ImageInfoResponse mockResponse = new ImageInfoResponse("idrc56755", "test.png", "test@gmail.com", "SOS", 54L, "C:\\Users\\gx178\\Pictures\\Screenshot 2023-08-07 123649.png", "png", LocalDateTime.now(), LocalDateTime.now());

        when(snapsService.getImageInfo(imageId)).thenReturn(mockResponse);

        mockMvc.perform(get(Constants.SnapsControllerHeader + Constants.SnapsGetInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockResponse)))
                .andDo(print());

        verify(snapsService, times(1)).getImageInfo(imageId);
    }

    @Test
    void testSnapsGetAllImages() throws Exception {

        when(snapsService.getAllImages()).thenReturn(Stream.of(res, res2));
        mockMvc.perform(get(Constants.SnapsControllerHeader + Constants.SnapsGetAll))
                .andExpect(status().isOk())
                .andDo(print());
        verify(snapsService, times(1)).getAllImages();


    }

    @Test
    void testgetAllImagesUploadedByUser() throws Exception {
        when(snapsService.getAllImagesUploadedByUser(snap.getUploaderUserEmail())).thenReturn(Stream.of(res));
        mockMvc.perform(get(Constants.SnapsControllerHeader + Constants.SnapsGetAllImagesForUser)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userEmail\":\"" + snap.getUploaderUserEmail() + "\"}"))
                .andExpect(status().isOk())
                .andDo(print());
        verify(snapsService, times(1)).getAllImagesUploadedByUser("test@gmail.com");
    }

    @Test
    void testgetAllImagesForCommunity() throws Exception {

        when(snapsService.getAllImagesForCommunity(snap.getCommunityName())).thenReturn(Stream.of(res));
        mockMvc.perform(get(Constants.SnapsControllerHeader + Constants.SnapsGetAllImagesForCommunity)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"communityName\":\"" + snap.getCommunityName() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uploaderEmail").value("SOS"))  // Verify the content in the response
                .andDo(print());
        verify(snapsService, times(1)).getAllImagesForCommunity("SOS");

    }

    @AfterEach
    void clearUp() {
        snap = null;
        res = null;
        res2 = null;
    }

}
