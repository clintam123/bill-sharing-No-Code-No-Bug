package com.nocodenobug.billsharing.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WrongFormatException extends ProjectException {
    private int code;
    private String message;

    public WrongFormatException(String message) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }
}
