package io.github.mennakhalilselim.taskmanagementsystem.error;

import io.github.mennakhalilselim.taskmanagementsystem.error.exception.TaskNotFound;
import io.github.mennakhalilselim.taskmanagementsystem.model.dto.response.ApiResponseDto;
import io.github.mennakhalilselim.taskmanagementsystem.util.ApiResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.computeIfAbsent(error.getField(), key -> new ArrayList<>())
                        .add(error.getDefaultMessage())
        );

        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.computeIfAbsent(error.getObjectName(), key -> new ArrayList<>())
                        .add(error.getDefaultMessage())
        );

        return ApiResponseUtil.validationError(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ApiResponseUtil.badRequest("Request body is missing or invalid");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleBadCredentials(BadCredentialsException ex) {
        return ApiResponseUtil.unauthorized("Invalid username or password");
    }

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<ApiResponseDto<Void>> handlePictureNotFound(TaskNotFound ex) {
        return ApiResponseUtil.notFound(ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleNoResourceFoundException(NoResourceFoundException ex) {
        return ApiResponseUtil.notFound(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Void>> handleException(Exception ex) {
        log.error("An error occurred: {}", ex.getMessage(), ex);
        return ApiResponseUtil.internalServerError();
    }
}
