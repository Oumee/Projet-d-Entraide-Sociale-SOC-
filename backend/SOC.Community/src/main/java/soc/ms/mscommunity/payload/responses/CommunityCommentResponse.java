package soc.ms.mscommunity.payload.responses;

import java.time.LocalDateTime;
import java.util.List;

public record CommunityCommentResponse(
        String commentId,
        String senderEmail,
        String content,
        LocalDateTime creationDate,
        LocalDateTime lastUpdateDate,
        boolean isDeleted,
        boolean isPinned,
        List<String> userEmailsWhoLikedComments,
        List<String> userEmailsWhoDislikedComments,
        List<String> replyCommentIds) {
}
