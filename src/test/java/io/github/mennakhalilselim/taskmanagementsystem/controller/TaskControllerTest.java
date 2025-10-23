package io.github.mennakhalilselim.taskmanagementsystem.controller;

import io.github.mennakhalilselim.taskmanagementsystem.error.exception.TaskNotFound;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.ApiResponseDto;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.TaskResponse;
import io.github.mennakhalilselim.taskmanagementsystem.service.task.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private TaskController taskController;

    @Test
    void findById_returnsWrappedTaskResponse() {
        UUID id = UUID.randomUUID();

        TaskResponse mockedTaskResponse = mock(TaskResponse.class);

        when(taskService.findTaskById(id, authentication)).thenReturn(mockedTaskResponse);

        ResponseEntity<ApiResponseDto<TaskResponse>> response = taskController.findById(id, authentication);

        assertNotNull(response, "ResponseEntity should not be null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be 200 OK");

        ApiResponseDto<TaskResponse> body = response.getBody();
        assertNotNull(body, "Response body (ApiResponseDto) should not be null");

        assertSame(mockedTaskResponse, body.getData(), "Returned TaskResponse should be the same instance returned by the service");
    }

    @Test
    void findById_whenTaskNotFound_throwsTaskNotFound() {
        UUID id = UUID.randomUUID();

        when(taskService.findTaskById(id, authentication))
                .thenThrow(new TaskNotFound("Task not found for id: " + id));

        assertThrows(TaskNotFound.class, () -> taskController.findById(id, authentication));
    }
}
