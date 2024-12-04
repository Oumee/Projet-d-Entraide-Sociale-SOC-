package soc.ms.msclientapi.user.payload.responses;

import java.util.Date;

public record UserReponse(
        String userEmail,
        String fullName,
        Date birthDate,
        String phoneNumber,
        String address) {
}
