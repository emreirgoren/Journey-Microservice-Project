package com.patika.auth_service.exception;

import com.patika.auth_service.dto.response.GenericResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthServiceException.class)
    public GenericResponse handleBlogHubException(AuthServiceException authServiceException){
        return GenericResponse.failed(authServiceException.getMessage());
    }

}