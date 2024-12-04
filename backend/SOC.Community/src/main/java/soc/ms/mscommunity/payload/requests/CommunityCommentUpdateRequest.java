package soc.ms.mscommunity.payload.requests;

public record CommunityCommentUpdateRequest(
        String commentId,
        String newContent) {
}
