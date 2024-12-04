package soc.ms.mscommunity.interfaces.services;

import soc.ms.mscommunity.payload.requests.CommunityCommentAddRequest;
import soc.ms.mscommunity.payload.requests.CommunityCommentReplyRequest;
import soc.ms.mscommunity.payload.responses.CommunityCommentFlatResponse;
import soc.ms.mscommunity.payload.responses.CommunityCommentResponse;

import java.util.stream.Stream;

public interface ISocCommunityCommentService {
    Stream<CommunityCommentResponse> getComments(String communityName);

    Stream<CommunityCommentFlatResponse> getFlatComments(String communityName);

    CommunityCommentResponse getComment(String commentId);

    CommunityCommentFlatResponse getFlatComment(String commentId);

    String addComment(CommunityCommentAddRequest request);

    void pinComment(String commentId, boolean pin);

    void deleteComment(String commentId);

    void changeComment(String commentId, String newComment);

    void reactComment(String commentId, String userEmail, boolean like, boolean add);

    String replyToComment(CommunityCommentReplyRequest reply);
}
