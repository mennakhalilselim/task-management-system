package io.github.mennakhalilselim.taskmanagementsystem.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String name;
    private String email;
    private String token;
}
