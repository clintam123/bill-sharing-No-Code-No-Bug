package com.nocodenobug.billsharing.payload.response;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.exceptions.ProjectException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponse<T> {
    private Integer status;
    private String message;
    private T data;


    public static <T> DefaultResponse<T> success(T body) {
        DefaultResponse<T> response = new DefaultResponse<>();
        response.setStatus(ResponseStatusConstant.SUCCESS.getCode());
        response.setMessage(ResponseStatusConstant.SUCCESS.getMessage());
        response.setData(body);
        return response;
    }

    public static <T> DefaultResponse<T> error(ProjectException e) {
        DefaultResponse<T> response = new DefaultResponse<>();
        response.setStatus(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }

    public static <T> DefaultResponse<T> error(String message) {
        DefaultResponse<T> response = new DefaultResponse<>();
        response.setStatus(ResponseStatusConstant.COMMON_FAIL.getCode());
        response.setMessage(message);
        return response;
    }
}
