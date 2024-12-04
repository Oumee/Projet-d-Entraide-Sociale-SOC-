package soc.ms.mscommunity.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import soc.ms.mscommunity.interfaces.Constants;
import soc.ms.mscommunity.payload.requests.*;
import soc.ms.mscommunity.payload.responses.CommunityCommentFlatResponse;
import soc.ms.mscommunity.payload.responses.CommunityCommentResponse;

import java.util.List;

@RequestMapping(Constants.CommunityCommentControllerHeader)
public interface ISocCommunityCommentController {

    @GetMapping(Constants.CommunityCommentGetAllComments)
    ResponseEntity<List<CommunityCommentResponse>> getAllComments(@RequestBody CommunityRequest request);

    @GetMapping(Constants.CommunityCommentGetAllFlatComments)
    ResponseEntity<List<CommunityCommentFlatResponse>> getAllFlatComments(@RequestBody CommunityRequest request);

    @GetMapping(Constants.CommunityCommentGetComment)
    ResponseEntity<CommunityCommentResponse> getComment(@RequestBody CommunityCommentRequest request);

    @GetMapping(Constants.CommunityCommentGetFlatComment)
    ResponseEntity<CommunityCommentFlatResponse> getFlatComment(@RequestBody CommunityCommentRequest request);

    @PostMapping(Constants.CommunityCommentAdd)
    ResponseEntity<String> addComment(@RequestBody CommunityCommentAddRequest request);

    @PostMapping(Constants.CommunityCommentPin)
    ResponseEntity<Void> pinComment(@RequestBody CommunityCommentPinRequest request);

    @PostMapping(Constants.CommunityCommentDelete)
    ResponseEntity<String> deleteComment(@RequestBody CommunityCommentRequest request);

    @PostMapping(Constants.CommunityCommentChange)
    ResponseEntity<String> changeComment(@RequestBody CommunityCommentUpdateRequest request);

    @PostMapping(Constants.CommunityCommentReact)
    ResponseEntity<String> reactComment(@RequestBody CommmunityCommentReactRequest request);

    @PostMapping(Constants.CommunityCommentReply)
    ResponseEntity<String> replyToComment(@RequestBody CommunityCommentReplyRequest request);
}
