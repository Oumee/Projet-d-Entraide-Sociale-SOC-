package soc.ms.mscommunity.interfaces.services;

import soc.ms.mscommunity.payload.requests.CommunityInfoRequest;
import soc.ms.mscommunity.payload.responses.CommunityInfoResponse;

import java.util.stream.Stream;

public interface ISocCommunityService {
    Stream<String> getAllCommunities();

    Stream<String> getAllMembers(String communityName);

    CommunityInfoResponse getCommunityInfo(String communityName);

    void setCommunityInfo(CommunityInfoRequest communityInfo);
}
