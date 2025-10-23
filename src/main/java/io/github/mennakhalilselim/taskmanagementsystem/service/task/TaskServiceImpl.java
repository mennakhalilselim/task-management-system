package io.github.mennakhalilselim.taskmanagementsystem.service.task;

import io.github.mennakhalilselim.taskmanagementsystem.error.exception.TaskNotFound;
import io.github.mennakhalilselim.taskmanagementsystem.mapper.TaskMapper;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.CreateTaskRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.UpdateTaskRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.TaskResponse;
import io.github.mennakhalilselim.taskmanagementsystem.model.entity.Task;
import io.github.mennakhalilselim.taskmanagementsystem.model.entity.User;
import io.github.mennakhalilselim.taskmanagementsystem.model.type.TaskStatus;
import io.github.mennakhalilselim.taskmanagementsystem.respository.TaskRepository;
import io.github.mennakhalilselim.taskmanagementsystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(CreateTaskRequest taskRequest, Authentication authentication) {
        User owner = AuthUtil.extractUserFromAuthentication(authentication);

        Task task = taskMapper.toEntity(taskRequest);
        task.setOwner(owner);

        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    @Override
    public List<TaskResponse> findTasksForAuthenticatedUser(Authentication authentication) {
        User owner = AuthUtil.extractUserFromAuthentication(authentication);
        return taskRepository.findByOwner(owner).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse findTaskById(UUID taskId, Authentication authentication) {
        Task task = loadOwnedTaskOrThrow(taskId, authentication);
        return taskMapper.toResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(UUID taskId, UpdateTaskRequest taskRequest, Authentication authentication) {
        Task task = loadOwnedTaskOrThrow(taskId, authentication);

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponse(updatedTask);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskStatus(UUID taskId, TaskStatus taskStatus, Authentication authentication) {
        Task task = loadOwnedTaskOrThrow(taskId, authentication);

        task.setStatus(taskStatus);

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponse(updatedTask);
    }

    @Override
    public void deleteTask(UUID taskId, Authentication authentication) {
        Task task = loadOwnedTaskOrThrow(taskId, authentication);
        taskRepository.delete(task);
    }

    private Task loadOwnedTaskOrThrow(UUID taskId, Authentication authentication) {
        User owner = AuthUtil.extractUserFromAuthentication(authentication);
        return taskRepository.findByIdAndOwner(taskId, owner)
                .orElseThrow(() -> new TaskNotFound("Task not found for id: " + taskId));
    }
}
