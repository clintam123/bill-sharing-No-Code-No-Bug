package com.nocodenobug.billsharing.exceptions.handling;

import com.nocodenobug.billsharing.exceptions.BadRequestException;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.exceptions.ProjectException;
import com.nocodenobug.billsharing.exceptions.WrongFormatException;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public SampleResponse handleNotFoundException(NotFoundException e) {
        return SampleResponse.builder()
                .success(false)
                .message(e.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(value =BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public SampleResponse handleBadRequestException(BadRequestException e) {
        return SampleResponse.builder()
                .success(false)
                .message(e.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(value = WrongFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DefaultResponse<?> handleWrongFormatException(WrongFormatException e) {
        return this.handleProjectException(e);
    }

    @ExceptionHandler(ProjectException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DefaultResponse<?> handleProjectException(ProjectException e) {
        return DefaultResponse.error(e);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            System.out.println(error.getDefaultMessage());
            errors.add(error.getDefaultMessage());
        }
        SampleResponse response = SampleResponse.builder()
                .success(false)
                .message(errors.toString())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
