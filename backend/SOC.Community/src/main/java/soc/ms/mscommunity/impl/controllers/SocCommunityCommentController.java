package soc.ms.mscommunity.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soc.ms.mscommunity.interfaces.controllers.ISocCommunityCommentController;
import soc.ms.mscommunity.interfaces.services.ISocCommunityCommentService;
import soc.ms.mscommunity.payload.requests.*;
import soc.ms.mscommunity.payload.responses.CommunityCommentFlatResponse;
import soc.ms.mscommunity.payload.responses.CommunityCommentResponse;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SocCommunityCommentController implements ISocCommunityCommentController {

    @Autowired
    ISocCommunityCommentService _commentService;

    @Override
    public ResponseEntity<List<CommunityCommentResponse>> getAllComments(@RequestBody CommunityRequest request) {
        return ResponseEntity.ok(_commentService.getComments(request.communityName()).toList());
    }

    @Override
    public ResponseEntity<List<CommunityCommentFlatResponse>> getAllFlatComments(@RequestBody CommunityRequest request) {
        return ResponseEntity.ok(_commentService.getFlatComments(request.communityName()).toList());
    }

    @Override
    public ResponseEntity<CommunityCommentResponse> getComment(@RequestBody CommunityCommentRequest request) {
        return ResponseEntity.ok(_commentService.getComment(request.commentId()));
    }

    @Override
    public ResponseEntity<CommunityCommentFlatResponse> getFlatComment(@RequestBody CommunityCommentRequest request) {
        return ResponseEntity.ok(_commentService.getFlatComment(request.commentId()));
    }

    @Override
    public ResponseEntity<String> addComment(@RequestBody CommunityCommentAddRequest request) {
        return ResponseEntity.ok(_commentService.addComment(request));
    }

    @Override
    public ResponseEntity<Void> pinComment(@RequestBody CommunityCommentPinRequest request) {
        _commentService.pinComment(request.commentId(), request.pin());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> deleteComment(@RequestBody CommunityCommentRequest request) {
        _commentService.deleteComment(request.commentId());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> changeComment(@RequestBody CommunityCommentUpdateRequest request) {
        _commentService.changeComment(request.commentId(), request.newContent());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> reactComment(@RequestBody CommmunityCommentReactRequest request) {
        _commentService.reactComment(request.commentId(), request.userEmail(), request.like(), request.add());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> replyToComment(@RequestBody CommunityCommentReplyRequest request) {
        return ResponseEntity.ok(_commentService.replyToComment(request));
    }
}
