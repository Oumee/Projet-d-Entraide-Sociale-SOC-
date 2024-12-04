package soc.ms.msclientapi.user.payload.requests;

public record UserCommunityQueryRequest(
        String userEmail,
        String communityName) {
}
