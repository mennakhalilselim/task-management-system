package io.github.mennakhalilselim.taskmanagementsystem.util;

import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.ApiResponseDto;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@UtilityClass
public class ApiResponseUtil {
    public static <T> ResponseEntity<ApiResponseDto<T>> success(T data, String message) {
        return build(HttpStatus.OK, message, data, null);
    }

    public static <T> ResponseEntity<ApiResponseDto<Void>> success(String message) {
        return build(HttpStatus.OK, message, null, null);
    }

    public static <T> ResponseEntity<ApiResponseDto<T>> created(T data, String message) {
        return build(HttpStatus.CREATED, message, data, null);
    }

    public static ResponseEntity<ApiResponseDto<Void>> validationError(Map<String, List<String>> errors) {
        return build(HttpStatus.BAD_REQUEST, "Validation Failed", null, errors);
    }

    public static ResponseEntity<ApiResponseDto<Void>> badRequest(String message) {
        return build(HttpStatus.BAD_REQUEST, message, null, null);
    }

    public static ResponseEntity<ApiResponseDto<Void>> unauthorized(String message) {
        return build(HttpStatus.UNAUTHORIZED, message, null, null);
    }

    public static ResponseEntity<ApiResponseDto<Void>> notFound(String message) {
        return build(HttpStatus.NOT_FOUND, message, null, Map.of("error", List.of(message)));
    }

    public static ResponseEntity<ApiResponseDto<Void>> internalServerError() {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null, null);
    }

    public static ResponseEntity<ApiResponseDto<Void>> internalServerError(String message) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, message, null, null);
    }

    private static <T> ResponseEntity<ApiResponseDto<T>> build(
            HttpStatus status,
            String message,
            T data,
            Map<String, List<String>> errors
    ) {
        ApiResponseDto<T> apiResponse = ApiResponseDto.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .errors(errors)
                .build();

        return ResponseEntity.status(status).body(apiResponse);
    }
}

