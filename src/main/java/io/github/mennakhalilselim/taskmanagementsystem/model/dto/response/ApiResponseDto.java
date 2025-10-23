package io.github.mennakhalilselim.taskmanagementsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {
    int status;
    String message;
    T data;
    Map<String, List<String>> errors;

    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();
}
