package io.github.mennakhalilselim.taskmanagementsystem.controller;

import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.CreateTaskRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.UpdateTaskRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.UpdateTaskStatusRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.ApiResponseDto;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.TaskResponse;
import io.github.mennakhalilselim.taskmanagementsystem.service.task.TaskService;
import io.github.mennakhalilselim.taskmanagementsystem.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<TaskResponse>> create(
            @Valid @RequestBody CreateTaskRequest request,
            Authentication authentication
    ) {
        TaskResponse response = taskService.createTask(request, authentication);
        return ApiResponseUtil.created(response, "Task added successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<TaskResponse>>> findAllForUser(Authentication authentication) {
        List<TaskResponse> tasks = taskService.findTasksForAuthenticatedUser(authentication);
        return ApiResponseUtil.success(tasks, "Tasks retrieved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TaskResponse>> findById(
            @Valid @PathVariable UUID id,
            Authentication authentication
    ) {
        TaskResponse task = taskService.findTaskById(id, authentication);
        return ApiResponseUtil.success(task, "Tasks retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TaskResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskRequest request,
            Authentication authentication
    ) {
        TaskResponse response = taskService.updateTask(id, request, authentication);
        return ApiResponseUtil.success(response, "Task updated successfully");
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponseDto<TaskResponse>> changeStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskStatusRequest request,
            Authentication authentication
    ) {
        TaskResponse response = taskService.updateTaskStatus(id, request.getStatus(), authentication);
        return ApiResponseUtil.success(response, "Task status updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteTask(
            @PathVariable UUID id,
            Authentication authentication
    ){
        taskService.deleteTask(id, authentication);
        return ApiResponseUtil.success("Task deleted successfully");
    }
}
