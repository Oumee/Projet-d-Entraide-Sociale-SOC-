package soc.ms.mscommunity.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soc.ms.mscommunity.interfaces.controllers.ISocCommunityController;
import soc.ms.mscommunity.interfaces.services.ISocCommunityService;
import soc.ms.mscommunity.payload.requests.CommunityInfoRequest;
import soc.ms.mscommunity.payload.requests.CommunityRequest;
import soc.ms.mscommunity.payload.responses.CommunityInfoResponse;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SocCommunityController implements ISocCommunityController {

    @Autowired
    ISocCommunityService _communityservice;

    @Override
    public ResponseEntity<List<String>> getAllCommunities() {
        return ResponseEntity.ok(_communityservice.getAllCommunities().toList());
    }

    @Override
    public ResponseEntity<CommunityInfoResponse> getCommunityInfo(@RequestBody CommunityRequest communityInfo) {
        return ResponseEntity.ok(_communityservice.getCommunityInfo(communityInfo.communityName()));
    }

    @Override
    public ResponseEntity<Void> setCommunityInfo(@RequestBody CommunityInfoRequest communityInfo) {
        _communityservice.setCommunityInfo(communityInfo);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<String>> getAllMembers(@RequestBody CommunityRequest communityInfo) {
        return ResponseEntity.ok(_communityservice.getAllMembers(communityInfo.communityName()).toList());
    }
}
