package com.nocodenobug.billsharing.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
}
