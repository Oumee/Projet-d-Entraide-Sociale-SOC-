package soc.ms.mscommunity.payload.requests;

public record CommunityCommentAddRequest(
        String communityName,
        String senderEmail,
        String content) {
}
