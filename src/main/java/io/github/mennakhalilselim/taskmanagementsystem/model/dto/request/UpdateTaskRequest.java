package io.github.mennakhalilselim.taskmanagementsystem.model.dto.request;

import io.github.mennakhalilselim.taskmanagementsystem.model.type.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    @NotBlank
    @Size(max = 255)
    private String title;

    private String description;

    @NotNull
    private TaskStatus status;
}
