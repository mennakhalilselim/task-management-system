package io.github.mennakhalilselim.taskmanagementsystem.model.dto.request;

import io.github.mennakhalilselim.taskmanagementsystem.model.type.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskStatusRequest {
    @NotNull
    private TaskStatus status;
}
