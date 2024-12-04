package soc.ms.mscommunity.payload.requests;

public record CommunityCommentPinRequest(
        String commentId,
        boolean pin) {
}
