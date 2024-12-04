package soc.ms.mscommunity.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import soc.ms.mscommunity.interfaces.Constants;
import soc.ms.mscommunity.payload.requests.CommunityInfoRequest;
import soc.ms.mscommunity.payload.requests.CommunityRequest;
import soc.ms.mscommunity.payload.responses.CommunityInfoResponse;

import java.util.List;

@RequestMapping(Constants.CommunityControllerHeader)
public interface ISocCommunityController {

    @GetMapping(Constants.CommunityGetAllCommunities)
    ResponseEntity<List<String>> getAllCommunities();

    @GetMapping(Constants.CommunityGetInfo)
    ResponseEntity<CommunityInfoResponse> getCommunityInfo(@RequestBody CommunityRequest communityInfo);

    @PostMapping(Constants.CommunitySetInfo)
    ResponseEntity<Void> setCommunityInfo(@RequestBody CommunityInfoRequest communityInfo);

    @GetMapping(Constants.CommunityGetAllMembers)
    ResponseEntity<List<String>> getAllMembers(@RequestBody CommunityRequest communityInfo);
}
