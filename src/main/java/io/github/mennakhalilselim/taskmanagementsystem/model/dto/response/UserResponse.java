package io.github.mennakhalilselim.taskmanagementsystem.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    String name;
    String email;
}
