package io.github.mennakhalilselim.taskmanagementsystem.controller;

import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.LoginRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.RegisterRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.ApiResponseDto;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.AuthenticationResponse;
import io.github.mennakhalilselim.taskmanagementsystem.service.auth.AuthenticationService;
import io.github.mennakhalilselim.taskmanagementsystem.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<ApiResponseDto<AuthenticationResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponseUtil.created(authenticationService.register(request), "Registered successfully");
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponseDto<AuthenticationResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponseUtil.success(authenticationService.login(request), "logged in successfully");
    }
}
