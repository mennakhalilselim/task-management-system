package io.github.mennakhalilselim.taskmanagementsystem.model.dto.request;

import io.github.mennakhalilselim.taskmanagementsystem.validation.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;
}
