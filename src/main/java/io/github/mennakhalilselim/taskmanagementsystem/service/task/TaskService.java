package io.github.mennakhalilselim.taskmanagementsystem.service.task;

import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.CreateTaskRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.UpdateTaskRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.TaskResponse;
import io.github.mennakhalilselim.taskmanagementsystem.model.type.TaskStatus;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest taskCreate, Authentication authentication);

    List<TaskResponse> findTasksForAuthenticatedUser(Authentication authentication);

    TaskResponse findTaskById(UUID taskId, Authentication authentication);

    TaskResponse updateTask(UUID taskId, UpdateTaskRequest taskUpdate, Authentication authentication);

    TaskResponse updateTaskStatus(UUID taskId, TaskStatus taskStatus, Authentication authentication);

    void deleteTask(UUID taskId, Authentication authentication);
}
