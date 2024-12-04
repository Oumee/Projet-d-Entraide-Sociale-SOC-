package soc.ms.msevent.payload.requests;

public record EventSubscriptionRequest(
        String userEmail,
        String communityName) {
}
