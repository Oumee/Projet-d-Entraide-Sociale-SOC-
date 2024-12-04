package soc.ms.msauth.payload.requests;

import java.util.Date;

public record RegisterRequest(
        String email,
        String password,
        String fullName,
        Date birthDate,
        String phoneNumber,
        String address) {
}
