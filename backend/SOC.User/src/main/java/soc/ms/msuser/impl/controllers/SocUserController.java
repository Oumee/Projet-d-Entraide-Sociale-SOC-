package soc.ms.msuser.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soc.ms.msclientapi.user.payload.requests.UserCommunityQueryRequest;
import soc.ms.msclientapi.user.payload.requests.UserQueryRequest;
import soc.ms.msclientapi.user.payload.responses.UserReponse;
import soc.ms.msuser.interfaces.controllers.ISocUserController;
import soc.ms.msuser.interfaces.services.ISocUserCommunityService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SocUserController implements ISocUserController {

    @Autowired
    ISocUserCommunityService _userService;

    @Override
    public ResponseEntity<List<String>> getAllUsers() {
        return ResponseEntity.ok(_userService.getAllUserEmails().toList());
    }

    @Override
    public ResponseEntity<UserReponse> getUser(@RequestBody UserQueryRequest userQuery) {
        return ResponseEntity.ok(_userService.getUser(userQuery.userEmail()));
    }

    @Override
    public ResponseEntity<List<String>> getCommunities(@RequestBody UserQueryRequest userQuery) {
        return ResponseEntity.ok(_userService.getCommunities(userQuery.userEmail()).toList());
    }

    @Override
    public ResponseEntity<Void> joinCommunity(@RequestBody UserCommunityQueryRequest communityQuery) {
        _userService.joinCommunity(communityQuery.userEmail(), communityQuery.communityName());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> leaveCommunity(@RequestBody UserCommunityQueryRequest communityQuery) {
        _userService.leaveCommunity(communityQuery.userEmail(), communityQuery.communityName());
        return ResponseEntity.ok().build();
    }
}
