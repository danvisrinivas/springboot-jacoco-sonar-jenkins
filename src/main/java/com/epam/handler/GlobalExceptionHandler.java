package com.epam.handler;

import com.epam.dtos.APIResponse;
import com.epam.dtos.ErrorResponse;
import com.epam.dtos.FieldError;
import com.epam.exception.AssociateException;
import com.epam.exception.BatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String FAILED = "FAILED";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<ErrorResponse> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        log.error("GlobalExceptionHandler:handleMethodArgumentException is invoked {}",exception.getMessage());
        ErrorResponse errors = ErrorResponse.builder()
                        .time(new Date().toString()).fieldErrors(new ArrayList<>()).build();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    FieldError fieldError = new FieldError(error.getField(), error.getDefaultMessage());
                    errors.getFieldErrors().add(fieldError);
                });

        return APIResponse.<ErrorResponse>builder().status(FAILED).results(errors).build();
    }

    @ExceptionHandler(AssociateException.class)
    public APIResponse<ErrorResponse> handleServiceException(AssociateException exception) {
        log.error("GlobalExceptionHandler: handleServiceException is invoked {}",exception.getMessage());
        return getErrorResponseAPIResponse( exception.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse<ErrorResponse> handleRuntimeException(RuntimeException exception,WebRequest webRequest) {
        log.error("GlobalExceptionHandler:handleRuntimeException is invoked {}",exception.getMessage());
        return getErrorResponseAPIResponse( exception.getMessage());
    }

    private APIResponse<ErrorResponse> getErrorResponseAPIResponse( String message) {
        ErrorResponse errors = ErrorResponse.builder()
                .time(new Date().toString()).fieldErrors(new ArrayList<>()).build();

        errors.getFieldErrors().add(FieldError.builder().errorMessage(message).build());
        return APIResponse.<ErrorResponse>builder().status(FAILED).results(errors).build();
    }
}
