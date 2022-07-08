package com.nocodenobug.billsharing.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleResponse {
    private Boolean success;
    private String message;
    private Object data;
}
