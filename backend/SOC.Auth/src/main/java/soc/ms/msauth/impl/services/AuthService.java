package soc.ms.msauth.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.msauth.interfaces.services.IAuthService;
import soc.ms.msauth.interfaces.utils.IJWTUtils;
import soc.ms.msauth.interfaces.utils.JWTUser;
import soc.ms.msauth.payload.requests.RegisterRequest;
import soc.ms.msauth.payload.responses.UserInfoResponse;
import soc.ms.msdatamongodb.data.SocUser;
import soc.ms.msdatamongodb.repository.ISocUserRepository;

@Service
public class AuthService implements IAuthService {

    @Autowired
    ISocUserRepository _userRepository;
    @Autowired
    IJWTUtils _jwtUtils;

    private static UserInfoResponse invalid() {
        return new UserInfoResponse(null, "Invalid username or password");
    }

    @Override
    public UserInfoResponse login(String email, String password) {
        try
        {
            var user = _userRepository.findById(email).orElseThrow();
            if (!user.getPassword().equals(password)) {
                return invalid();
            }

            // Create JWT token
            String jwt = _jwtUtils.createToken(new JWTUser(email));

            // Create response with user info and token
            return new UserInfoResponse(jwt, email);
        } catch (Exception ignored) { }
        return invalid();
    }

    @Override
    public UserInfoResponse signUp(RegisterRequest registerRequest) {
        try {
            var user = SocUser.builder()
                    .email(registerRequest.email())
                    .password(registerRequest.password())
                    .fullName(registerRequest.fullName())
                    .birthDate(registerRequest.birthDate())
                    .phoneNumber(registerRequest.phoneNumber())
                    .address(registerRequest.address())
                    .build();
            _userRepository.save(user);

            // Create JWT token
            String jwt = _jwtUtils.createToken(new JWTUser(user.getEmail()));

            return new UserInfoResponse(jwt, user.getEmail());
        }
        catch (Exception ignored) { }
        return invalid();
    }

    @Override
    public void logout() {
        // Do nothing
    }
}
