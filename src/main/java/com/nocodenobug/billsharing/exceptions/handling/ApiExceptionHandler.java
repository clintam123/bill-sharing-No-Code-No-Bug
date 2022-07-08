package com.nocodenobug.billsharing.exceptions.handling;

import com.nocodenobug.billsharing.exceptions.ProjectException;
import com.nocodenobug.billsharing.response.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ProjectException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DefaultResponse handleProjectException(ProjectException e) {
        return DefaultResponse.error(e);
    }
}
