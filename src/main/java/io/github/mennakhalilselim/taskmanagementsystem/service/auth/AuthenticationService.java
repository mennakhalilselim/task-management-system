package io.github.mennakhalilselim.taskmanagementsystem.service.auth;

import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.LoginRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.RegisterRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(LoginRequest request);
}
