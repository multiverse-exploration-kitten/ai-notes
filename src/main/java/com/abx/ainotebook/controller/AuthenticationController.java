package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.AuthenticationRequest;
import com.abx.ainotebook.model.AuthenticationResponse;
import com.abx.ainotebook.model.RegisterRequest;
import com.abx.ainotebook.service.AuthenticationService;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if (request == null
                || Objects.equals(request.getEmail(), "")
                || Objects.equals(request.getPassword(), "")
                || Objects.equals(request.getFirstname(), "")
                || Objects.equals(request.getLastname(), "")) {
            return ResponseEntity.badRequest().body(null);
        }

        AuthenticationResponse response = authenticationService.register(request);
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        if (request == null || Objects.equals(request.getEmail(), "") || Objects.equals(request.getPassword(), "")) {
            return ResponseEntity.badRequest().body(null);
        }

        AuthenticationResponse response = authenticationService.authenticate(request);
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(response);
    }
}
