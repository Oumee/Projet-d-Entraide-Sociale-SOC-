package soc.ms.msuser.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import soc.ms.msclientapi.user.payload.requests.UserCommunityQueryRequest;
import soc.ms.msclientapi.user.payload.requests.UserQueryRequest;
import soc.ms.msclientapi.user.payload.responses.UserReponse;
import soc.ms.msuser.interfaces.Constants;

import java.util.List;

@RequestMapping(Constants.UserControllerHeader)
public interface ISocUserController {

    @GetMapping(Constants.UserGetAll)
    ResponseEntity<List<String>> getAllUsers();

    @GetMapping(Constants.UserGet)
    ResponseEntity<UserReponse> getUser(@RequestBody UserQueryRequest userQuery);

    @GetMapping(Constants.UserGetCommunities)
    ResponseEntity<List<String>> getCommunities(@RequestBody UserQueryRequest userQuery);

    @PostMapping(Constants.UserJoinCommunity)
    ResponseEntity<Void> joinCommunity(@RequestBody UserCommunityQueryRequest communityQuery);

    @PostMapping(Constants.UserLeaveCommunity)
    ResponseEntity<Void> leaveCommunity(@RequestBody UserCommunityQueryRequest communityQuery);
}
