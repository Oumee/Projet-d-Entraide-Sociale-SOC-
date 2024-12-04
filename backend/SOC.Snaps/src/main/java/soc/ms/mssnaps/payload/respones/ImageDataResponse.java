package soc.ms.mssnaps.payload.respones;

public record ImageDataResponse(
        byte[] image,
        ImageInfoResponse imageInfo) {
}
