package com.nocodenobug.billsharing.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SampleResponse {
    private Boolean success;
    private String message;
    private Object data;
}
