package com.nocodenobug.billsharing.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionObject extends RuntimeException{
    String message;
}
