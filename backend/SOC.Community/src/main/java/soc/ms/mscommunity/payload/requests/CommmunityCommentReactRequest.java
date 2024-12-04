package soc.ms.mscommunity.payload.requests;

public record CommmunityCommentReactRequest(
        String commentId,
        String userEmail,
        boolean like,
        boolean add) {
}
