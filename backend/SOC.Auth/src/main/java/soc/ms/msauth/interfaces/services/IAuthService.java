package soc.ms.msauth.interfaces.services;

import soc.ms.msauth.payload.requests.RegisterRequest;
import soc.ms.msauth.payload.responses.UserInfoResponse;

public interface IAuthService {
    UserInfoResponse login(String email, String password);
    UserInfoResponse signUp(RegisterRequest registerRequest);
    void logout();
}
