package io.github.mennakhalilselim.taskmanagementsystem.mapper;

import io.github.mennakhalilselim.taskmanagementsystem.model.dto.request.CreateTaskRequest;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.TaskResponse;
import io.github.mennakhalilselim.taskmanagementsystem.model.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(CreateTaskRequest request);

    TaskResponse toResponse(Task task);
}
