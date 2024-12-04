package soc.ms.mscommunity.payload.requests;

public record CommunityCommentChangeRequest(
        String commentId,
        String content) {
}
