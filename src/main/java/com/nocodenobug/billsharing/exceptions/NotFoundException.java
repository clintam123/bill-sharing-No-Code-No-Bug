package com.nocodenobug.billsharing.exceptions;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends ProjectException{

    public NotFoundException(int code, String message, Object data){
        super(code,message,data);
    }
}
