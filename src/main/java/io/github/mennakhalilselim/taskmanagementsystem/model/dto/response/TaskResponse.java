package io.github.mennakhalilselim.taskmanagementsystem.model.dto.response;

import io.github.mennakhalilselim.taskmanagementsystem.model.type.TaskStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
}
