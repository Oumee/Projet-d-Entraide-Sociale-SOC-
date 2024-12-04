package soc.ms.msauth.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soc.ms.msauth.interfaces.Constants;
import soc.ms.msauth.interfaces.services.IAuthService;
import soc.ms.msauth.payload.requests.LoginRequest;
import soc.ms.msauth.payload.requests.RegisterRequest;
import soc.ms.msauth.payload.responses.UserInfoResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Constants.AuthControllerHeader)
public class AuthController {
    @Autowired
    IAuthService _service;

    @PostMapping(Constants.AuthLogin)
    public ResponseEntity<UserInfoResponse> login(@RequestBody LoginRequest loginRequest) {
        var response = _service.login(loginRequest.email(), loginRequest.password());
        return response.token() == null
                ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
                : ResponseEntity.ok(response);
    }

    @PostMapping(Constants.AuthSingUp)
    public ResponseEntity<UserInfoResponse> signUp(@RequestBody RegisterRequest loginRequest) {
        var response = _service.signUp(loginRequest);
        return response.token() == null
                ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
                : ResponseEntity.ok(response);
    }

    @PostMapping(Constants.AuthLogout)
    public ResponseEntity<Void> logout() {
        _service.logout();
        return ResponseEntity.ok().build();
    }
}
