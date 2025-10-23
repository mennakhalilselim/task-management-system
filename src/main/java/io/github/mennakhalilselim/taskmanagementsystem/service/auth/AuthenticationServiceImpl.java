package io.github.mennakhalilselim.taskmanagementsystem.service.auth;

import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.LoginRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.RegisterRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.AuthenticationResponse;
import io.github.mennakhalilselim.taskmanagementsystem.model.entity.User;
import io.github.mennakhalilselim.taskmanagementsystem.respository.UserRepository;
import io.github.mennakhalilselim.taskmanagementsystem.security.model.SecurityUser;
import io.github.mennakhalilselim.taskmanagementsystem.security.service.JwtService;
import io.github.mennakhalilselim.taskmanagementsystem.security.service.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(new SecurityUser(user));

        return AuthenticationResponse.builder()
                .name(user.getName())
                .token(token)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();

        String token = jwtService.generateToken(securityUser);

        return AuthenticationResponse.builder()
                .name(user.getName())
                .token(token)
                .build();
    }

    @Override
    public void logout(String authHeader) {
        String token = authHeader.substring("Bearer ".length()).trim();
        tokenBlacklistService.blacklist(token);
        SecurityContextHolder.clearContext();
    }
}
