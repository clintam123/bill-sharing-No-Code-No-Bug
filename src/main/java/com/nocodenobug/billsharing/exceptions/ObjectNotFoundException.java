package com.nocodenobug.billsharing.exceptions;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;

public class ObjectNotFoundException extends ProjectException {

    public ObjectNotFoundException(ResponseStatusConstant e) {
        super(e.getCode(), e.getMessage());
    }
}
