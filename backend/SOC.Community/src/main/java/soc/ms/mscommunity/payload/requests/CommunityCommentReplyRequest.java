package soc.ms.mscommunity.payload.requests;

public record CommunityCommentReplyRequest(
        String parentCommentId,
        String communityName,
        String senderEmail,
        String content) {
}
