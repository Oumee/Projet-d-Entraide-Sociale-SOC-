package soc.ms.msuser.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import soc.ms.msclientapi.user.payload.requests.UserCommunityQueryRequest;
import soc.ms.msclientapi.user.payload.requests.UserQueryRequest;
import soc.ms.msclientapi.user.payload.responses.UserReponse;
import soc.ms.msdatamongodb.repository.*;
import soc.ms.mssnaps.interfaces.services.ISnapsServices;
import soc.ms.msuser.interfaces.Constants;
import soc.ms.msuser.interfaces.services.ISocUserCommunityService;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.param;


@WebMvcTest(ControllerTest.class)

public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ISocUserCommunityService _userService;
    @MockBean
    private ISocUserRepository _userRepository;
    @MockBean
    private ISocAdminRepository adminRepository;



    @MockBean
    private ISnapsServices snapsService;

    @MockBean
    private ISocSnapsRepository snapsRepository;


    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private ISocCommunityRepository communityRepository;

    @MockBean
    private ISocCommunityCommentRepository communityCommentRepository;
    @MockBean
    private ISocNotificationRepository iSocNotificationRepository;

    @MockBean
    private ISocNotificationUserRepository iSocNotificationUserRepository;
    @MockBean
    private ISocUserResponsableDemandeRepository responsableDemandeRepository;

    UserReponse userReponse;
    UserCommunityQueryRequest userCommunityQueryRequest;
    UserQueryRequest  userQueryRequest;
    @BeforeEach
    public void setUp()
    {
        userReponse= new UserReponse("test@gmail.com","test1", null,null,"adress");
        userCommunityQueryRequest= new UserCommunityQueryRequest("test@gmail.com","SOS");
        userQueryRequest = new UserQueryRequest("test@gmail.com");

    }

    @Test
    public void getAllUesrsTest() throws Exception
    {

        when(_userService.getAllUserEmails()).thenReturn(Stream.of(userReponse.address()));
        mockMvc.perform(get(Constants.UserControllerHeader+Constants.UserGetAll))
                .andExpect(status().is(404))
                .andDo(print());
        //verify(_userService.getAllUserEmails()).toList();
    }

    @Test
    public void getUserTest() throws Exception
    {
        when (_userService.getUser("test@gmail.com")).thenReturn(userReponse);
        mockMvc.perform(get(Constants.UserControllerHeader+Constants.UserGet))
                .andExpect(status().is(404))
                .andDo(print());
       // verify(_userService.getUser("test@gmail.com")).fullName();

    }
    @Test
    public void getCommunitiesTest() throws  Exception
    {

        when (_userService.getCommunities("test@gmail.com")).thenReturn(Stream.of("SOS"));
        mockMvc.perform(get(Constants.UserControllerHeader + Constants.UserGetCommunities)  // Change to POST for sending body
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userQueryRequest))) // Send userQuery as JSON
                .andExpect(status().is(404))
                .andDo(print());
    }

    @Test
    public void joinCommunityTest() throws Exception
    {
        doNothing().when(_userService).joinCommunity(userCommunityQueryRequest.userEmail(), userCommunityQueryRequest.communityName());
        mockMvc.perform(post(Constants.UserControllerHeader+Constants.UserJoinCommunity))
                .andExpect(status().is(404))
                .andDo(print());
        //verify(_userService, times(1)).joinCommunity(userCommunityQueryRequest.userEmail(), userCommunityQueryRequest.communityName());
    }

    @Test
    public void leaveCommunityTest() throws Exception
    {
        doNothing().when(_userService).leaveCommunity(userCommunityQueryRequest.userEmail(), userCommunityQueryRequest.communityName());
        mockMvc.perform(post(Constants.UserControllerHeader+Constants.UserLeaveCommunity))
                .andExpect(status().is(404))
                .andDo(print());
       // verify(_userService, times(1)).leaveCommunity(userCommunityQueryRequest.userEmail(), userCommunityQueryRequest.communityName());
    }

}
