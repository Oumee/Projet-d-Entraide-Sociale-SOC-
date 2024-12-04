package soc.ms.msuser.interfaces.services;

import soc.ms.msclientapi.user.payload.responses.UserReponse;

import java.util.stream.Stream;

public interface ISocUserCommunityService {
    Stream<String> getAllUserEmails();
    UserReponse getUser(String userEmail);

    Stream<String> getCommunities(String userEmail);
    void joinCommunity(String userEmail, String communityName);
    void leaveCommunity(String userEmail, String communityName);
}
